package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

public class ShapeDeleteRemoveNotAllowedException extends Exception{

    ShapeDeleteRemoveNotAllowedException() {
    }

    ShapeDeleteRemoveNotAllowedException(String message) {
        super(message);
    }
}
