package pwr.cospacefinderbackend.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
    }
    public NotFoundException(String s) {
        super(s);
    }
}
