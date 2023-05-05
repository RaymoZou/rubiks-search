import org.junit.Test;
import static org.junit.Assert.*;

public class CubeTest {

    public static char G = 'G';
    public static char B = 'B';
    public static char W = 'W';
    public static char Y = 'Y';
    public static char O = 'O';
    public static char R = 'R';


    public String sampleScramble = "F' R' B2 F2 D' L2 D' R2 D' F2 D' U2 B2 L' D2 L' B F' L' U2 L2";
    static char[][] solvedCube = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
    };

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
        assertArrayEquals(new char[]{Y, W, G, O, G, R, W, G, G}, cube.getFace("green"));
        assertArrayEquals(new char[]{B, Y, R, O, B, Y, Y, B, O}, cube.getFace("blue"));
        assertArrayEquals(new char[]{W, R, Y, B, W, B, R, R, W}, cube.getFace("white"));
        assertArrayEquals(new char[]{O, Y, O, R, Y, G, G, O, R}, cube.getFace("yellow"));
        assertArrayEquals(new char[]{R, Y, O, B, R, W, Y, W, B}, cube.getFace("red"));
        assertArrayEquals(new char[]{B, W, G, O, O, G, W, G, B}, cube.getFace("orange"));
    }

//    @Test
//    public void testChildren() {
//        Cube cube = new Cube(solvedCube);
//        cube.scramble(sampleScramble);
//    }


}
