package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import java.util.List;

public class Shapes {

    List<ShapeType> shapes;

    Shapes() {
        shapes.add(ShapeType.CIRCULAR);
        shapes.add(ShapeType.RECTANGULAR);
        shapes.add(ShapeType.SPECIAL);
    }

    public List<ShapeType> getShapes() {
        return shapes;
    }
}
