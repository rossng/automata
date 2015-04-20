package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * A Symbol, which can belong to an Alphabet of Symbols recognised by an Automaton as part of a Word.
 */
public class Symbol {
    @NotNull
    String representation;
    public Symbol(@NotNull String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Symbol && (this.toString().equals(((Symbol) object).toString()));
    }

    @Override
    public int hashCode() {
        return this.representation.hashCode();
    }
}
