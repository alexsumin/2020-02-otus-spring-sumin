package ru.alexsumin.springcourse.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.dao.mapper.GenreMapper;
import ru.alexsumin.springcourse.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreDaoImpl implements GenreDao {

    NamedParameterJdbcOperations jdbc;
    GenreMapper genreMapper;

    @Override
    public Genre save(Genre genre) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genres (name) values (:name)",
                new MapSqlParameterSource(Map.of("name", genre.getName())),
                keyHolder);
        genre.setId(keyHolder.getKey().longValue());
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("select * from genres", genreMapper);
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return jdbc.query("select * from genres where name=:name",
                Map.of("name", name),
                rs -> rs.next() ? Optional.ofNullable(genreMapper.mapRow(rs, 1)) : Optional.empty());
    }
}
