package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    GrassField map = new GrassField(10, 0);

    @Test
    public void testIsOccupied(){
        map.grass.put(new Vector2d(2, 3), new Grass(new Vector2d(2, 3)));
        map.animals.put(new Vector2d(2, 2), new Animal(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void testPlace() throws PositionAlreadyOccupiedException {
        assertTrue(map.place(new Animal(new Vector2d(0, 0))));
    }

    @Test
    public void testObjectAt() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        assertEquals(map.objectAt(new Vector2d(2, 2)), animal);
        Grass grass = new Grass(new Vector2d(2, 3));
        map.grass.put(new Vector2d(2, 3), grass);
        assertEquals(map.objectAt(new Vector2d(2, 3)), grass);
    }

    @Test
    public void testCanMoveTo() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(3, 2)));
    }

    @Test
    public void testMove() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal();
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void testToString(){
        System.out.println(map);
        assertTrue(true);
    }

}