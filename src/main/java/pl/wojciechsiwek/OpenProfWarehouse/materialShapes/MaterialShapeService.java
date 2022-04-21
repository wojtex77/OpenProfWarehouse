package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.RectangularTube;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.Shape;

@Service
public class MaterialShapeService {

    MaterialShape toMaterialShape(Shape tube){
        MaterialShape materialShape = new MaterialShape();
        materialShape.setArea(tube.getArea());
        tube.generateName();
        materialShape.setName(tube.getName());

        return  materialShape;
    }
}
