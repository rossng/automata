package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

/**
 * A state in an Automaton
 */
public class State {
    @NotNull
    private final String name;
    public State(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return "State {" + this.name + "}";
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof State && (this.toString().equals(((State) object).toString()));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
