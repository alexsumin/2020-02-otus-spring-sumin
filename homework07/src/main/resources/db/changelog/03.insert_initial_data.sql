insert into AUTHORS (ID, NAME) values(1, 'Bruce Eckel');
insert into AUTHORS (ID, NAME) values(2, 'Jonathan Littell');
insert into AUTHORS (ID, NAME) values(3, 'Lev Nikolayevich Tolstoy');

insert into GENRES (ID, NAME) values(1, 'Technical literature');
insert into GENRES (ID, NAME) values(2, 'Novel');

insert into BOOKS (ID, TITLE, PUBLISHED, GENRE_ID, AUTHOR_ID) values(1, 'Thinking in Java', '2006-09-13', 1, 1);
insert into BOOKS (ID, TITLE, PUBLISHED, GENRE_ID, AUTHOR_ID) values(2, 'The Kindly Ones', '2006-02-20', 2, 2);
insert into BOOKS (ID, TITLE, PUBLISHED, GENRE_ID, AUTHOR_ID) values(3, 'On Java8', '2013-01-01', 1, 1);
insert into BOOKS (ID, TITLE, PUBLISHED, GENRE_ID, AUTHOR_ID) values(4, 'War and Peace', '1869-01-01', 2, 3);

insert into COMMENTS (ID, TEXT, BOOK_ID) values(1, 'From the author of the cult Thinking in Java, Recommend!',  3);
insert into COMMENTS (ID, TEXT, BOOK_ID) values(2, 'Going to read',  4);