CREATE TABLE nested_ordered_items (
    id int NOT NULL AUTO_INCREMENT,
    reservation_id int NOT NULL,
    ordered_item_id int NOT NULL,
    nested_qty int NOT NULL,


    PRIMARY KEY (id),

    FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ordered_item_id) REFERENCES ordered_items(id) ON UPDATE CASCADE ON DELETE CASCADE
);