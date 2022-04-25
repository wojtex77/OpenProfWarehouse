package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class RectangularBar implements Shape {

    private final ShapeType type;
    private Double width;
    private Double height;
    private Double area;
    private String name;
    private int id;


    public RectangularBar(Double width, Double height) {
        this.width = width;
        this.height = height;
        calcArea();
        type = ShapeType.RECTANGULAR_BAR;
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
        if (width == null || height == null) {
            area = 0d;
        } else {
            area = height * width;
        }
    }


    @Override
    public String generateName() {
        if (width == null || height == null) {
            name = "";
        } else {
            name = "PP" + "-" + width + "x" + height;
        }
        return name;
    }
}
