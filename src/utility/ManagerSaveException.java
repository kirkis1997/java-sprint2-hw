package utility;

public class ManagerSaveException extends  RuntimeException {

    public ManagerSaveException(final String message) {
        super(message);
    }

    public ManagerSaveException(final String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerSaveException(final Throwable cause) {
        super(cause);
    }
}