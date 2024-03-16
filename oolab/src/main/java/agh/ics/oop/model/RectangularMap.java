package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

import agh.ics.oop.model.util.MapVisualizer;

public class RectangularMap extends AbstractWorldMap{

    int width;
    int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }


    @Override
    public boolean canMoveTo(Vector2d position){
        return super.canMoveTo(position) && position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height));
    }


    @Override
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
