package ru.alexsumin.springcourse.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.alexsumin.springcourse.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        return Book.builder()
                .id(resultSet.getLong("book_id"))
                .title(resultSet.getString("title"))
                .published(resultSet.getDate("published"))
                .build();
    }
}
