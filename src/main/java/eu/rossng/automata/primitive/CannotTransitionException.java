package eu.rossng.automata.primitive;

/**
 * Thrown when there is no valid Transition to follow
 */
public class CannotTransitionException extends Exception {
    public CannotTransitionException(String message) {
        super(message);
    }
}
