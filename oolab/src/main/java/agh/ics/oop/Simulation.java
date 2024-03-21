package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;


public class Simulation implements Runnable{
    public final List<Animal> animals;
    private final int animals_amount;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    private int currentAnimalIndex = 0;

    public Simulation(List<Vector2d> start_positions, List<MoveDirection> moves, WorldMap map){
        this.animals = new ArrayList<>();
        this.animals_amount = start_positions.size();
        this.map = map;
        this.moves = moves;
        for (Vector2d position: start_positions){
            Animal animal = new Animal(position);
            animals.add(animal);
        }
    }

    @Override
    public void run(){
        for (Animal animal:animals){
            try {
                map.place(animal);
            }
            catch (PositionAlreadyOccupiedException ignored){

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (MoveDirection direction: moves){
            map.move(animals.get(currentAnimalIndex), direction);
            getNextAnimalIndex();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getNextAnimalIndex() {
        currentAnimalIndex++;
        if (currentAnimalIndex % animals_amount == 0) {
            currentAnimalIndex = 0;
        }
    }
}
