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
//                    cube.scramble("B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2");
                    cube.scramble("U2 L2 U' B U2 B2 L' D' R U2 B2 D R2 U2 L2 D' F2 R2 L2 B2 L2");
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
        float group0_threshold = root.getGroup0FVal();
        while (result == null) {
            result = IdaStarSearch_Group0(root, group0_threshold);
            group0_threshold = minPruned; // repeat IDA with new threshold
            minPruned = Integer.MAX_VALUE; // reset the minPruned value
        }
        Cube cube0 = result;
        System.out.println(cube0.currPath);

        // reset depth of cube0
        cube0.depthLevel = 0;
        cube0.currPath = "";

        result = null;
        float group1_threshold = cube0.getGroup1FVal();
        while (result == null) {
            result = IDAStarSearch_Group1(cube0, group1_threshold);
            group1_threshold = minPruned;
            minPruned = Integer.MAX_VALUE;
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
        if (node.isGroup1Goal()) return node;
        if ((node.getGroup1FVal() <= threshold)) {
            node.generateChildren();
            Cube[] children = {
                    node.UCube, node.UPrimeCube, node.RCube,
                    node.RPrimeCube, node.DCube, node.DPrimeCube,
                    node.LCube, node.LPrimeCube,
            };
            for (Cube c : children) {
                Cube result = IDAStarSearch_Group1(c, threshold);
                if (result != null) return result;
            }
        } else {
            if (node.getGroup1FVal() <= minPruned) minPruned = node.getGroup1FVal();
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
