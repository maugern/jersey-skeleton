-- Nicolas Mauger

-- database_creation.sql

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(30),
  alias VARCHAR(30) UNIQUE,
  email VARCHAR(256) UNIQUE,
  passwdHash VARCHAR(512)
);
