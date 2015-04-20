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

    public State execute(State currentState, Symbol symbol) throws CannotTransitionException {
        for (Transition transition : transitions) {
            if (transition.from().equals(currentState) && transition.on().equals(symbol)) {
                return transition.to();
            }
        }
        throw new CannotTransitionException("Could not transition from " + currentState.toString() + " by symbol " + symbol.toString());
    }

    public Boolean isCompleteFor(Set<State> states, Alphabet alphabet) {
        return states.stream().allMatch(state -> alphabet.symbols().stream()
                        .allMatch(symbol -> this.transitions.stream().anyMatch(t -> t.from().equals(state) && t.on().equals(symbol)))
        );
    }

    public class CannotTransitionException extends Exception {
        public CannotTransitionException(String message) {
            super(message);
        }
    }
}
