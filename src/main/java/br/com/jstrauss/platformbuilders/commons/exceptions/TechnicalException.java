package br.com.jstrauss.platformbuilders.commons.exceptions;

public class TechnicalException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 4846161541462614981L;

    public TechnicalException() {
        super();
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }   
    
}