CREATE TABLE ordered_items (
    id int NOT NULL AUTO_INCREMENT,
    order_number varchar(100) NOT NULL,
    part_id int NOT NULL,
    qty int NOT NULL,

    PRIMARY KEY (id),

    FOREIGN KEY (order_number) REFERENCES orders(order_number) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (part_id) REFERENCES parts(id) ON UPDATE CASCADE ON DELETE RESTRICT

);