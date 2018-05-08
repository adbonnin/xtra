package fr.adbonnin.xtra.exception;

public class ExhaustedRetryException extends RuntimeException {

    public ExhaustedRetryException(String message) {
        super(message);
    }
}
