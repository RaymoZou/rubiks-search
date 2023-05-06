import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Solver {

    static Scanner scanner;
    static String divider = "------------------------------";

    // search
    static Cube cube;

    static float minPruned = Integer.MAX_VALUE;
    int fVal = 0;
    int SEARCH_THRESHOLD = 0;



    static char[][] solvedCube = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
    };


    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command:");
            scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "scramble":
                    cube = new Cube(solvedCube);
//                    cube.scramble("U L'");
                    cube.scramble(Cube.sampleScramble);
//                    cube.move("R2 L2 U2 D2 B2 F'");
                    break;
                case "input":
                    enterCubeInfo();
                case "solve":
                    solve(cube);
                    break;
                case "view":
                    cube.printFaces();
                    break;
                case "oriented edges":
                    System.out.print(cube.nonorientedEdges);
                    break;
                case "help":
                    System.out.println("solve, quit, help");
                case "quit":
                    System.exit(0);
                default:
                    System.out.println("unrecognized command, try again");
            }
            System.out.println(divider);
        }
    }

    static void solve(Cube root) {
        Cube result = null;
        float threshold = root.getFVal();
        while (result == null) {
            result = IDAStarSearch(root, threshold);
            threshold = minPruned; // repeat IDA with new threshold
            minPruned = Integer.MAX_VALUE; // reset the minPruned value
        }
        int x = 5;
    }

    static Cube IDAStarSearch(Cube node, float threshold) {
        if (node.nonorientedEdges == 0) return node;
        if ((node.getFVal() <= threshold)) {
            // expand
            node.generateChildren();
            Cube[] children = {
                    node.UCube, node.UPrimeCube, node.FCube,
                    node.FPrimeCube, node.RCube, node.RPrimeCube,
                    node.DCube, node.DPrimeCube, node.LCube,
                    node.LPrimeCube, node.BCube, node.BPrimeCube,
            };
            for (Cube c : children) {
                Cube result = IDAStarSearch(c, threshold);
                if (result != null) return result;
            }
        } else {
            Cube newCube = node;
            if (node.getFVal() <= minPruned) minPruned = node.getFVal();
        }
        return null;
    }


    // TODO: add exception handling for invalid inputs
    // inputs must consist of 9 char strings
    static void enterCubeInfo() {
        cube = new Cube();
        String[] faces = {"blue", "green", "yellow", "white", "orange", "red"};
        for (String face : faces) {
            System.out.println("Input " + face + " face:");
            String input = scanner.nextLine();
            cube.setFace(face, input.toCharArray());
        }
    }
}
