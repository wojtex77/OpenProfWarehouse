package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

public class DuplicatedEntryException extends Exception{
    public DuplicatedEntryException() {
    }

    public DuplicatedEntryException(String message) {
        super(message);
    }
}
