package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{

    private int updates = 0;

    public void mapChanged(WorldMap worldMap, String message){
        System.out.println(message);
        updates++;
        System.out.println("Total updates amount: " + updates);
        System.out.println("Current map state: ");
        System.out.println(worldMap);
    }
}
