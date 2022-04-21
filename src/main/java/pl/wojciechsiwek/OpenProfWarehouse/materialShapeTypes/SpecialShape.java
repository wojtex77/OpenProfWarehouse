package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class SpecialShape implements Shape {

    private final ShapeType type;
    private double area;
    private String name;

    public SpecialShape(String name, double area) {
        this.area = area;
        this.name = name;
        type=ShapeType.SPECIAL;
    }



    @Override
    public void calcArea() {
    }

    @Override
    public String generateName() {
        return name;
    }

    @Override
    public double getArea() {
        return area;
    }
}
