package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

public class GradeRemoveNotAllowedException extends Exception {
    GradeRemoveNotAllowedException() {
    }

    GradeRemoveNotAllowedException(String message) {
        super(message);
    }
}
