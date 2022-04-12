package main.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String msg){
        super(msg);
    }
}
