ALTER TABLE ORDERED_ITEMS ADD NESTED_QTY INT AFTER QTY;
ALTER TABLE ORDERED_ITEMS ADD TO_NEST_QTY INT AFTER NESTED_QTY;