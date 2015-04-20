package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Defines a set of transitions.
 */
public class DeterministicDelta {
    @NotNull
    private final HashSet<Transition> transitions;

    public DeterministicDelta(@NotNull Set<Transition> transitions) {
        this.transitions = new HashSet<>(transitions);
    }

    public HashSet<Transition> getTransitions() {
        return transitions;
    }
}
