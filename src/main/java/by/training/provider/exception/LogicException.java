package by.training.provider.exception;

/**
 * The Class LogicException.
 */
public class LogicException extends Exception{
    
    /**
     * Instantiates a new logic exception.
     */
    public LogicException(){
        super();
    }

    /**
     * Instantiates a new logic exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public LogicException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * Instantiates a new logic exception.
     *
     * @param cause the cause
     */
    public LogicException(Throwable cause){
        super(cause);
    }

    /**
     * Instantiates a new logic exception.
     *
     * @param message the message
     */
    public LogicException(String message){
        super(message);
    }
}
