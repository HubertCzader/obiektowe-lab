package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class World {
    public static void main(String[] args){
        List<MoveDirection> directions;
        try {
            directions = OptionsParser.argsToEnum(args);
        }
        catch (IllegalArgumentException exception){
            System.err.println(exception.getMessage());
            List<String> new_args = new ArrayList<>();
            for (String argument:args){
                if (Objects.equals(argument, "f") || Objects.equals(argument, "b") ||
                        Objects.equals(argument, "r") || Objects.equals(argument, "l")){
                    new_args.add(argument);
                }
            }
            directions = OptionsParser.argsToEnum((String[]) new_args.toArray());
        }
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        RectangularMap map = new RectangularMap(5, 6);
        map.attachObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);
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
