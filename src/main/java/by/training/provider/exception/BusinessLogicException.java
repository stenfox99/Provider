package by.training.provider.exception;

public class BusinessLogicException extends Exception{
    public BusinessLogicException(){
        super();
    }

    public BusinessLogicException(String message, Throwable cause){
        super(message, cause);
    }

    public BusinessLogicException(Throwable cause){
        super(cause);
    }

    public BusinessLogicException(String message){
        super(message);
    }
}
