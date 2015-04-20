package eu.rossng.automata;

import com.sun.istack.internal.NotNull;
import eu.rossng.automata.primitive.Alphabet;
import eu.rossng.automata.primitive.DeterministicDelta;
import eu.rossng.automata.primitive.State;

import java.util.Optional;
import java.util.Set;

/**
 * Simulate a deterministic finite automaton
 */
public class DeterministicFiniteAutomaton {
    @NotNull
    private final Set<State> states, accept;
    @NotNull
    private final Alphabet alphabet;
    @NotNull
    private final DeterministicDelta delta;
    @NotNull
    private Optional<State> currentState;

    public DeterministicFiniteAutomaton(Set<State> states, Alphabet alphabet, DeterministicDelta delta, State start, Set<State> accept) {
        if (!states.contains(start)) {
            throw new IllegalArgumentException("The start state must be in the set of all states.");
        } else if (!states.containsAll(accept)) {
            throw new IllegalArgumentException("The set of accept states must be a subset of the set of all states.");
        } else {
            this.states = states;
            this.alphabet = alphabet;
            this.delta = delta;
            this.currentState = Optional.of(start);
            this.accept = accept;
        }
    }
}
