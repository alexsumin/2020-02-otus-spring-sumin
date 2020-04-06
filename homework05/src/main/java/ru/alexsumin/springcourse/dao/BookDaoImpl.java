package ru.alexsumin.springcourse.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.dao.mapper.AuthorMapper;
import ru.alexsumin.springcourse.dao.mapper.BookMapper;
import ru.alexsumin.springcourse.dao.mapper.GenreMapper;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;

import java.io.Serializable;
import java.util.*;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookDaoImpl implements BookDao {

    NamedParameterJdbcOperations jdbc;
    BookMapper bookMapper;
    AuthorMapper authorMapper;
    GenreMapper genreMapper;

    @Override
    public Book save(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(prepareSaveQuery(book), prepareParameterSource(book), keyHolder);
        long id = keyHolder.getKey().longValue();
        book.setId(id);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        var query = "select b.book_id, b.title, b.published, " +
                "a.author_id, a.name, " +
                "g.genre_id, g.name " +
                "from books b " +
                "inner join authors a on b.author_id = a.author_id " +
                "inner join genres g on b.genre_id = g.genre_id " +
                "where b.book_id = :id " +
                "order by b.book_id";

        return Optional.ofNullable(
                jdbc.query(query, Map.of("id", id), resultSet -> {
                    final int i = 0;
                    Book book = null;
                    while (resultSet.next()) {
                        book = bookMapper.mapRow(resultSet, i);
                        if (book == null) return null;
                        Author author = authorMapper.mapRow(resultSet, i);
                        Genre genre = genreMapper.mapRow(resultSet, i);
                        book.setGenre(genre);
                        book.setAuthor(author);
                    }
                    return book;
                }));
    }


    @Override
    public List<Book> findAll() {
        var query = "select b.book_id, b.title, b.published, " +
                "a.author_id, a.name, " +
                "g.genre_id, g.name " +
                "from books b " +
                "inner join authors a on b.author_id = a.author_id " +
                "inner join genres g on b.genre_id = g.genre_id " +
                "order by b.book_id";

        return jdbc.query(query, rs -> {
            var books = new ArrayList<Book>();
            Book currentBook;
            int bookId = 0;
            while (rs.next()) {
                currentBook = bookMapper.mapRow(rs, bookId++);
                books.add(currentBook);
                currentBook.setGenre(genreMapper.mapRow(rs, bookId));
                currentBook.setAuthor(authorMapper.mapRow(rs, bookId));
            }
            return books;
        });
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from books where book_id = :id",
                Map.of("id", id));
    }

    @Override
    public Book update(Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("published", book.getPublished())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());
        jdbc.update("update books " +
                        "set title = :title, " +
                        "published=:published, " +
                        "author_id=:author_id, " +
                        "genre_id=:genre_id " +
                        "where " +
                        "book_id = :book_id", params);
        return book;

    }

    private String prepareSaveQuery(Book book) {
        var insertPart = new StringBuilder("insert into books (title, published");
        var valuesPart = new StringBuilder(") values (:title, :published");
        if (book.getAuthor() != null) {
            insertPart.append(", author_id");
            valuesPart.append(", :author_id");
        }
        if (book.getGenre() != null) {
            insertPart.append(", genre_id");
            valuesPart.append(", :genre_id");
        }
        return insertPart
                .append(valuesPart)
                .append(")")
                .toString();
    }

    private SqlParameterSource prepareParameterSource(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("published", book.getPublished());
        if (book.getAuthor() != null) {
            parameterSource.addValue("author_id", book.getAuthor().getId());
        }
        if (book.getGenre() != null) {
            parameterSource.addValue("genre_id", book.getGenre().getId());
        }
        return parameterSource;
    }
}
