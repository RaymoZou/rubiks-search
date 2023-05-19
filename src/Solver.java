import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solver {

    static Scanner scanner;
    static String divider = "------------------------------";

    // search
    static Cube cube;
    static int nodesSearched = 0;

    static StringBuilder currPath = new StringBuilder();
    static List<Cube> cubePath = new ArrayList<>();
    static Cube result = null;
    static ArrayList<Cube> cubeStack = new ArrayList<>();


    static char[][] solvedCube = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
    };


    public static void main(String[] args) {
        cube = new Cube(solvedCube);
//        cube.scramble("B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2");
        cube.scramble("U2 L2 U' B U2 B2 L' D' R U2 B2 D R2 U2 L2 D' F2 R2 L2 B2 L2");
        solve(cube);

//        while (true) {
//            System.out.println("Enter command:");
//            scanner = new Scanner(System.in);
//            String command = scanner.nextLine();
//            switch (command) {
//                case "scramble":
//                    cube = new Cube(solvedCube);
//                    cube.scramble("F' R");
//                    cube.scramble("B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2");
////                    cube.scramble("U2 L2 U' B U2 B2 L' D' R U2 B2 D R2 U2 L2 D' F2 R2 L2 B2 L2");
////                    cube.doR2();
//                    break;
//                case "input":
//                    enterCubeInfo();
//                case "solve":
//                    solve(cube);
//                    break;
//                case "view":
//                    cube.printFaces();
//                    break;
//                case "oriented edges":
//                    System.out.print(cube.getNonOrientatedEdges());
//                    break;
//                case "help":
//                    System.out.println("solve, quit, help");
//                case "quit":
//                    System.exit(0);
//                default:
//                    System.out.println("unrecognized command, try again");
//            }
//            System.out.println(divider);
//        }
    }

    static void solve(Cube root) {
        Cube cube1 = null;
        int maxDepth = Integer.MAX_VALUE;
        cube1 = IDDFS_Group0(root, maxDepth);

        System.out.println("GROUP 0 -> GROUP 1 STAGE COMPLETE");
        System.out.println("searched " + nodesSearched + " nodes");
        System.out.println("solution found at depth: " + cube1.depthLevel);
        System.out.println(currPath);
        System.out.println(divider);

        // GROUP 2
        cube1.lastMove = "";
        cube1.depthLevel = 0;
        currPath.setLength(0);
        nodesSearched = 0;
        cubePath.clear();

        Cube cube2 = IDDFS_Group1(cube1, maxDepth);
        System.out.println("GROUP 1 -> GROUP 2 STAGE COMPLETE");
        System.out.println("searched " + nodesSearched + " nodes");
        System.out.println("solution found at depth: " + cube2.depthLevel);
        System.out.println(currPath);
        System.out.println(divider);
    }

    // to orientate all edges
    static Cube IDDFS_Group0(Cube node, float depth) {
        for (int i = 0; i < depth; i++) {
            Cube result = DFS_Group0(node, i);
            if (result != null) return result;
            System.out.println("finished searching depth " + i);
        }
        return null;
    }

    static Cube DFS_Group0(Cube node, float depth) {
        nodesSearched++;
        if (depth == 0) {
            if (node.isGroup0Goal()) {
                cubePath.add(node);
                for (Cube cube : cubePath) {
                    currPath.append(cube.lastMove);
                }
                return node;
            } else {
                return null;
            }
        }
        cubePath.add(node);
//        node.generateChildren();
//        Cube[] children = {
//                node.UCube, node.UPrimeCube, node.U2Cube,
//                node.FCube, node.FPrimeCube, node.F2Cube,
//                node.RCube, node.RPrimeCube, node.R2Cube,
//                node.DCube, node.DPrimeCube, node.D2Cube,
//                node.LCube, node.LPrimeCube, node.L2Cube,
//                node.BCube, node.BPrimeCube, node.B2Cube
//        };
//
//        // prune redundant children
//        if (node.lastMove.equals("U") || node.lastMove.equals("U'") || node.lastMove.equals("U2")) {
//            children[0] = null;
//            children[1] = null;
//            children[2] = null;
//        }
//        if (node.lastMove.equals("F") || node.lastMove.equals("F'") || node.lastMove.equals("F2")) {
//            children[3] = null;
//            children[4] = null;
//            children[5] = null;
//        }
//        if (node.lastMove.equals("R") || node.lastMove.equals("R'") || node.lastMove.equals("R2")) {
//            children[6] = null;
//            children[7] = null;
//            children[8] = null;
//        }
//        if (node.lastMove.equals("D") || node.lastMove.equals("D'") || node.lastMove.equals("D2")) {
//            children[9] = null;
//            children[10] = null;
//            children[11] = null;
//        }
//        if (node.lastMove.equals("L") || node.lastMove.equals("L'") || node.lastMove.equals("L2")) {
//            children[12] = null;
//            children[13] = null;
//            children[14] = null;
//        }
//        if (node.lastMove.equals("B") || node.lastMove.equals("B'") || node.lastMove.equals("B2")) {
//            children[15] = null;
//            children[16] = null;
//            children[17] = null;
//        }

        for (Cube c : node.getChildren()) {
            if (c != null) {
                Cube result = DFS_Group0(c, depth - 1);
                if (result != null) return result;
                c = null;
            }
        }
        if (cubePath.size() != 0) cubePath.remove(cubePath.size() - 1);
        return null;
    }

    static Cube IDDFS_Group1(Cube node, float maxDepth) {
        for (int i = 0; i < maxDepth; i++) {
            Cube result = DFS_Group1(node, i);
            if (result != null) return result;
            System.out.println("finished searching depth " + i);
        }
        return null;
    }

    static Cube DFS_Group1(Cube node, float depth) {
        nodesSearched++;
        if (depth == 0) {
            if (node.isGroup1Goal()) {
                cubePath.add(node);
                for (Cube cube : cubePath) {
                    currPath.append(cube.lastMove);
                }
                return node;
            } else {
                return null;
            }
        }
        cubePath.add(node);
//        node.generateChildren();
//        Cube[] children = {
//                node.UCube, node.UPrimeCube, node.U2Cube,
//                null, null, node.F2Cube,
//                node.RCube, node.RPrimeCube, node.R2Cube,
//                node.DCube, node.DPrimeCube, node.D2Cube,
//                node.LCube, node.LPrimeCube, node.L2Cube,
//                null, null, node.B2Cube
//        };

//        // prune redundant children
//        if (node.lastMove.equals("U") || node.lastMove.equals("U'") || node.lastMove.equals("U2")) {
//            children[0] = null;
//            children[1] = null;
//            children[2] = null;
//        }
//        if (node.lastMove.equals("F") || node.lastMove.equals("F'") || node.lastMove.equals("F2")) {
//            children[3] = null;
//            children[4] = null;
//            children[5] = null;
//        }
//        if (node.lastMove.equals("R") || node.lastMove.equals("R'") || node.lastMove.equals("R2")) {
//            children[6] = null;
//            children[7] = null;
//            children[8] = null;
//        }
//        if (node.lastMove.equals("D") || node.lastMove.equals("D'") || node.lastMove.equals("D2")) {
//            children[9] = null;
//            children[10] = null;
//            children[11] = null;
//        }
//        if (node.lastMove.equals("L") || node.lastMove.equals("L'") || node.lastMove.equals("L2")) {
//            children[12] = null;
//            children[13] = null;
//            children[14] = null;
//        }
//        if (node.lastMove.equals("B") || node.lastMove.equals("B'") || node.lastMove.equals("B2")) {
//            children[15] = null;
//            children[16] = null;
//            children[17] = null;
//        }

        for (Cube c : node.getChildren()) {
            if (c != null) {
                Cube result = DFS_Group1(c, depth - 1);
                if (result != null) {
                    return result;
                } else {
                    c = null;
                }
            }
        }
        if (cubePath.size() != 0) cubePath.remove(cubePath.size() - 1);
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
