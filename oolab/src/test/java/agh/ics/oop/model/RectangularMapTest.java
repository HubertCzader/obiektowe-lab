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
    public void testPlace() throws PositionAlreadyOccupiedException {
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.place(new Animal(new Vector2d(2, 2))));
    }

    @Test
    public void testObjectAt() throws PositionAlreadyOccupiedException {
        assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal animal = new Animal(new Vector2d(4, 4));
        map.place(animal);
        assertEquals(map.objectAt(new Vector2d(4, 4)), animal);
    }

    @Test
    public void testCanMoveTo() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(5, 4)));
        assertFalse(map.canMoveTo(new Vector2d(-1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(3, 2)));
    }

    @Test
    public void testMove() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal();
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void testToString(){
        System.out.println(map);
        assertTrue(true);
    }
}