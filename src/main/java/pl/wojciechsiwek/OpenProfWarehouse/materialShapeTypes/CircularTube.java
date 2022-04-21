package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

public class CircularTube implements Shape {

    private final ShapeType type;
    private Double outerDiameter;
    private Double innerDiameter;
    private Double thickness;
    private Double area;

    private String name;


    public CircularTube(Double outerDiameter, Double thickness) {
        this.outerDiameter = outerDiameter;
        this.thickness = thickness;
        if (outerDiameter == null || thickness == null) {
            innerDiameter = null;
        } else {
            innerDiameter = outerDiameter - (2 * thickness);
        }
        type = ShapeType.CIRCULAR;
        calcArea();
    }

    public ShapeType getType() {
        return type;
    }

    public Double getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(Double outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public Double getInnerDiameter() {
        return innerDiameter;
    }

    public void setInnerDiameter(Double innerDiameter) {
        this.innerDiameter = innerDiameter;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getArea() {
        return area;
    }

    @Override
    public void calcArea() {
        if (outerDiameter == null || thickness == null) {
            area = 0d;
        } else {
            area = (Math.PI / 4) * (Math.pow(outerDiameter, 2) - Math.pow(innerDiameter, 2));
        }
    }

    @Override
    public String generateName() {
        if (outerDiameter == null || thickness == null) {
            name = "";
        } else {
            name = "RO" + "-" + outerDiameter + "x" + thickness;
        }
        return name;
    }
}
