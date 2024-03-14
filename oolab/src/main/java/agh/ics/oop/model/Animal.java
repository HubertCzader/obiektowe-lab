package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    public MapDirection orientation = MapDirection.NORTH;
    public Vector2d position;

    public Animal(){
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.position = position;
    }

    public boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }

    @Override
    public String toString(){
        return String.valueOf(position) + ", " + String.valueOf(orientation);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d move_vector = switch(this.orientation){
                    case NORTH -> Objects.equals(direction, MoveDirection.BACKWARD) ? new Vector2d(0, -1) : new Vector2d(0, 1);
                    case SOUTH -> Objects.equals(direction, MoveDirection.BACKWARD) ? new Vector2d(0, 1) : new Vector2d(0, -1);
                    case WEST -> Objects.equals(direction, MoveDirection.BACKWARD) ? new Vector2d(1, 0) : new Vector2d(-1, 0);
                    case EAST -> Objects.equals(direction, MoveDirection.BACKWARD) ? new Vector2d(-1, 0) : new Vector2d(1, 0);
                };
                if (position.add(move_vector).follows(new Vector2d(0, 0)) && position.add(move_vector).precedes(new Vector2d(4, 4))){
                    position = position.add(move_vector);
                }
            }
        }
    }
}
