package eu.rossng.automata.primitive;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Transition class
 */
public class TransitionTest {

    Transition transition1, transition2, transition3, transition4, transition5;
    State state1, state2, state3;
    Symbol a, A;

    @Before
    public void setUp() throws Exception {
        this.a = new Symbol("a");
        this.A = new Symbol("A");

        this.state1 = new State("state1");
        this.state2 = new State("state2");
        this.state3 = new State("state3");

        this.transition1 = new Transition(this.state1, this.state2, this.a);
        this.transition2 = new Transition(this.state1, this.state2, this.a);
        this.transition3 = new Transition(this.state1, this.state2, this.A);
        this.transition4 = new Transition(this.state1, this.state3, this.a);
        this.transition5 = new Transition(this.state3, this.state2, this.a);
    }

    @Test
    public void testFrom() throws Exception {
        assertEquals(
                "transition1 from() should equal state1",
                this.transition1.from(),
                this.state1
        );
        assertNotEquals(
                "transition1 from() should not equal state2",
                this.transition1.from(),
                this.state2
        );
    }

    @Test
    public void testTo() throws Exception {
        assertEquals(
                "transition1 to() should equal state2",
                this.transition1.to(),
                this.state2
        );
        assertNotEquals(
                "transition1 to() should not equal state3",
                this.transition1.to(),
                this.state3
        );
    }

    @Test
    public void testOn() throws Exception {
        assertEquals(
                "transition1 on() should equal a",
                this.transition1.on(),
                this.a
        );
        assertNotEquals(
                "transition1 on() should not equal A",
                this.transition1.on(),
                this.A
        );
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(
                "transition1 should equal transition2",
                this.transition1,
                this.transition2
        );
        assertNotEquals(
                "transition1 should not equal transition3 (different on())",
                this.transition1,
                this.transition3
        );
        assertNotEquals(
                "transition1 should not equal transition4 (different to())",
                this.transition1,
                this.transition4
        );
        assertNotEquals(
                "transition1 should not equal transition5 (different from())",
                this.transition1,
                this.transition5
        );
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(
                "transition1 hashCode should equal transition2",
                this.transition1.hashCode(),
                this.transition2.hashCode()
        );
        assertNotEquals(
                "transition1 hashCode should not equal transition3 (different on())",
                this.transition1.hashCode(),
                this.transition3.hashCode()
        );
        assertNotEquals(
                "transition1 hashCode should not equal transition4 (different to())",
                this.transition1.hashCode(),
                this.transition4.hashCode()
        );
        assertNotEquals(
                "transition1 hashCode should not equal transition5 (different from())",
                this.transition1.hashCode(),
                this.transition5.hashCode()
        );
    }
}