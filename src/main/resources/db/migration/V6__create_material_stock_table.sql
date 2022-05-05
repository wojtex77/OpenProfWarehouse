CREATE TABLE material_stock (
    signature varchar(100) NOT NULL,
    profile varchar(100) NOT NULL,
    material varchar(100) NOT NULL,
    profile_length DECIMAL(10, 2) NOT NULL,
    qty int NOT NULL,
    available_qty int NOT NULL,
    material_type varchar(100) NOT NULL,


    PRIMARY KEY (signature),
    UNIQUE (signature),
    FOREIGN KEY (profile) REFERENCES material_shapes(name) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (material) REFERENCES material_grades(full_name) ON UPDATE CASCADE ON DELETE RESTRICT

);