package pl.wojciechsiwek.OpenProfWarehouse.orders;

public class DuplicatedOrderEntryException extends RuntimeException{
    public DuplicatedOrderEntryException() {
    }

    public DuplicatedOrderEntryException(String message) {
        super(message);
    }
}
