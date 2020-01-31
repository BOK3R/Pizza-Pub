package pl.pizza.pub.utils;

/**
 * Creats self exception
 */
public class ApplicationException extends Exception {

    /**
     * Constuctor of self exception
     * @param message message of exception
     */
    public ApplicationException(String message) {
        super(message);
    }
}
