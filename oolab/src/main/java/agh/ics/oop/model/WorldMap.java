package agh.ics.oop.model;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Map;
import java.util.UUID;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the move is not valid,
     * then an exception is raised.
     */
    boolean place(Animal animal) throws PositionAlreadyOccupiedException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return worldElement or null if the position is not occupied.
     */
    WorldElement objectAt(Vector2d position);

    /**
     * Return a boundary of the map.
     *
     * @return Boundary.
     */
    Boundary getCurrentBounds();

    /**
     * Return an id of the running map simulation.
     *
     * @return int.
     */
    int getId();

    /**
     * Return an updates amount of the running map simulation.
     *
     * @return int.
     */
    int getUpdates();

    /**
     * Return map of all objects at map.
     *
     * @return Map<Vector2d, WorldElement>.
     */
    Map<Vector2d, WorldElement> getElements();
}

