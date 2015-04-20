package eu.rossng.automata.primitive;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the State class
 */
public class StateTest {

    State state1, state2, state3;

    @Before
    public void setUp() {
        this.state1 = new State("foo");
        this.state2 = new State("foo");
        this.state3 = new State("bar");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testName() throws Exception {
        assertEquals(
                "Name of state1 should be 'foo'",
                this.state1.name(),
                "foo"
        );
        assertEquals(
                "Name of state3 should be 'bar'",
                this.state3.name(),
                "bar"
        );
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(
                "toString of state1 should be 'State {foo}'",
                this.state1.toString(),
                "State {foo}"
        );
        assertEquals(
                "toString of state1 should be 'State {bar}'",
                this.state3.toString(),
                "State {bar}"
        );
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(
                "state1 should equal state2",
                this.state1,
                this.state2
        );
        assertNotEquals(
                "state1 should not equal state3",
                this.state1,
                this.state3
        );
    }
}