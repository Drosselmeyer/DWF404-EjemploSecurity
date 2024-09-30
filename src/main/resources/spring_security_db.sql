CREATE DATABASE spring_security_db;

USE spring_security_db;

CREATE TABLE role (
    name VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(100) NOT NULL
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id VARCHAR(50) NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES user(id),
                            FOREIGN KEY (role_id) REFERENCES role(name)
);

-- Insertar roles
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_VENDEDOR');
INSERT INTO role (name) VALUES ('ROLE_CLIENTE');

-- Insertar usuarios con contraseñas encriptadas
INSERT INTO user (username, password) VALUES ('admin', '$2a$10$CwTycUXWue0Thq9StjUM0uJ8TBgUx7HkXbzpvM8yX7UPX6BfKLS9m'); -- password: admin
INSERT INTO user (username, password) VALUES ('vendedor', '$2a$10$CwTycUXWue0Thq9StjUM0uJ8TBgUx7HkXbzpvM8yX7UPX6BfKLS9m'); -- password: vendedor
INSERT INTO user (username, password) VALUES ('cliente', '$2a$10$CwTycUXWue0Thq9StjUM0uJ8TBgUx7HkXbzpvM8yX7UPX6BfKLS9m'); -- password: cliente

-- Asignar roles a los usuarios
INSERT INTO user_roles (user_id, role_id) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role_id) VALUES (2, 'ROLE_VENDEDOR');
INSERT INTO user_roles (user_id, role_id) VALUES (3, 'ROLE_CLIENTE');
