CREATE TABLE user (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(30),
    password VARCHAR(30)
);

INSERT INTO user (name, password) VALUES ('user', '1234');
