package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * A set of symbols. An Automaton can recognise an Alphabet of Symbols.
 */
public class Alphabet {
    @NotNull
    private final HashSet<Symbol> symbols;

    public Alphabet(@NotNull Set<Symbol> symbols) {
        this.symbols = new HashSet<>(symbols);
    }

    public Set<Symbol> symbols() {
        return symbols;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Alphabet && (this.symbols().equals(((Alphabet) object).symbols()));
    }

    @Override
    public int hashCode() {
        return "Alphabet ".hashCode() + symbols.hashCode();
    }
}
