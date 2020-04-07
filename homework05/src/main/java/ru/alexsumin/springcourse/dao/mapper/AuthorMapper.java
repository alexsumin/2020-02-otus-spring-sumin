package ru.alexsumin.springcourse.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.alexsumin.springcourse.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        return Author.builder()
                .id(resultSet.getLong("author_id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
