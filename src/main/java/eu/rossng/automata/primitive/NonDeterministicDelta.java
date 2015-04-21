package eu.rossng.automata.primitive;

import com.sun.istack.internal.NotNull;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Defines a set of transitions.
 */
public class NonDeterministicDelta {
    @NotNull
    private final HashSet<NonDeterministicTransition> transitions;

    public NonDeterministicDelta(@NotNull Set<NonDeterministicTransition> transitions) {
        this.transitions = new HashSet<>(transitions);
    }

    public HashSet<NonDeterministicTransition> transitions() {
        return transitions;
    }

    public Set<State> execute(Set<State> currentStates, Optional<Symbol> symbol) {
        // If we still have a symbol to spend:
        return symbol.map(s -> {
            // First, recursively get of a list of every state that can be reached by spending the symbol
            Set<State> states = this.execute(
                    transitions.stream()
                            .filter(transition -> currentStates.contains(transition.from())
                                    && transition.on().isPresent()
                                    && transition.on().map(on -> on.equals(s)).orElse(false))
                            .map(NonDeterministicTransition::to)
                            .collect(Collectors.toSet()),
                    Optional.empty()
            );
            // Now concatenate the recursively-generated list of every state that can be reached by waiting to spend the symbol
            // and using a free transition first
            states.addAll(
                this.execute(
                        transitions.stream()
                                .filter(transition -> currentStates.contains(transition.from())
                                        && !transition.on().isPresent())
                                .map(NonDeterministicTransition::to)
                                .collect(Collectors.toSet()),
                        symbol
                )
            );
            // Now concatenate the list of states that can be reached by just making one non-free transition and stopping
            // (even if it's possible to visit more states). Because there's still a symbol left to spend, we must exclude
            // free transitions
            states.addAll(
                    transitions.stream()
                            .filter(transition -> currentStates.contains(transition.from())
                                    && transition.on().isPresent()
                                    && transition.on().map(on -> on.equals(s)).orElse(false))
                            .map(NonDeterministicTransition::to)
                            .collect(Collectors.toSet())
            );
            return states;
        }
        // If there is not symbol left to spend, return only the list of States that can be reached by free transitions
        ).orElseGet(() -> {
            // First, recursively get a list of all states that can be reached by recursively using free transitions
            Set<State> states = this.execute(
                    transitions.stream()
                            .filter(transition -> currentStates.contains(transition.from())
                                    && !transition.on().isPresent())
                            .map(NonDeterministicTransition::to)
                            .collect(Collectors.toSet()),
                    symbol
            );
            // Second, concatenate the list of all states that can be reach by only making one free transition
            states.addAll(
                    transitions.stream()
                            .filter(transition -> currentStates.contains(transition.from())
                                    && !transition.on().isPresent())
                            .map(NonDeterministicTransition::to)
                            .collect(Collectors.toList())
            );
            return states;
        });
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
