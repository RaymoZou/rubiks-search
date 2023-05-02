import java.util.Scanner;

public class Solver {

    static Scanner scanner;
    static Cube cube;
    static String divider = "------------------------------";

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
                case "solve":
//                    enterCubeInfo();
                    cube = new Cube(solvedCube);
                    cube.move("F'");
                    cube.move("R'");
                    cube.move("B2");
                    cube.move("F2");
                    cube.move("D'");
                    cube.move("L2");
                    cube.move("D'");
                    cube.move("R2");
                    cube.move("D'");
                    cube.move("F2");
                    cube.move("D'");
                    cube.move("U2");
                    cube.move("B2");
                    cube.move("L'");
                    cube.move("D2");
                    cube.move("L'");
                    cube.move("B");
                    cube.move("F'");
                    cube.move("L'");
                    cube.move("U2");
                    cube.move("L2");
                    break;
                case "view":
                    cube.printFaces();
                    break;
                case "help":
                    System.out.println("solve, quit, help");
                case "quit":
                    System.exit(0);
                default:
                    System.out.println("unrecognized command, try again");
            }
        }
    }

    // TODO: add exception handling for invalid inputs
    // inputs must consist of 9 char strings
    static void enterCubeInfo() {
        cube = new Cube();
        String[] faces = {"blue", "green", "yellow", "white", "orange", "red"};
        for (int i = 0; i < faces.length; i++) {
            System.out.println("Input " + faces[i] + " face:");
            String input = scanner.nextLine();
            cube.setFace(faces[i], input.toCharArray());
//            System.out.println(divider);
        }
    }
}
