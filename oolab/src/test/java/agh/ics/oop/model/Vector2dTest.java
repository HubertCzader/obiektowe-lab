package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;


class Vector2dTest {

    @Test
    public void testEquals(){
        Vector2d Object = new Vector2d(2, 1);
        assertEquals(Object, new Vector2d(2, 1));
        assertNotEquals(Object, new Vector2d(1, 0));
    }

    @Test
    public void testToString(){
        assertEquals(Objects.toString(new Vector2d(2, 1)), "(2,1)");
    }

    @Test
    public void testPrecedes(){
        assertTrue(new Vector2d(2, 1).precedes(new Vector2d(3, 3 )));
        assertTrue(new Vector2d(2, 1).precedes(new Vector2d(2, 1 )));
        assertFalse(new Vector2d(2, 1).precedes(new Vector2d(1, 0 )));
    }

    @Test
    public void testFollows(){
        assertTrue(new Vector2d(2, 1).follows(new Vector2d(1, 0 )));
        assertTrue(new Vector2d(2, 1).follows(new Vector2d(2, 1 )));
        assertFalse(new Vector2d(2, 1).follows(new Vector2d(3, 3 )));
    }

    @Test
    public void testUpperRight(){
        assertEquals(new Vector2d(2, 1).upperRight(new Vector2d(1, 2)), new Vector2d(2, 2));
        assertNotEquals(new Vector2d(3, 3).upperRight(new Vector2d(1, 4)), new Vector2d(3, 3));
    }

    @Test
    public void testLowerLeft(){
        assertEquals(new Vector2d(3, 3).lowerLeft(new Vector2d(1, 4)), new Vector2d(1, 3));
        assertNotEquals(new Vector2d(2, 1).lowerLeft(new Vector2d(1, 2)), new Vector2d(2, 2));
    }

    @Test
    public void add(){
        assertEquals(new Vector2d(3, 3).add(new Vector2d(1, 4)), new Vector2d(4, 7));
        assertNotEquals(new Vector2d(2, 1).add(new Vector2d(1, 2)), new Vector2d(2, 2));
    }

    @Test
    public void subtract(){
        assertEquals(new Vector2d(3, 3).subtract(new Vector2d(1, 4)), new Vector2d(2, -1));
        assertNotEquals(new Vector2d(2, 1).subtract(new Vector2d(1, 2)), new Vector2d(2, 2));
    }

    @Test
    public void opposite(){
        assertEquals(new Vector2d(-2, 1).opposite(), new Vector2d(2, -1));
        assertNotEquals(new Vector2d(-3, 3).opposite(), new Vector2d(3, 3));
    }
}