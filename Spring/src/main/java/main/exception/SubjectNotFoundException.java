package main.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(String msg) {
        super(msg);
    }
}
