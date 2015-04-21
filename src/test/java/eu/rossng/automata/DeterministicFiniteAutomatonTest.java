package eu.rossng.automata;

import eu.rossng.automata.primitive.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test the DeterministicFiniteAutomaton class
 */
public class DeterministicFiniteAutomatonTest {
    DeterministicFiniteAutomaton dfa1;
    DeterministicDelta delta1, delta2, delta3;
    Symbol a, b;
    Alphabet alphabet1;
    State q0, q1, q2;
    Set<State> states1, finishStates1;

    @Before
    public void setUp() {
        /* Create a DFA of the form:
        States: q0, q1, q2
        Alphabet: a, b
        Start: q0
        Accept: q0, q2

        Transitions:
            on a:
            q0 -> q1
            q1 -> q2
            q2 -> q0
            on b:
            q0 -> q2
            q2 -> q1
            q1 -> q0
         */

        this.a = new Symbol("a");
        this.b = new Symbol("b");
        this.alphabet1 = new Alphabet(new HashSet<>(Arrays.asList(a, b)));

        this.q0 = new State("q0");
        this.q1 = new State("q1");
        this.q2 = new State("q2");
        this.states1 = new HashSet<>(Arrays.asList(this.q0, this.q1, this.q2));
        this.finishStates1 = new HashSet<>(Arrays.asList(this.q0, this.q2));

        this.delta1 = new DeterministicDelta(new HashSet<>(Arrays.asList(
                new DeterministicTransition(this.q0, this.q1, this.a),
                new DeterministicTransition(this.q1, this.q2, this.a),
                new DeterministicTransition(this.q2, this.q0, this.a),
                new DeterministicTransition(this.q0, this.q2, this.b),
                new DeterministicTransition(this.q2, this.q1, this.b),
                new DeterministicTransition(this.q1, this.q0, this.b)
        )));

        this.dfa1 = new DeterministicFiniteAutomaton(states1, alphabet1, delta1, this.q0, this.finishStates1);
    }

    @Test
    public void testAcceptsWord() {
        assertTrue(
                "dfa1 should accept ''",
                this.dfa1.acceptsWord(Collections.emptyList())
        );
        assertTrue(
                "dfa1 should accept 'ba'",
                this.dfa1.acceptsWord(Arrays.asList(this.b, this.a))
        );
        assertTrue(
                "dfa1 should accept 'babba'",
                this.dfa1.acceptsWord(Arrays.asList(this.b, this.a, this.b, this.b, this.a))
        );
        assertFalse(
                "dfa1 should not accept 'a'",
                this.dfa1.acceptsWord(Collections.singletonList(this.a))
        );
        assertFalse(
                "dfa1 should not accept 'bb'",
                this.dfa1.acceptsWord(Arrays.asList(this.b, this.b))
        );
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    /**
     * Check that DeterministicFiniteAutomaton::acceptsWord throws a NotInAlphabetException if it encounters a symbol
     * that is not in the machine's alphabet
     */
    public void testAcceptsWordThrowsNotInAlphabetException() throws Exception {
        exception.expect(NotInAlphabetException.class);
        this.dfa1.acceptsWord(Arrays.asList(this.b, this.a, new Symbol("c")));
    }
}