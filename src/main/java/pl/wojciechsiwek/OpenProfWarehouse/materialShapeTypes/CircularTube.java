package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class CircularTube implements Shape {

    private final ShapeType type;
    private double outerDiameter;
    private double innerDiameter;
    private double thickness;
    private double area;

    private String name;


    CircularTube(float outerDiameter, float thickness) {
        this.outerDiameter = outerDiameter;
        this.thickness = thickness;
        innerDiameter = outerDiameter - (2 * thickness);
        type = ShapeType.CIRCULAR;
        calcArea();
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void calcArea() {
        area = (Math.PI / 4) * (Math.pow(outerDiameter, 2) - Math.pow(innerDiameter, 2));
    }

    @Override
    public String generateName() {
        name = "RO" + "-" + outerDiameter + "x" + thickness;
        return name;
    }
}
