-- password: admin
insert into USER (ID, USERNAME, EMAIL, PASSWORD, ROLE) values(1, 'admin', 'admin@mail.ru', '$2a$10$UcSq8eLGwiok4IbALVkigOK/LyteumYOHU.dFxbWpnVOY.scE7xLO', 'ROLE_ADMIN');
-- password: password
insert into USER (ID, USERNAME, EMAIL, PASSWORD, ROLE) values(2, 'user', 'user@mail.ru', '$2a$10$Sg.7p0G/BxmAsW9IgWWSA.a1NKzNB770.6rRIUD4tI5TTVZdV30oS', 'ROLE_USER');