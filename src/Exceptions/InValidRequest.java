package Exceptions;

public class InValidRequest extends RuntimeException {
    public InValidRequest(String message) {
        super(message);
    }
}
