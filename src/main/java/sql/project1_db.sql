create table person(
  person_id int generated by default as identity primary key,
  name varchar(100) not null unique,
  yearOfBirth int not null
);
create table book(
    id_book int generated by default as identity primary key,
    person_id int references person(person_id) on delete set null,
    name_book varchar(100) not null,
    year_book int not null,
    author_book varchar(100) not null
);
insert into person(name, yearOfBirth) values('Kola', 1980);
insert into book(person_id, name_book, year_book, author_book) values(1, 'Tom i gery', 1980, 'Kola');
select * from person;
select * from book;



