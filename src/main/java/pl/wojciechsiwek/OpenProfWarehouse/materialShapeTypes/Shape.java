package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public interface Shape {

    void calcArea();
    String generateName();
    Double getArea();
    String getName();

    void setId(int id);

    ShapeType getType();
    int getId();

}
