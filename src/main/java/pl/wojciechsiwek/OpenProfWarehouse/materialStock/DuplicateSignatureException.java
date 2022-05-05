package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

public class DuplicateSignatureException extends RuntimeException{
    public DuplicateSignatureException() {
    }

    public DuplicateSignatureException(String message) {
        super(message);
    }
}
