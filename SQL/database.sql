CREATE TABLE user (
    user_id INT AUTO_INCREMENT,
    user_name VARCHAR(36) NOT NULL,
    user_init VARCHAR(5) NOT NULL,
    user_cpr INT NOT NULL UNIQUE,
    user_password VARCHAR(50) NOT NULL,
    PRIMARY KEY ( user_id )
    );

CREATE TABLE roles (
    roles_title VARCHAR(36) NOT NULL, 
    PRIMARY KEY ( roles_title ) 
    );

CREATE TABLE has_roles (
    user_id INT NOT NULL,
    roles_title VARCHAR(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (roles_title) REFERENCES roles(roles_title),
    UNIQUE (roles_title, user_id)
    );

INSERT INTO roles (roles_title) VALUES ('Admin'), ('Pharmaceut'), ('Produktionsleder'), ('Laborant')

