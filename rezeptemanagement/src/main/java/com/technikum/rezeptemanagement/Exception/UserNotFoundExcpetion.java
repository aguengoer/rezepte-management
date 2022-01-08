package com.technikum.rezeptemanagement.Exception;

public class UserNotFoundExcpetion extends RuntimeException {

    public UserNotFoundExcpetion() {
    }

    public UserNotFoundExcpetion(String message) {
        super(message);
    }

    public UserNotFoundExcpetion(Throwable cause) {
        super(cause);
    }

    public UserNotFoundExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundExcpetion(String message, Object... args) {
        super(String.format(message, args));
    }
}
