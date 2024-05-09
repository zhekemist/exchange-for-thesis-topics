CREATE DATABASE IF NOT EXISTS test;

USE test;

CREATE TABLE IF NOT EXISTS person (
    id INT NOT NULL AUTO_INCREMENT,
    name TEXT NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO person ( name ) VALUES ( 'Testing Person A' );
INSERT INTO person ( name ) VALUES ( 'Testing Person B' );
