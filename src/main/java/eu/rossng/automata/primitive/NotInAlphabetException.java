package eu.rossng.automata.primitive;

/**
 * Thrown when a Symbol to be executed does not appear in the relevant
 * Alphabet
 */
public class NotInAlphabetException extends IllegalArgumentException {
    public NotInAlphabetException(String message) {
        super(message);
    }
}
