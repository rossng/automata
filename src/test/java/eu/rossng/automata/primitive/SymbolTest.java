package eu.rossng.automata.primitive;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Symbol class
 */
public class SymbolTest {

    Symbol symbol1, symbol2, symbol3, symbol4;

    @Before
    public void setUp() throws Exception {
        this.symbol1 = new Symbol("a");
        this.symbol2 = new Symbol("a");
        this.symbol3 = new Symbol("A");
        this.symbol4 = new Symbol("\u03B5");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(
                "symbol1 toString should be 'a'",
                this.symbol1.toString(),
                "a"
        );
        assertEquals(
                "symbol4 toString should be '\u03B5'",
                this.symbol4.toString(),
                "\u03B5"
        );
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(
                "symbol1 should equal symbol2",
                this.symbol1,
                this.symbol2
        );
        assertNotEquals(
                "symbol1 should not equal symbol3",
                this.symbol1,
                this.symbol3
        );
        assertNotEquals(
                "symbol1 should not equal symbol4",
                this.symbol1,
                this.symbol4
        );
    }
}