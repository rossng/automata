package eu.rossng.automata;

import com.sun.istack.internal.NotNull;
import eu.rossng.automata.primitive.*;

import java.util.List;
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
        } else if (!delta.isValidFor(states, alphabet)) {
            throw new IllegalArgumentException("The set of transitions does not cover every case.");
        } else {
            this.states = states;
            this.alphabet = alphabet;
            this.delta = delta;
            this.start = start;
            this.accept = accept;
        }
    }

    public Boolean acceptsWord(List<Symbol> word) throws NotInAlphabetException {
        State currentState = this.start;
        for (Symbol symbol : word) {
            if (!this.alphabet.symbols().contains(symbol)) {
                throw new NotInAlphabetException("The symbol '" + symbol.toString() + "' is not in the alphabet of this automaton");
            }
            try {
                currentState = this.delta.execute(currentState, symbol);
            } catch (CannotTransitionException e) {
                System.err.println("Failed to execute transition from " + currentState.toString() + " by symbol " + symbol.toString());
                e.printStackTrace();
            }
        }
        return this.accept.contains(currentState);
    }

}
