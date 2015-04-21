package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * A transition between two States in a NonDeterministicFiniteAutomaton
 */
public class NonDeterministicTransition {

    @NotNull
    private final State from, to;
    @NotNull
    private final Optional<Symbol> on;

    public NonDeterministicTransition(@NotNull State from, @NotNull State to, @NotNull Optional<Symbol> on) {
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

    public Optional<Symbol> on() {
        return on;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof NonDeterministicTransition
                && (this.from().equals(((NonDeterministicTransition) object).from()))
                && (this.on().equals(((NonDeterministicTransition) object).on()))
                && (this.to().equals(((NonDeterministicTransition) object).to()));
    }

    @Override
    public int hashCode() {
        return this.from().hashCode() + this.to().hashCode() + this.on().hashCode();
    }
}
