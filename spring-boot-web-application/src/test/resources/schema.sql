DROP TABLE IF EXISTS users;

CREATE TABLE users (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL,
   email TEXT NOT NULL,
   active INTEGER NOT NULL
);

INSERT INTO users
(name, email, active)
VALUES('Alice', 'alice@gmail.com', true),
      ('Bob', 'bob@gmail.com', false),
      ('Carol', 'carol@gmail.com', true),
      ('Dan', 'dan@gmail.com', false),
      ('Eve', 'eve@gmail.com', true),
      ('Fred', 'fred@gmail.com', true),
      ('Ger', 'ger@gmail.com', false),
        ('Harriet', 'harriet@gmail.com', true)
;