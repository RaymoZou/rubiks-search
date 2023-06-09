import java.util.Arrays;
import java.util.Objects;

public class Cube {
    // each face is 18 bytes
    private char[] green;
    private char[] blue;
    private char[] white;
    private char[] yellow;
    private char[] orange;
    private char[] red;

//    // edges
//    public char[] UB;
//    public char[] UR;
//    public char[] UF;
//    public char[] UL;
//    public char[] FR;
//    public char[] FL;
//    public char[] BR;
//    public char[] BL;
//    public char[] DF;
//    public char[] DR;
//    public char[] DB;
//    public char[] DL;

    // corners

    public char[] UBL;
    public char[] UBR;
    public char[] UFR;
    public char[] UFL;
    public char[] DFR;
    public char[] DFL;
    public char[] DBR;
    public char[] DBL;

    public static final int NUM_EDGES = 12;

    private String lastMove = "";

    public int depthLevel = 0;

//    public Cube UCube = null;
//    public Cube UPrimeCube = null;
//    public Cube U2Cube = null;
//
//    public Cube FCube = null;
//    public Cube FPrimeCube = null;
//    public Cube F2Cube = null;
//
//    public Cube RCube = null;
//    public Cube RPrimeCube = null;
//    public Cube R2Cube = null;
//
//    public Cube LCube = null;
//    public Cube LPrimeCube = null;
//    public Cube L2Cube = null;
//
//    public Cube DCube = null;
//    public Cube DPrimeCube = null;
//    public Cube D2Cube = null;
//
//    public Cube BCube = null;
//    public Cube BPrimeCube = null;
//    public Cube B2Cube = null;

    public Cube() {
        this.green = null;
        this.blue = null;
        this.white = null;
        this.yellow = null;
        this.orange = null;
        this.red = null;
    }

    public Cube(char[][] faces) {
        green = Arrays.copyOf(faces[0], faces[0].length);
        blue = Arrays.copyOf(faces[1], faces[1].length);
        white = Arrays.copyOf(faces[2], faces[2].length);
        yellow = Arrays.copyOf(faces[3], faces[3].length);
        orange = Arrays.copyOf(faces[4], faces[4].length);
        red = Arrays.copyOf(faces[5], faces[5].length);
//        populateEdges();
        populateCorners();
    }

    public Cube(char[][] faces, int depthLevel, String path) {
        green = Arrays.copyOf(faces[0], faces[0].length);
        blue = Arrays.copyOf(faces[1], faces[1].length);
        white = Arrays.copyOf(faces[2], faces[2].length);
        yellow = Arrays.copyOf(faces[3], faces[3].length);
        orange = Arrays.copyOf(faces[4], faces[4].length);
        red = Arrays.copyOf(faces[5], faces[5].length);
//        populateEdges();
        populateCorners();

        this.depthLevel = depthLevel;
        this.lastMove = path;
    }

    public void resetLastMove() {
        lastMove = "";
    }

    public String getLastMove() {
        return lastMove;
    }

//    public float getGroup0FVal() {
//        return depthLevel + getGroup0Heuristic();
//    }
//
//    public float getGroup1FVal() {
//        return depthLevel + getGroup1Heuristic();
//    }

    public boolean isGroup0Goal() {
        return getNonOrientatedEdges() == 0;
    }

    public boolean isGroup1Goal() {
        return getUnsolvedGroup1Pieces() == 0;
    }

    public boolean isGroup2Goal() {
        return getUnsolvedGroup2Pieces() == 0;
    }

    public boolean isSolved() {
        return Arrays.equals(green, new char[]{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}) &&
                Arrays.equals(blue, new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}) &&
                Arrays.equals(white, new char[]{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}) &&
                Arrays.equals(yellow, new char[]{'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'}) &&
                Arrays.equals(orange, new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}) &&
                Arrays.equals(red, new char[]{'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'});
    }

    // 4 is the max number of edges that can become oriented with one move
//    public float getGroup0Heuristic() {
//        return getNonOrientatedEdges() / 4;
//    }
//
//    public float getGroup1Heuristic() {
//        return getUnsolvedGroup1Pieces() / 6;
//    }


    // group 0 -> group 1
    public boolean isEdgeOriented(char[] edge) {
        if (edge[0] == 'R' || edge[0] == 'O') {
            return false;
        } else return edge[1] != 'W' && edge[1] != 'Y';
    }

    // group 0
    public float getNonOrientatedEdges() {

        // create edges for easier access
        char[] UB = new char[]{white[1], blue[1]};
        char[] UR = new char[]{white[5], red[1]};
        char[] UF = new char[]{white[7], green[1]};
        char[] UL = new char[]{white[3], orange[1]};

        char[] FR = new char[]{green[5], red[3]};
        char[] FL = new char[]{green[3], orange[5]};
        char[] BR = new char[]{blue[3], red[5]};
        char[] BL = new char[]{blue[5], orange[3]};

        char[] DF = new char[]{yellow[1], green[7]};
        char[] DR = new char[]{yellow[5], red[7]};
        char[] DB = new char[]{yellow[7], blue[7]};
        char[] DL = new char[]{yellow[3], orange[7]};

        int edgeCount = 0;
        char[][] edges = {UB, UR, UF, UL, FR, FL, BR, BL, DF, DR, DB, DL};
        for (char[] edge : edges) {
            if (isEdgeOriented(edge)) {
                edgeCount++;
            }
        }
        return NUM_EDGES - edgeCount;
    }

    public float getUnsolvedGroup1Pieces() {

        char[] FL = new char[]{green[3], orange[5]};
        char[] FR = new char[]{green[5], red[3]};
        char[] BL = new char[]{blue[5], orange[3]};
        char[] BR = new char[]{blue[3], red[5]};

        int count = 0;
        char[][] corners = {UBL, UBR, UFL, UFR, DFL, DFR, DBL, DBR};
        for (char[] corner : corners) {
            if (corner[0] == 'Y' || corner[0] == 'W') count++;
        }
        char[][] edges = {FL, FR, BL, BR};
        for (char[] edge : edges) {
            if (edge[0] == 'G') {
                if (edge[1] == 'O' || edge[1] == 'R') count++;
            } else if (edge[0] == 'B') {
                if (edge[1] == 'O' || edge[1] == 'R') count++;
            }
        }
        return 12 - count;
    }

    // 8 corners and 8 edges
    public float getUnsolvedGroup2Pieces() {
        int count = 0;
        // 8 corners
        if (UFL[1] == 'G' || UFL[1] == 'B') count++;
        if (UFR[1] == 'G' || UFR[1] == 'B') count++;
        if (DFL[1] == 'G' || DFL[1] == 'B') count++;
        if (DFR[1] == 'G' || DFR[1] == 'B') count++;
        if (UBL[1] == 'G' || UBL[1] == 'B') count++;
        if (UBR[1] == 'G' || UBR[1] == 'B') count++;
        if (DBL[1] == 'G' || DBL[1] == 'B') count++;
        if (DBR[1] == 'G' || DBR[1] == 'B') count++;
        // 8 edges
        if (green[1] == 'G' || green[1] == 'B') count++;
        if (orange[1] == 'O' || orange[1] == 'R') count++;
        if (orange[7] == 'O' || orange[7] == 'R') count++;
        if (green[7] == 'G' || green[7] == 'B') count++;
        if (blue[1] == 'G' || blue[1] == 'B') count++;
        if (red[1] == 'O' || red[1] == 'R') count++;
        if (red[7] == 'O' || red[7] == 'R') count++;
        if (blue[7] == 'G' || blue[7] == 'B') count++;
        return 16 - count;
    }


    // all moves are assuming green front, white top orientation
    public void scramble(String actions) {
        String[] moves = actions.split("\\s+");
        for (String move : moves) {
            switch (move) {
                case "U" -> doU();
                case "U'" -> doUPrime();
                case "U2" -> doU2();
                case "F" -> doF();
                case "F'" -> doFPrime();
                case "F2" -> doF2();
                case "R" -> doR();
                case "R'" -> doRPrime();
                case "R2" -> doR2();
                case "L" -> doL();
                case "L'" -> doLPrime();
                case "L2" -> doL2();
                case "B" -> doB();
                case "B'" -> doBPrime();
                case "B2" -> doB2();
                case "D" -> doD();
                case "D'" -> doDPrime();
                case "D2" -> doD2();
            }
        }
//        populateEdges();
        populateCorners();
    }

//    private void populateEdges() {
//        // U Layer Edges
//        UB = new char[]{white[1], blue[1]};
//        UR = new char[]{white[5], red[1]};
//        UF = new char[]{white[7], green[1]};
//        UL = new char[]{white[3], orange[1]};
//
//        // Middle Edges
//        FR = new char[]{green[5], red[3]};
//        FL = new char[]{green[3], orange[5]};
//        BR = new char[]{blue[3], red[5]};
//        BL = new char[]{blue[5], orange[3]};
//
//        // D layer Edges
//        DF = new char[]{yellow[1], green[7]};
//        DR = new char[]{yellow[5], red[7]};
//        DB = new char[]{yellow[7], blue[7]};
//        DL = new char[]{yellow[3], orange[7]};
//    }

    private void populateCorners() {
        UBL = new char[]{white[0], blue[2], orange[0]};
        UBR = new char[]{white[2], blue[0], red[2]};
        UFL = new char[]{white[6], green[0], orange[2]};
        UFR = new char[]{white[8], green[2], red[0]};

        DFL = new char[]{yellow[0], green[6], orange[8]};
        DFR = new char[]{yellow[2], green[8], red[6]};
        DBL = new char[]{yellow[6], blue[8], orange[6]};
        DBR = new char[]{yellow[8], blue[6], red[8]};
    }

    public void turn(String mainFace, String[] sideFaces, int[][] changeIndices) {
        char[] mainFaceArr = getFace(mainFace);
        char[] sideFaceArr0 = getFace(sideFaces[0]);
        char[] sideFaceArr1 = getFace(sideFaces[1]);
        char[] sideFaceArr2 = getFace(sideFaces[2]);
        char[] sideFaceArr3 = getFace(sideFaces[3]);
        char[][] sideFaceArrays = {sideFaceArr0, sideFaceArr1, sideFaceArr2, sideFaceArr3};

        char[] mainFaceNew;

        mainFaceNew = new char[]{
                mainFaceArr[6], mainFaceArr[3], mainFaceArr[0],
                mainFaceArr[7], mainFaceArr[4], mainFaceArr[1],
                mainFaceArr[8], mainFaceArr[5], mainFaceArr[2],
        };


        char[] firstFace = new char[sideFaceArr0.length];
        System.arraycopy(sideFaceArr0, 0, firstFace, 0, firstFace.length);
        for (int i = 0; i < changeIndices.length; i++) {
            for (int k = 0; k < 3; k++) {
                if (i == changeIndices.length - 1) {
                    sideFaceArrays[i][changeIndices[i][k]] = firstFace[changeIndices[0][k]];
                } else {
                    sideFaceArrays[i][changeIndices[i][k]] = sideFaceArrays[i + 1][changeIndices[i + 1][k]];
                }
            }
        }

        setFace(mainFace, mainFaceNew);
        for (int i = 0; i < sideFaceArrays.length; i++) {
            setFace(sideFaces[i], sideFaceArrays[i]);
        }
//        populateEdges();
        populateCorners();
    }

    public Cube[] getChildren() {
        String[] moves = {"U", "U'", "U2", "F", "F'", "F2", "R", "R'", "R2", "D", "D'", "D2", "B", "B'", "B2", "L", "L'", "L2"};
        Cube[] cubes = new Cube[18];
        for (int i = 0; i < moves.length; i++) {
            Cube tempCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, depthLevel + 1, moves[i]);
            tempCube.scramble(moves[i]);
            cubes[i] = tempCube;
        }
        return cubes;
    }

    public Cube[] getChildren(String[] children) {
        Cube[] cubes = new Cube[children.length];
        for (int i = 0; i < children.length; i++) {
            Cube tempCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, depthLevel + 1, children[i]);
            tempCube.scramble(children[i]);
            cubes[i] = tempCube;
        }
        return cubes;
    }

    // getCubes
//    public void generateChildren() {
//        String[] moves = {"U", "U'", "U2", "F", "F'", "F2", "B", "B'", "B2", "R", "R'", "R2", "L", "L'", "L2", "D", "D'", "D2",};
//
//        for (String move : moves) {
//            Cube tempCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, depthLevel + 1, move);
//            tempCube.scramble(move);
//            setChild(move, tempCube);
//        }
//
//    }

//    public Cube getChild(String childName) {
//        return switch (childName) {
//            case "U" -> UCube;
//            case "U'" -> UPrimeCube;
//            case "U2" -> U2Cube;
//            case "F" -> FCube;
//            case "F'" -> FPrimeCube;
//            case "F2" -> F2Cube;
//            case "B" -> BCube;
//            case "B'" -> BPrimeCube;
//            case "B2" -> B2Cube;
//            case "R" -> RCube;
//            case "R'" -> RPrimeCube;
//            case "R2" -> R2Cube;
//            case "L" -> LCube;
//            case "L':" -> LPrimeCube;
//            case "L2" -> L2Cube;
//            case "D" -> DCube;
//            case "D'" -> DPrimeCube;
//            case "D2" -> D2Cube;
//            default -> throw new IllegalArgumentException("Invalid childName: " + childName);
//        };
//    }

//    public void setChild(String childName, Cube value) {
//        switch (childName) {
//            case "U" -> UCube = value;
//            case "U'" -> UPrimeCube = value;
//            case "U2" -> U2Cube = value;
//            case "F" -> FCube = value;
//            case "F'" -> FPrimeCube = value;
//            case "F2" -> F2Cube = value;
//            case "B" -> BCube = value;
//            case "B'" -> BPrimeCube = value;
//            case "B2" -> B2Cube = value;
//            case "R" -> RCube = value;
//            case "R'" -> RPrimeCube = value;
//            case "R2" -> R2Cube = value;
//            case "L" -> LCube = value;
//            case "L'" -> LPrimeCube = value;
//            case "L2" -> L2Cube = value;
//            case "D" -> DCube = value;
//            case "D'" -> DPrimeCube = value;
//            case "D2" -> D2Cube = value;
//            default -> throw new IllegalArgumentException("Invalid childName: " + childName);
//        }
//    }

    public void doU() {
        String mainFace = "white";
        String[] sideFaces = {"green", "red", "blue", "orange"};
        int[][] changeIndices = {{0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doU2() {
        doU();
        doU();
    }

    public void doUPrime() {
        doU();
        doU();
        doU();
    }

    public void doF() {
        String mainFace = "green";
        String[] sideFaces = {"yellow", "red", "white", "orange"};
        int[][] changeIndices = {{0, 1, 2}, {6, 3, 0}, {8, 7, 6}, {2, 5, 8}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doFPrime() {
        doF();
        doF();
        doF();
    }

    public void doF2() {
        doF();
        doF();
    }

    public void doR() {
        String mainFace = "red";
        String[] sideFaces = {"green", "yellow", "blue", "white"};
        int[][] changeIndices = {{2, 5, 8}, {2, 5, 8}, {6, 3, 0}, {2, 5, 8}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doRPrime() {
        doR();
        doR();
        doR();
    }

    public void doR2() {
        doR();
        doR();
    }

    public void doL() {
        String mainFace = "orange";
        String[] sideFaces = {"green", "white", "blue", "yellow"};
        int[][] changeIndices = {{0, 3, 6}, {0, 3, 6}, {8, 5, 2}, {0, 3, 6}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doL2() {
        doL();
        doL();
    }

    public void doLPrime() {
        doL();
        doL();
        doL();
    }

    public void doB() {
        String mainFace = "blue";
        String[] sideFaces = {"white", "red", "yellow", "orange"};
        int[][] changeIndices = {{0, 1, 2}, {2, 5, 8}, {8, 7, 6}, {6, 3, 0}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doB2() {
        doB();
        doB();
    }

    public void doBPrime() {
        doB();
        doB();
        doB();
    }

    public void doD() {
        String mainFace = "yellow";
        String[] sideFaces = {"green", "orange", "blue", "red"};
        int[][] changeIndices = {{6, 7, 8}, {6, 7, 8}, {6, 7, 8}, {6, 7, 8}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doD2() {
        doD();
        doD();
    }

    public void doDPrime() {
        doD();
        doD();
        doD();
    }

    public void setFace(String face, char[] value) {
        switch (face) {
            case "green" -> green = value;
            case "blue" -> blue = value;
            case "white" -> white = value;
            case "yellow" -> yellow = value;
            case "orange" -> orange = value;
            case "red" -> red = value;
            default -> System.out.println("not a valid face");
        }
    }

    public char[] getFace(String face) {
        switch (face) {
            case "green" -> {
                return green;
            }
            case "blue" -> {
                return blue;
            }
            case "white" -> {
                return white;
            }
            case "yellow" -> {
                return yellow;
            }
            case "orange" -> {
                return orange;
            }
            case "red" -> {
                return red;
            }
            default -> {
                System.out.println("not a valid face");
                return null;
            }
        }
    }

    public void printFaces() {
        System.out.println("green face: ");
        for (int i = 0; i < green.length; i = i + 3) {
            System.out.println(green[i] + "" + green[i + 1] + "" + green[i + 2]);
        }

        System.out.println("blue face: ");
        for (int i = 0; i < blue.length; i = i + 3) {
            System.out.println(blue[i] + "" + blue[i + 1] + "" + blue[i + 2]);
        }

        System.out.println("white face: ");
        for (int i = 0; i < white.length; i = i + 3) {
            System.out.println(white[i] + "" + white[i + 1] + "" + white[i + 2]);
        }

        System.out.println("yellow face: ");
        for (int i = 0; i < yellow.length; i = i + 3) {
            System.out.println(yellow[i] + "" + yellow[i + 1] + "" + yellow[i + 2]);
        }

        System.out.println("orange face: ");
        for (int i = 0; i < orange.length; i = i + 3) {
            System.out.println(orange[i] + "" + orange[i + 1] + "" + orange[i + 2]);
        }

        System.out.println("red face: ");
        for (int i = 0; i < red.length; i = i + 3) {
            System.out.println(red[i] + "" + red[i + 1] + "" + red[i + 2]);
        }
    }
}