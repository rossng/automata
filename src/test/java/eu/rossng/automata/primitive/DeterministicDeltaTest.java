package eu.rossng.automata.primitive;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test the DeterministicDelta class.
 */
public class DeterministicDeltaTest {

    DeterministicDelta delta1, delta2, delta3;
    Symbol a, b;
    Alphabet alphabet1;
    State q0, q1, q2;
    Set<State> states1;

    @Before
    public void setUp() throws Exception {
        /*
        Define a partial DFA

        States: q0, q1, q2
        Alphabet: a, b

        Transitions:
            on a:
            q0 -> q1
            q1 -> q2
            q2 -> q0
            on b:
            q0 -> q2
            q2 -> q1
            q1 -> q0

        This set of transitions is valid given the set of states and the alphabet
         */

        this.a = new Symbol("a");
        this.b = new Symbol("b");
        this.alphabet1 = new Alphabet(new HashSet<>(Arrays.asList(a, b)));

        this.q0 = new State("q0");
        this.q1 = new State("q1");
        this.q2 = new State("q2");
        this.states1 = new HashSet<>(Arrays.asList(q0, q1, q2));

        delta1 = new DeterministicDelta(new HashSet<>(Arrays.asList(
                new DeterministicTransition(q0, q1, a),
                new DeterministicTransition(q1, q2, a),
                new DeterministicTransition(q2, q0, a),
                new DeterministicTransition(q0, q2, b),
                new DeterministicTransition(q2, q1, b),
                new DeterministicTransition(q1, q0, b)
        )));

        /*
        Define a partial DFA

        States: q0, q1, q2
        Alphabet: a, b

        Transitions:
            on a:
            q0 -> q1
            q1 -> q2
            q2 -> q0
            on b:
            q0 -> q2
            q1 -> q0

        This set of transitions is not valid given the set of states and the alphabet (because there is a transition missing)
         */

        delta2 = new DeterministicDelta(new HashSet<>(Arrays.asList(
                new DeterministicTransition(q0, q1, a),
                new DeterministicTransition(q1, q2, a),
                new DeterministicTransition(q2, q0, a),
                new DeterministicTransition(q0, q2, b),
                new DeterministicTransition(q1, q0, b)
        )));

        /*
        Define a partial DFA

        States: q0, q1, q2
        Alphabet: a, b

        Transitions:
            on a:
            q0 -> q1
            q1 -> q2
            q2 -> q0
            on b:
            q0 -> q2
            q2 -> q1
            q1 -> q0
            q1 -> q1

        This set of transitions is not valid given the set of states and the alphabet (because there is an extra transition)
         */

        delta3 = new DeterministicDelta(new HashSet<>(Arrays.asList(
                new DeterministicTransition(q0, q1, a),
                new DeterministicTransition(q1, q2, a),
                new DeterministicTransition(q2, q0, a),
                new DeterministicTransition(q0, q2, b),
                new DeterministicTransition(q2, q1, b),
                new DeterministicTransition(q1, q0, b),
                new DeterministicTransition(q1, q1, b)
        )));
    }

    @Test
    public void testGetTransitions() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {
        assertEquals(
                "delta1 execute(q0, a) should go to q1",
                delta1.execute(this.q0, this.a),
                this.q1
        );
        assertEquals(
                "delta1 execute(q0, b) should go to q2",
                delta1.execute(this.q0, this.b),
                this.q2
        );
        assertNotEquals(
                "delta1 execute(q0, a) should not go to q2",
                delta1.execute(this.q0, this.a),
                this.q2
        );
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testExecuteCannotTransitionExceptionWithMissingTransition() throws Exception {
        exception.expect(CannotTransitionException.class);
        delta2.execute(this.q2, this.b);
    }

    @Test
    public void testExecuteCannotTransitionExceptionWithExtraTransition() throws Exception {
        exception.expect(CannotTransitionException.class);
        delta3.execute(this.q1, this.b);
    }

    @Test
    public void throwsExceptionWithSpecificType() {
        exception.expect(NullPointerException.class);
        throw new NullPointerException();
    }

    @Test
    public void testIsValidFor() throws Exception {
        assertTrue(
                "delta1 should be valid for states1 + alphabet1",
                delta1.isValidFor(states1, alphabet1)
        );
        assertFalse(
                "delta2 should not be valid for states1 + alphabet1 (it doesn't have all possible transitions covered)",
                delta2.isValidFor(states1, alphabet1)
        );
        assertFalse(
                "delta3 should not be valid for states1 + alphabet1 (it has a duplicate - i.e. non-deterministic - transition)",
                delta3.isValidFor(states1, alphabet1)
        );
    }
}