ALTER TABLE "MATERIAL_STOCK"
ADD
SINGLE_WEIGHT DECIMAL(10, 2) NOT NULL AFTER AVAILABLE_QTY;

ALTER TABLE "MATERIAL_STOCK"
ADD
WHOLE_WEIGHT DECIMAL(10, 2) NOT NULL AFTER SINGLE_WEIGHT;