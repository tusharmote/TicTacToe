package Tictactoecode.Exception;

public class DupliacteSymbolException extends Exception{
    public DupliacteSymbolException() {
    }

    public DupliacteSymbolException(String message) {
        super(message);
    }

    public DupliacteSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DupliacteSymbolException(Throwable cause) {
        super(cause);
    }

    public DupliacteSymbolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
