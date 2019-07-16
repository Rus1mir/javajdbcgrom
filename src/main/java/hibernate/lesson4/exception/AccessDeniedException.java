package hibernate.lesson4.exception;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }
}
