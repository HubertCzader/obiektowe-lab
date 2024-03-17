package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;


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

        List<Simulation> simulations = generate_simulations(100, directions);

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runSync();
        System.out.println();
        simulationEngine.runAsync();
        System.out.println();
        simulationEngine.runAsyncInThreadPool();
        System.out.println("\nSystem zakończył działanie\n");
    }


    public static List<Simulation> generate_simulations(int amount, List<MoveDirection> directions){
        List <Simulation> simulations = new ArrayList<>();
        ConsoleMapDisplay monitor = new ConsoleMapDisplay();
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Random random = new Random();
        for (int i=0; i<amount; i++){
            int random_number = random.nextInt(2);
            AbstractWorldMap map;
            if (random_number == 0) {
                map = new RectangularMap(5, 6, i);
            }
            else {
                map = new GrassField(10, i);
            }
            map.attachObserver(monitor);
            simulations.add(new Simulation(positions, directions, map));
        }
        return simulations;
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
