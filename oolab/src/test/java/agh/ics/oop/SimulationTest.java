package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    public void testRun(){
        String[] args = new String[]{"f", "b", "b", "r", "l"};
        List<MoveDirection> directions = OptionsParser.argsToEnum(args);
        List<Vector2d> positions = List.of(new Vector2d(3,4), new Vector2d(3,3), new Vector2d(0,0));
        RectangularMap map = new RectangularMap(4, 4, 0);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.animals;
        assertEquals(animals.get(0).getPosition(), new Vector2d(3, 4));
        assertEquals(animals.get(0).getOrientation(), MapDirection.EAST);
        assertEquals(animals.get(1).getPosition(), new Vector2d(3, 2));
        assertEquals(animals.get(1).getOrientation(), MapDirection.WEST);
        assertEquals(animals.get(2).getPosition(), new Vector2d(0, 0));
        assertEquals(animals.get(2).getOrientation(), MapDirection.NORTH);
    }

}