package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    public void testRun(){
        String[] args = new String[]{"f", "b", "b", "r", "l"};
        List<MoveDirection> directions = OptionsParser.argsToEnum(args);
        List<Vector2d> positions = List.of(new Vector2d(3,4), new Vector2d(3,4), new Vector2d(0,0));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        List<Animal> animals = simulation.animals;
        assertEquals(animals.get(0).position, new Vector2d(3, 4));
        assertEquals(animals.get(0).orientation, MapDirection.EAST);
        assertEquals(animals.get(1).position, new Vector2d(3, 3));
        assertEquals(animals.get(1).orientation, MapDirection.WEST);
        assertEquals(animals.get(2).position, new Vector2d(0, 0));
        assertEquals(animals.get(2).orientation, MapDirection.NORTH);
    }

}