package ru.alexsumin.springcourse.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.dao.mapper.AuthorMapper;
import ru.alexsumin.springcourse.domain.Author;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorDaoImpl implements AuthorDao {

    NamedParameterJdbcOperations jdbc;
    AuthorMapper authorMapper;

    @Override
    public Author save(Author author) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into authors (name) values (:name)",
                new MapSqlParameterSource(Map.of("name", author.getName())),
                keyHolder);
        author.setId(keyHolder.getKey().longValue());
        return author;
    }

    @Override
    public List<Author> findAll() {
        return jdbc.query("select * from authors", authorMapper);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return jdbc.query("select * from authors where name=:name",
                Map.of("name", name),
                rs -> rs.next() ? Optional.ofNullable(authorMapper.mapRow(rs, 1)) : Optional.empty());
    }
}
