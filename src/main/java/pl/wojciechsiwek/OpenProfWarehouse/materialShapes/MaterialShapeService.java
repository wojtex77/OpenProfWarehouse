package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.CircularTube;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.RectangularTube;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.Shape;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.SpecialShape;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MaterialShapeService {

    MaterialShape toMaterialShape(Shape tube) {
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
                case "RO": {
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
                default: {
                    Shape shape = new SpecialShape(name, materialShape.map(MaterialShape::getArea).orElse(null));
                    shape.setId(materialShape.map(MaterialShape::getId).orElse(null));
                    return shape;
                }
            }
        } else {
            return new SpecialShape(name, materialShape.map(MaterialShape::getArea).orElse(null));
        }
    }

    String getTemplate(Shape shape) {

        if (shape.getName().substring(0, 2).equals("RO")){
            return "materialShapes/editCircular";
        }
        return "materialShapes/editSpecial";
    }
}
