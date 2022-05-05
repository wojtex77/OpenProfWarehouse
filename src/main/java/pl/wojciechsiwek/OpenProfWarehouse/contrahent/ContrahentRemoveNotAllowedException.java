package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

public class ContrahentRemoveNotAllowedException extends Exception {
    ContrahentRemoveNotAllowedException() {
    }

    ContrahentRemoveNotAllowedException(String message) {
        super(message);
    }
}
