package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;


public class World {
    public static void main(String[] args){
        List<MoveDirection> directions = OptionsParser.argsToEnum(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
    }

    public static void run(List<MoveDirection> directions){
        for (MoveDirection direction:directions){
            System.out.println(direction);
        }
    }
    public static void run_old(String[] args){
        for(String argument:args){
            String message = switch(argument){
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak idzie w prawo";
                case "l" -> "Zwierzak idzie w lewo";
                default -> "Nieznana komenda";
            };
            System.out.println(message);
        }

    }
}
