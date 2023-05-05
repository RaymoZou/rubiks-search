import java.util.ArrayList;
import java.util.Scanner;

public class Solver {

    static Scanner scanner;
    static String divider = "------------------------------";

    // search
    static Cube cube;
    ArrayList<Cube> frontier = new ArrayList<Cube>();
    int fVal = 0;



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

    static void solve(Cube cube) {
        // scrambled cube = group_0
        // oriented edges = group_1

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
