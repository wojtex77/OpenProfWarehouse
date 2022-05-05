package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

public class DuplicatedGradeEntryException extends RuntimeException{
    public DuplicatedGradeEntryException() {
    }

    public DuplicatedGradeEntryException(String message) {
        super(message);
    }
}
