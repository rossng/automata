package eu.rossng.automata;

import com.sun.istack.internal.NotNull;
import eu.rossng.automata.primitive.Alphabet;
import eu.rossng.automata.primitive.DeterministicDelta;
import eu.rossng.automata.primitive.State;
import eu.rossng.automata.primitive.Symbol;

import java.util.List;
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
    private State start;

    public DeterministicFiniteAutomaton(Set<State> states, Alphabet alphabet, DeterministicDelta delta, State start, Set<State> accept) {
        if (!states.contains(start)) {
            throw new IllegalArgumentException("The start state must be in the set of all states.");
        } else if (!states.containsAll(accept)) {
            throw new IllegalArgumentException("The set of accept states must be a subset of the set of all states.");
        } else {
            this.states = states;
            this.alphabet = alphabet;
            this.delta = delta;
            this.start = start;
            this.accept = accept;
        }
    }

    public Boolean acceptsWord(List<Symbol> word) {
        State currentState = this.start;
        for (Symbol symbol : word) {
            try {
                currentState = this.delta.execute(currentState, symbol);
            } catch (DeterministicDelta.CannotTransitionException e) {
                System.err.println("Failed to execute transition from " + currentState.toString() + " by symbol " + symbol.toString());
                e.printStackTrace();
            }
        }
        return this.accept.contains(currentState);
    }

}
