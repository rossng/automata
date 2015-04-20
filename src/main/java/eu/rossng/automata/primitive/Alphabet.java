package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * A set of symbols. An Automaton can recognise an Alphabet of Symbols.
 */
public class Alphabet {
    @NotNull
    HashSet<Symbol> symbols;
    public Alphabet(@NotNull Set<Symbol> symbols) {
        this.symbols = new HashSet<>(symbols);
    }
}
