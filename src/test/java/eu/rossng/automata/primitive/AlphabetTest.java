package eu.rossng.automata.primitive;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Test the Alphabet class.
 */
public class AlphabetTest {

    Symbol a, A, epsilon;
    Alphabet alphabet1, alphabet2, alphabet3;

    @Before
    public void setUp() throws Exception {
        this.a = new Symbol("a");
        this.A = new Symbol("A");
        this.epsilon = new Symbol("\u03B5");
        this.alphabet1 = new Alphabet(new HashSet<>(Arrays.asList(this.a, this.A)));
        this.alphabet2 = new Alphabet(new HashSet<>(Arrays.asList(this.a, this.A)));
        this.alphabet3 = new Alphabet(new HashSet<>(Arrays.asList(this.a, this.A, this.epsilon)));
    }

    @Test
    public void testSymbols() throws Exception {
        assertEquals(
                "alphabet1 and alphabet2 should have same symbols",
                this.alphabet1.symbols(),
                this.alphabet2.symbols()
        );
        assertNotEquals(
                "alphabet1 and alphabet3 should not have same symbols",
                this.alphabet1.symbols(),
                this.alphabet3.symbols()
        );
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(
                "alphabet1 and alphabet2 should be equal",
                this.alphabet1,
                this.alphabet2
        );
        assertNotEquals(
                "alphabet1 and alphabet3 should not be equal",
                this.alphabet1,
                this.alphabet3
        );
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(
                "alphabet1 and alphabet2 hashCode should be equal",
                this.alphabet1.hashCode(),
                this.alphabet2.hashCode()
        );
        assertNotEquals(
                "alphabet1 and alphabet3 hashCode should not be equal",
                this.alphabet1.hashCode(),
                this.alphabet3.hashCode()
        );
    }
}