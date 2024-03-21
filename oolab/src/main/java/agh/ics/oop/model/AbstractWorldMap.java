package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

abstract public class AbstractWorldMap implements WorldMap{
    protected Map<Vector2d, WorldElement> animals = new HashMap<>();
    protected List<MapChangeListener> observers = new ArrayList<>();

    private int updates = 0;

    public int getUpdates(){
        updates++;
        return updates;
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position){
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }

    public boolean place(Animal animal) throws PositionAlreadyOccupiedException {
        if (animals.containsKey(animal.getPosition())){
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
        animals.put(animal.getPosition(), animal);
        mapChanged("Made action: place animal at position " + animal.getPosition());
        return true;
    }

    public boolean canMoveTo(Vector2d position){
        return !animals.containsKey(position);
    }

    public void move(Animal animal, MoveDirection direction){
        Vector2d old_position = new Vector2d(animal.getPosition());
        MapDirection old_orientation = animal.getOrientation();
        animal.move(direction, this);
        if (!old_position.equals(animal.getPosition()) || old_orientation != animal.getOrientation()){
            animals.remove(old_position);
            animals.put(animal.getPosition(), animal);
            mapChanged("Made action: move animal from position " + old_position + " to " + animal.getPosition());
        }
    }

    public Map<Vector2d, WorldElement> getElements() {
        return new HashMap<>(animals);
    }

    public void attachObserver(MapChangeListener observer){
        observers.add(observer);
    }

    public void detachObserver(MapChangeListener observer){
        observers.remove(observer);
    }

    public void mapChanged(String message){
        for (MapChangeListener observer:observers){
            observer.mapChanged(this, message);
        }
    }

    @Override
    public String toString(){
        Boundary boundary = getCurrentBounds();
        return new MapVisualizer(this).draw(boundary.lower_left(), boundary.upper_right());
    }


}
