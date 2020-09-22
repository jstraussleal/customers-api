package br.com.jstrauss.platformbuilders.commons.exceptions;

public class BusinessException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 8209073177881451753L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }    
    
}