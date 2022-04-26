package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

import java.util.List;

public class Shapes {

    List<ShapeType> shapes;

    Shapes() {
        shapes.add(ShapeType.CIRCULAR_TUBE);
        shapes.add(ShapeType.RECTANGULAR_TUBE);
        shapes.add(ShapeType.SPECIAL);
    }

    public List<ShapeType> getShapes() {
        return shapes;
    }
}
