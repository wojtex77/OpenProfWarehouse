package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class RectangularTube implements Shape {

    private final ShapeType type;
    private double width;
    private double height;
    private double thickness;
    private double area;
    private String name;

    RectangularTube(double width, double height, double thickness) {
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        calcArea();
        type=ShapeType.RECTANGULAR;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void calcArea() {
        area = 2 * thickness * (height + width) - (4 * thickness * thickness);
    }


    @Override
    public String generateName() {
        name = "RP" + "-" + width + "x" + height+ "x" + thickness;
        return name;
    }
}
