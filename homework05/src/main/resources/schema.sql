DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS AUTHORS;

CREATE TABLE GENRES(
    GENRE_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255)
);

CREATE TABLE AUTHORS(
    AUTHOR_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255)
);

CREATE TABLE BOOKS(
    BOOK_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(255),
    PUBLISHED DATE,
    GENRE_ID BIGINT,
    AUTHOR_ID BIGINT,
    FOREIGN KEY (GENRE_ID) REFERENCES GENRES(GENRE_ID),
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(AUTHOR_ID)
);