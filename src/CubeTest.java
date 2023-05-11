import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

public class CubeTest {

    public static char G = 'G';
    public static char B = 'B';
    public static char W = 'W';
    public static char Y = 'Y';
    public static char O = 'O';
    public static char R = 'R';


    public String sampleScramble = "F' R' B2 F2 D' L2 D' R2 D' F2 D' U2 B2 L' D2 L' B F' L' U2 L2";
    private final float floatComparisonDelta = 0.001F;

    static final char[][] sampleState = {
            {Y, W, G, O, G, R, W, G, G}, // green
            {B, Y, R, O, B, Y, Y, B, O}, // blue
            {W, R, Y, B, W, B, R, R, W}, // white
            {O, Y, O, R, Y, G, G, O, R}, // yellow
            {B, W, G, O, O, G, W, G, B}, // orange
            {R, Y, O, B, R, W, Y, W, B}, // red
    };
    static final char[][] solvedCube = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
    };

    @Before
    public void setUp() {
        Cube cube = new Cube(solvedCube);
    }

    @Test
    public void testConstructor() {
        Cube cube = new Cube();
        assertNull(cube.getFace("green"));
        assertNull(cube.getFace("blue"));
        assertNull(cube.getFace("white"));
        assertNull(cube.getFace("yellow"));
        assertNull(cube.getFace("red"));
        assertNull(cube.getFace("orange"));
    }

    @Test
    public void testMultipleMoves() {
        Cube cube = new Cube(solvedCube);
        cube.scramble(sampleScramble);
        assertArrayEquals(sampleState[0], cube.getFace("green"));
        assertArrayEquals(sampleState[1], cube.getFace("blue"));
        assertArrayEquals(sampleState[2], cube.getFace("white"));
        assertArrayEquals(sampleState[3], cube.getFace("yellow"));
        assertArrayEquals(sampleState[4], cube.getFace("orange"));
        assertArrayEquals(sampleState[5], cube.getFace("red"));
    }

    @Test
    public void testEdgeOrientation() {
        Cube cube = new Cube(solvedCube);
        cube.generateChildren();
        assertEquals(0, cube.getGroup0Heuristic(), floatComparisonDelta);
        assertEquals(0, cube.RCube.getGroup0Heuristic(), floatComparisonDelta);
        assertEquals(1, cube.FCube.getGroup0Heuristic(), floatComparisonDelta);
        assertEquals(1, cube.FPrimeCube.getGroup0Heuristic(), floatComparisonDelta);
        assertEquals(1, cube.BCube.getGroup0Heuristic(), floatComparisonDelta);
        assertEquals(1, cube.BPrimeCube.getGroup0Heuristic(), floatComparisonDelta);
    }

    @Test
    public void testChildren() {
        Cube cube = new Cube(sampleState);
        cube.generateChildren();
        assertArrayEquals(new char[]{R, Y, O, O, G, R, W, G, G,}, cube.UCube.getFace("green"));
        assertEquals(1, cube.RCube.depthLevel);
        cube.RCube.generateChildren();
        assertEquals(2, cube.RCube.LCube.depthLevel);
    }
}
