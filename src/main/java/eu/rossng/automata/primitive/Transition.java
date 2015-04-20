package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

/**
 * A transition between two AutomatonStates
 */
public class Transition {

    @NotNull
    private final State from, to;
    @NotNull
    private final Symbol on;

    public Transition(@NotNull State from, @NotNull State to, @NotNull Symbol on) {
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
}
