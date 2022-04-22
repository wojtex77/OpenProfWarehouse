package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class SpecialShape implements Shape {

    private final ShapeType type;
    private double area;
    private String name;
    private int id;

    public SpecialShape(String name, double area) {
        this.area = area;
        this.name = name;
        type=ShapeType.SPECIAL;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public void calcArea() {
    }

    @Override
    public String generateName() {
        return null;
    }

    @Override
    public Double getArea() {
        return area;
    }

    @Override
    public String getName() {
        return name;
    }
}
