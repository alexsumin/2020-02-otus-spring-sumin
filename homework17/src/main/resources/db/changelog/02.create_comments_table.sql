CREATE TABLE COMMENTS(
    ID BIGSERIAL PRIMARY KEY,
    TEXT VARCHAR(1000) NOT NULL,
    BOOK_ID BIGINT NOT NULL,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID)
);