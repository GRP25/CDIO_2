INSERT INTO user(user_name, user_init, user_cpr, user_password) 
    VALUES('Bobby Jensen','BJ',1122334444,'password');
INSERT INTO user(user_name, user_init, user_cpr, user_password) 
    VALUES('Jens Peter','JP',1122331111,'password');
INSERT INTO user(user_name, user_init, user_cpr, user_password) 
    VALUES('Kenneth Ursula','KU',1122332222,'password');
INSERT INTO user(user_name, user_init, user_cpr, user_password) 
    VALUES('Ole Henriksen','OH',1122333333,'password');
INSERT INTO user(user_name, user_init, user_cpr, user_password) 
    VALUES('Ib Hansen','IH',1122335555,'password');

INSERT INTO has_roles (user_id, roles_title)
    VALUES(1,'Admin');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(2,'Admin');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(2,'Pharmaceut');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(3,'Produktionsleder');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(3,'Laborant');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(4,'Admin');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(4,'Laborant');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(5,'Admin');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(5,'Pharmaceut');
INSERT INTO has_roles (user_id, roles_title)
    VALUES(5,'Produktionsleder');
