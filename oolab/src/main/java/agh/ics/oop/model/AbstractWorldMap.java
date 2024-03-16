package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class AbstractWorldMap implements WorldMap{
    protected Map<Vector2d, WorldElement> animals = new HashMap<>();

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position){
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }

    public boolean place(Animal animal) {
        if (animals.containsKey(animal.getPosition())){
            return false;
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }

    public boolean canMoveTo(Vector2d position){
        return !animals.containsKey(position);
    }

    public void move(Animal animal, MoveDirection direction){
        Vector2d old_position = animal.getPosition();
        animal.move(direction, this);
        animals.remove(old_position);
        animals.put(animal.position, animal);
    }

    public Map<Vector2d, WorldElement> getElements() {
        return new HashMap<>(animals);
    }
}
