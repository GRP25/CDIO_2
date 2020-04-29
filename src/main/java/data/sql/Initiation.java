CREATE TABLE IF NOT EXISTS user (
   user_id integer PRIMARY KEY AUTOINCREMENT,
   user_name text NOT NULL,
   user_init text NOT NULL,
   user_cpr integer NOT NULL UNIQUE,
   user_password text NOT NULL,
   user_groups text NOT NULL
);
