package com.github.appreciated.app.layout.test.left;

/**
 * Created by Intellij IDEA.
 * Date: 27. 2. 2019 12:46
 *
 * @author Gabriel Gecy
 */
public class AccessDeniedException extends Exception {

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
