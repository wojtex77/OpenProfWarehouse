package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class RectangularTube implements Shape {

    private final ShapeType type;
    private Double width;
    private Double height;
    private Double thickness;
    private Double area;
    private String name;
    private int id;


    public RectangularTube(Double width, Double height, Double thickness) {
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        calcArea();
        type = ShapeType.RECTANGULAR;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public ShapeType getType() {
        return type;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
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
    public Double getArea() {
        return area;
    }

    void setArea(Double area) {
        this.area = area;
    }

    @Override
    public void calcArea() {
        if (width == null || height == null || thickness == null) {
            area = 0d;
        } else {
            area = 2 * thickness * (height + width) - (4 * thickness * thickness);
        }
    }


    @Override
    public String generateName() {
        if (width == null || height == null || thickness == null) {
            name = "";
        } else {
            name = "RP" + "-" + width + "x" + height + "x" + thickness;
        }
        return name;
    }
}
