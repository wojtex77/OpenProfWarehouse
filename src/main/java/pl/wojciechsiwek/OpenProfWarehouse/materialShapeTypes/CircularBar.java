package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class CircularBar implements Shape {

    private final ShapeType type;
    private Double diameter;
    private Double area;
    private String name;
    private int id;


    public CircularBar(Double diameter) {
        this.diameter = diameter;
        calcArea();
        type = ShapeType.CIRCULAR_BAR;
    }

    public ShapeType getType() {
        return type;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Double getArea() {
        return area;
    }

    void setArea(Double area) {
        this.area = area;
    }

    @Override
    public void calcArea() {
        if (diameter == null) {
            area = 0d;
        } else {
            area = Math.PI * diameter / 4;
        }
    }


    @Override
    public String generateName() {
        if (diameter == null) {
            name = "";
        } else {
            name = "PO" + "-" + diameter;
        }
        return name;
    }
}
