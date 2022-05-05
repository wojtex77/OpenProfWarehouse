CREATE TABLE parts (
    id varchar(100) NOT NULL AUTO_INCREMENT,
    part_name varchar(100) NOT NULL,
    drawing varchar(100) NULL,
    article varchar(100) NULL,

    contrahent varchar(100) NOT NULL,
    material varchar(100) NOT NULL,
    profile varchar(100) NOT NULL,

    profile_length DECIMAL(10, 2) NOT NULL,


    PRIMARY KEY (id),
    UNIQUE (part_name),

    FOREIGN KEY (contrahent) REFERENCES contrahents(full_name) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (profile) REFERENCES material_shapes(name) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (material) REFERENCES material_grades(full_name) ON UPDATE CASCADE ON DELETE RESTRICT

);