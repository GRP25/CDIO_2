CREATE TABLE user (
    user_id INT AUTO_INCREMENT,
    user_name VARCHAR(36) NOT NULL,
    user_init VARCHAR(5) NOT NULL,
    user_cpr INT NOT NULL UNIQUE,
    user_password VARCHAR(50) NOT NULL,
    PRIMARY KEY ( user_id )
    );

CREATE TABLE roles (
    roles_id INT AUTO_INCREMENT,
    roles_title VARCHAR(36) NOT NULL, 
    PRIMARY KEY ( roles_id ) 
    );

CREATE TABLE has_roles (
    user_id INT NOT NULL,
    roles_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (roles_id) REFERENCES roles(roles_id),
    UNIQUE (roles_id, user_id)
    );
