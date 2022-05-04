CREATE TABLE contrahents (
    id int NOT NULL AUTO_INCREMENT,
    alias varchar(100) NOT NULL,
    full_name varchar(100) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (alias),
    UNIQUE (full_name)

);