package main.exception;

public class MarkNotFoundException extends RuntimeException{
    public MarkNotFoundException(String msg){
        super(msg);
    }
}
