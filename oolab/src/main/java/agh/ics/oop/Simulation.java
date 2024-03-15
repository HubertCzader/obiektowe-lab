package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;


public class Simulation {
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

    public void run(){
        for (MoveDirection direction: moves){
            map.move(animals.get(currentAnimalIndex), direction);
            System.out.println(map);
            getNextAnimalIndex();
        }
    }

    private void getNextAnimalIndex() {
        currentAnimalIndex++;
        if (currentAnimalIndex % animals_amount == 0) {
            currentAnimalIndex = 0;
        }
    }
}
