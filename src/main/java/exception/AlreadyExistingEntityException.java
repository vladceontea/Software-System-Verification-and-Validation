package exception;

public class AlreadyExistingEntityException extends RuntimeException{
    public AlreadyExistingEntityException(String exception) {
        super(exception);
    }
}