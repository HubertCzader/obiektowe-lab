package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    RectangularMap map = new RectangularMap(4, 4);

    @Test
    public void testIsOccupied(){
        map.animals.put(new Vector2d(2, 2), new Animal(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    public void testPlace(){
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        map.place(new Animal(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void testObjectAt(){
        assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal animal = new Animal(new Vector2d(4, 4));
        map.place(animal);
        assertEquals(map.objectAt(new Vector2d(4, 4)), animal);
    }

    @Test
    public void testCanMoveTo(){
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(5, 4)));
        assertFalse(map.canMoveTo(new Vector2d(-1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(3, 2)));
    }

    @Test
    public void testMove(){
        Animal animal = new Animal();
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(animal.position, new Vector2d(2, 3));
        assertEquals(animal.orientation, MapDirection.NORTH);
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }
}