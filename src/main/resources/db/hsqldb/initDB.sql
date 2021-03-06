DROP TABLE users_table IF EXISTS;
DROP TABLE events_table IF EXISTS;

CREATE TABLE users_table (
  id INTEGER IDENTITY PRIMARY KEY,
  login VARCHAR(32),
  password VARCHAR(32)
);

CREATE TABLE events_table (
  id INTEGER IDENTITY PRIMARY KEY,
  theme VARCHAR(512),
  descr VARCHAR(65536),
  cr_date DATETIME,
  linked_user_id INTEGER
);
ALTER TABLE events_table ADD CONSTRAINT fk_linked_user FOREIGN KEY (linked_user_id) REFERENCES users_table (id);

CREATE TABLE files_table (
  id INTEGER IDENTITY PRIMARY KEY,
  fname VARCHAR(512),
  fsize INTEGER,
  upl_date DATETIME,
  user_id INTEGER
);
ALTER TABLE files_table ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users_table (id);