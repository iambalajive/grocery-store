package com.balaji.exception;

/**
 * Exception thrown during adverse situations in the Application
 */
public class GroceryStoreException extends Exception {
    public GroceryStoreException(String message) {
        super(message);
    }
}
