package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public interface Shape {

    void calcArea();

    String generateName();

    Double getArea();

    String getName();

    ShapeType getType();

    int getId();

    void setId(int id);

}
