package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public State execute(State currentState, Symbol symbol) throws CannotTransitionException {
        List<State> validTransitions = transitions.stream()
                .filter(transition -> transition.from().equals(currentState) && transition.on().equals(symbol))
                .map(Transition::to)
                .collect(Collectors.toList());

        if (validTransitions.size() == 1) {
            return validTransitions.get(0);
        } else if (validTransitions.size() < 1) {
            throw new CannotTransitionException("Could not transition from " + currentState.toString() + " by symbol " + symbol.toString() + " (no valid transitions found)");
        } else {
            throw new CannotTransitionException("Could not transition from " + currentState.toString() + " by symbol " + symbol.toString() + " (more than one valid transition found)");
        }
    }

    /**
     * Given a set of States and an Alphabet, determine whether this transition function can be used to build a
     * deterministic finite automaton. This means that there must be a transition for every symbol in the alphabet from
     * every state in the set of states, and no cases where there is >1 option for a (symbol, state) pair.
     */
    public Boolean isValidFor(Set<State> states, Alphabet alphabet) {
        // first, look for any duplicate (symbol, state) pairs
        // second, check that every (symbol, state) pair has a transition
        return transitions.stream().map(transition -> new Pair<>(transition.from(), transition.on())).collect(Collectors.toSet()).size() == transitions.size()
                && states.stream().allMatch(state -> alphabet.symbols().stream().allMatch(symbol -> this.transitions.stream().anyMatch(t -> t.from().equals(state) && t.on().equals(symbol))));
    }
}
