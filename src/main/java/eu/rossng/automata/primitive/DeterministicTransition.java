package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

/**
 * A transition between two AutomatonStates
 */
public class DeterministicTransition {

    @NotNull
    private final State from, to;
    @NotNull
    private final Symbol on;

    public DeterministicTransition(@NotNull State from, @NotNull State to, @NotNull Symbol on) {
        this.from = from;
        this.to = to;
        this.on = on;
    }

    public State from() {
        return from;
    }

    public State to() {
        return to;
    }

    public Symbol on() {
        return on;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof DeterministicTransition
                && (this.from().equals(((DeterministicTransition) object).from()))
                && (this.on().equals(((DeterministicTransition) object).on()))
                && (this.to().equals(((DeterministicTransition) object).to()));
    }

    @Override
    public int hashCode() {
        return this.from().hashCode() + this.to().hashCode() + this.on().hashCode();
    }
}
