package Tictactoecode.Exception;

public class InvalidNoofPlayersException extends Exception {
    public InvalidNoofPlayersException() {
    }

    public InvalidNoofPlayersException(String message) {
        super(message);
    }

    public InvalidNoofPlayersException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNoofPlayersException(Throwable cause) {
        super(cause);
    }

    public InvalidNoofPlayersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
