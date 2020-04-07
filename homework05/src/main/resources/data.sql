insert into AUTHORS (author_id, name) values(1, 'Bruce Eckel');
insert into AUTHORS (author_id, name) values(2, 'Jonathan Littell');
insert into AUTHORS (author_id, name) values(3, 'Lev Nikolayevich Tolstoy');

insert into GENRES (genre_id, name) values(1, 'Technical literature');
insert into GENRES (genre_id, name) values(2, 'Novel');

insert into BOOKS (book_id, title, published, genre_id, author_id) values(1, 'Thinking in Java', '2006-09-13', 1, 1);
insert into BOOKS (book_id, title, published, genre_id, author_id) values(2, 'The Kindly Ones', '2006-02-20', 2, 2);
insert into BOOKS (book_id, title, published, genre_id, author_id) values(3, 'On Java8', '2013-01-01', 1, 1);
insert into BOOKS (book_id, title, published, genre_id, author_id) values(4, 'War and Peace', '1869-01-01', 2, 3);