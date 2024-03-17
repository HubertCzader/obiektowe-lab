package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{


    public synchronized void mapChanged (WorldMap worldMap, String message){
        System.out.println(message);
        System.out.println("Total updates amount: " + worldMap.getUpdates());
        System.out.println("Map " + worldMap.getId() + " state: ");
        System.out.println(worldMap);
    }
}
