package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MaterialShapeService {

    MaterialShape convertToMaterialShape(Shape tube) {
        MaterialShape materialShape = new MaterialShape();
        materialShape.setArea(tube.getArea());
        tube.generateName();
        materialShape.setName(tube.getName());
        materialShape.setId(tube.getId());

        return materialShape;
    }

    Shape returnProperType(Optional<MaterialShape> materialShape) {
        String name = materialShape.map(MaterialShape::getName).orElse(null);
        if (name != null) {
            String shapeType = name.substring(0, 2);
            switch (shapeType) {
                case "RO": { //Circular_tube
                    return convertToCircular(materialShape, name);
                }
                case "RP": { //Rectangular_tube
                    return convertToRectangularTube(materialShape, name);
                }
                case "PP": { //Rectangular_bar
                    return convertToRectangularBar(materialShape, name);
                }
                case "PO": { //Circular_bar
                    return convertToCircularBar(materialShape, name);
                }
                default: {
                    return convertToSpecial(materialShape, name);
                }
            }
        } else {
            return new SpecialShape(name, materialShape.map(MaterialShape::getArea).orElse(null));
        }
    }

    private Shape convertToCircularBar(Optional<MaterialShape> materialShape, String name) {
        Pattern pattern = Pattern.compile("[^-]*$");
        Matcher matcher = pattern.matcher(name);
        matcher.find();
        String diameter = matcher.group(0);

        Shape shape = new CircularBar(Double.valueOf(diameter));
        shape.setId(materialShape.map(MaterialShape::getId).orElse(null));
        return shape;
    }

    private Shape convertToRectangularTube(Optional<MaterialShape> materialShape, String name) {
        Pattern pattern = Pattern.compile("(?<=-)(.*?)(?=x)");
        Matcher matcher = pattern.matcher(name);
        matcher.find();
        String width = matcher.group(0);

        pattern = Pattern.compile("(?<=x)(.*?)(?=x)");
        matcher = pattern.matcher(name);
        matcher.find();
        String height = matcher.group(0);

        pattern = Pattern.compile("[^x]*$");
        matcher = pattern.matcher(name);
        matcher.find();
        String thickness = matcher.group(0);
        Shape shape = new RectangularTube(Double.valueOf(width), Double.valueOf(height), Double.valueOf(thickness));
        shape.setId(materialShape.map(MaterialShape::getId).orElse(null));
        return shape;
    }

    private Shape convertToSpecial(Optional<MaterialShape> materialShape, String name) {
        Shape shape = new SpecialShape(name, materialShape.map(MaterialShape::getArea).orElse(null));
        shape.setId(materialShape.map(MaterialShape::getId).orElse(null));
        return shape;
    }

    private Shape convertToCircular(Optional<MaterialShape> materialShape, String name) {
        Pattern pattern = Pattern.compile("(?<=-)(.*?)(?=x)");
        Matcher matcher = pattern.matcher(name);
        matcher.find();
        String outerDiameter = matcher.group(0);
        pattern = Pattern.compile("[^x]*$");
        matcher = pattern.matcher(name);
        matcher.find();
        String thickness = matcher.group(0);
        Shape shape = new CircularTube(Double.valueOf(outerDiameter), Double.valueOf(thickness));
        shape.setId(materialShape.map(MaterialShape::getId).orElse(null));
        return shape;
    }

    private Shape convertToRectangularBar(Optional<MaterialShape> materialShape, String name) {
        Pattern pattern = Pattern.compile("(?<=-)(.*?)(?=x)");
        Matcher matcher = pattern.matcher(name);
        matcher.find();
        String width = matcher.group(0);
        pattern = Pattern.compile("[^x]*$");
        matcher = pattern.matcher(name);
        matcher.find();
        String height = matcher.group(0);
        Shape shape = new RectangularBar(Double.valueOf(width), Double.valueOf(height));
        shape.setId(materialShape.map(MaterialShape::getId).orElse(null));
        return shape;
    }

    String getTemplate(Shape shape) {

        switch (shape.getType()) {
            case CIRCULAR_TUBE -> {
                return "materialShapes/editCircular";
            }
            case RECTANGULAR_TUBE -> {
                return "materialShapes/editRectangular";
            }
            case RECTANGULAR_BAR -> {
                return "materialShapes/editRectangularBar";
            }
            case CIRCULAR_BAR -> {
                return "materialShapes/editCircularBar";
            }
            default -> {
                return "materialShapes/editSpecial";
            }
        }


    }
}
