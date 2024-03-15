package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import agh.ics.oop.model.util.MapVisualizer;

public class RectangularMap implements WorldMap{

    Map<Vector2d, Animal> animals = new HashMap<>();
    int width;
    int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public boolean place(Animal animal){
        if (isOccupied(animal.position)){
            return false;
        }
        animals.put(animal.position, animal);
        return true;
    }

    public Animal objectAt(Vector2d position){
        if (isOccupied(position)){
            return animals.get(position);
        }
        return null;
    }

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position) && position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height));
    }

    public void move(Animal animal, MoveDirection direction){
        Vector2d old_position = animal.position;
        animal.move(direction, this);
        animals.remove(old_position);
        animals.put(animal.position, animal);
    }

    @Override
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
