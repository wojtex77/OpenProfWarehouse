CREATE TABLE orders (
    id int NOT NULL AUTO_INCREMENT,
    order_number varchar(100) NOT NULL,
    contrahent varchar(100) NOT NULL,
    term DATE NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (order_number),

    FOREIGN KEY (contrahent) REFERENCES contrahents(full_name) ON UPDATE CASCADE ON DELETE RESTRICT

);