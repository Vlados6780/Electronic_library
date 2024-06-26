# Electronic_library:
I developed a web application for a local library that now allows librarians to easily manage patron registration, check-out, and return of books. Now librarians can easily register new readers, issue books to them, and return books to the system after they are returned. This application greatly simplifies the work of the library and provides more efficient management of its resources.
---
### Entities:
Person (fields: full name (UNIQUE), year of birth) Book (fields: title, author, year)
Relationship between entities: One to Many. A person can have many books. A book can only belong to one person.
The database must have two tables - Person and Book. Set up automatic id generation for all tables. For this project, create a new database called project1.
---
### Required functionality:
1) Pages for adding, changing and deleting a person.
2) Pages for adding, changing and deleting a book
3) A page with a list of all people (people are clickable - when clicked, you go to the person’s page).
4) A page with a list of all books (books are clickable - when clicked, you go to the book page).
5) A person’s page, which shows the values of his fields and the list of books he took. If the person has not taken any books, instead of the list there should be the text “The person has not taken any books yet.”
6) A page of a book that shows the values of the fields of this book and the name of the person who took this book. If this book has not been taken by anyone, there should be the text "This book is free."
7) On the book page, if the book was taken by a person, next to his name there should be a “Release book” button. This button is pressed by the librarian when the reader returns this book back to the library. After clicking on this button, the book becomes free again and disappears from the list of the person’s books.
8) On the book page, if the book is free, there should be a drop-down list with all people and a button “Assign book”. This button is pressed by the librarian when the reader wants to take this book home. After clicking this button, the book should begin to belong to the selected person and should appear in his list of books.
9) All fields must be validated - using @Valid and Spring Validator if required.
---
### Technologies that I used for implementation:
+ Java 19
+ Maven
+ Spring Boot
+ PostgreSQL
+ Thymeleaf
+ Spring Web

