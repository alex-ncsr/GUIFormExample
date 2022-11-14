package md.tekwill.exceptions;

public class EmployeeValidationException extends Exception{

    public EmployeeValidationException(String message) {
        super(message);
    }

    public EmployeeValidationException() {
    }

    public EmployeeValidationException(Throwable cause) {
        super(cause);
    }
}
