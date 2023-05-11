import java.util.Scanner;

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
                    cube.scramble("B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2");
//                    cube.scramble(Cube.sampleScramble);
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
                    System.out.print(cube.getNonOrientatedEdges());
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
        float threshold = root.getGroup0FVal();
        while (result == null) {
            result = IdaStarSearch_Group0(root, threshold);
            threshold = minPruned; // repeat IDA with new threshold
            minPruned = Integer.MAX_VALUE; // reset the minPruned value
        }
        Cube cube1 = result;
        System.out.println(cube1.currPath);
    }

    // to orientate all edges
    static Cube IdaStarSearch_Group0(Cube node, float threshold) {
        if (node.isGroup0Goal()) return node;
        if ((node.getGroup0FVal() <= threshold)) {
            // expand
            node.generateChildren();
            Cube[] children = {
                    node.UCube, node.UPrimeCube, node.FCube,
                    node.FPrimeCube, node.RCube, node.RPrimeCube,
                    node.DCube, node.DPrimeCube, node.LCube,
                    node.LPrimeCube, node.BCube, node.BPrimeCube,
            };
            for (Cube c : children) {
                Cube result = IdaStarSearch_Group0(c, threshold);
                if (result != null) return result;
            }
        } else {
            if (node.getGroup0FVal() <= minPruned) minPruned = node.getGroup0FVal();
        }
        return null;
    }

    // to solve opposite cross edges
    static Cube IDAStarSearch_Group1(Cube node, float threshold) {
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