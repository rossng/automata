package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Defines a set of transitions.
 */
public class DeterministicDelta {
    @NotNull
    private final HashSet<Transition> transitions;

    public DeterministicDelta(@NotNull Set<Transition> transitions) {
        // TODO: verify that the transitions are deterministic
        this.transitions = new HashSet<>(transitions);
    }

    public HashSet<Transition> getTransitions() {
        return transitions;
    }

    public Optional<State> execute(State currentState, Symbol symbol) {
        for (Transition transition : transitions) {
            if (transition.from().equals(currentState) && transition.on().equals(symbol)) {
                return Optional.of(transition.to());
            }
        }
        return Optional.empty();
    }
}
