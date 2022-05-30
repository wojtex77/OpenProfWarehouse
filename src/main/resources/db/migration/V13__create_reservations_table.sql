CREATE TABLE reservations (
    id int NOT NULL AUTO_INCREMENT,
    stock_signature varchar(100) NOT NULL,
    repetition int NOT NULL,


    PRIMARY KEY (id),

    FOREIGN KEY (stock_signature) REFERENCES material_stock(signature) ON UPDATE CASCADE ON DELETE CASCADE
);