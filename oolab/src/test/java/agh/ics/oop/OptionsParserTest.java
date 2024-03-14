package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    public void testArrayToEnum(){
        String[] args = new String[]{"f", "b", "r", "l"};
        MoveDirection[] directions_result = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT};

        assertArrayEquals(OptionsParser.argsToEnum(args).toArray(MoveDirection[]::new), directions_result);
    }

    @Test
    public void testArrayToEnum2(){
        String[] args = new String[]{"f", "b", "j", "r", "l"};
        MoveDirection[] directions_result = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals(OptionsParser.argsToEnum(args).toArray(MoveDirection[]::new), directions_result);
    }


}