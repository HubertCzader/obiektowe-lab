package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private final int grass_amount;
    private final int id;
    public Map<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int grass_amount, int id){
        this.grass_amount = grass_amount;
        this.id = id;
        placeGrass();
    }

    public void placeGrass(){
        int acc_grass = grass_amount;
        while (acc_grass > 0){
            Random rand = new Random();
            int min = 0;
            int max = (int) Math.sqrt(grass_amount * 10);
            Vector2d random_position = new Vector2d(rand.nextInt((max - min) + 1) + min, rand.nextInt((max - min) + 1) + min);
            if (!isOccupied(random_position)){
                grass.put(random_position, new Grass(random_position));
                acc_grass--;
            }
        }
    }

    public Boundary getCurrentBounds(){
        Vector2d lower_left = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upper_right = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Map<Vector2d, WorldElement> mergedMap = getElements();

        for(Vector2d key: mergedMap.keySet()){
            lower_left = lower_left.lowerLeft(key);
            upper_right = upper_right.upperRight(key);
        }
        return new Boundary(lower_left, upper_right);
    }

    public int getId(){
        return id;
    }


    @Override
    public boolean isOccupied(Vector2d position){
        return super.isOccupied(position) || grass.containsKey(position);
    }


    @Override
    public WorldElement objectAt(Vector2d position){
        if (isOccupied(position)){
            WorldElement object = super.objectAt(position);
            if (Objects.equals(object, null)){
                return grass.get(position);
            }
            return object;
        }
        return null;
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> mergedMap = new HashMap<>(grass);
        mergedMap.putAll(super.getElements());
        return mergedMap;
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        Boundary boundary = getCurrentBounds();
        return super.canMoveTo(position) && position.follows(boundary.lower_left()) && position.precedes(boundary.upper_right());
    }

}
