package de.fh_muenster.buecherwelt.buecherwelt.exceptions;


public class NoSessionException extends Exception {

    public NoSessionException(String message) {
        super(message);
    }

    public NoSessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}