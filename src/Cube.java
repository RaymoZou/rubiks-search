public class Cube {

    // faces
    private char[] green;
    private char[] blue;
    private char[] white;
    private char[] yellow;
    private char[] orange;
    private char[] red;

    // edges
    public char[] UB;
    public char[] UR;
    public char[] UF;
    public char[] UL;
    public char[] FR;
    public char[] FL;
    public char[] BR;
    public char[] BL;
    public char[] DF;
    public char[] DR;
    public char[] DB;
    public char[] DL;


    static public String sampleScramble = "F' R' B2 F2 D' L2 D' R2 D' F2 D' U2 B2 L' D2 L' B F' L' U2 L2";
    public float nonorientedEdges;
    public static final int NUM_EDGES = 12;
    public static final int BRANCHING_FACTOR = 12;

    public int depthLevel = 0;

    public Cube UCube = null;
    public Cube UPrimeCube = null;
    public Cube FCube = null;
    public Cube FPrimeCube = null;
    public Cube RCube = null;
    public Cube RPrimeCube = null;
    public Cube LCube = null;
    public Cube LPrimeCube = null;
    public Cube DCube = null;
    public Cube DPrimeCube = null;
    public Cube BCube = null;
    public Cube BPrimeCube = null;

    public Cube() {
        this.green = null;
        this.blue = null;
        this.white = null;
        this.yellow = null;
        this.orange = null;
        this.red = null;
    }

    public Cube(char[][] faces) {
        char[] greenCopy = new char[9];
        char[] blueCopy = new char[9];
        char[] whiteCopy = new char[9];
        char[] yellowCopy = new char[9];
        char[] orangeCopy = new char[9];
        char[] redCopy = new char[9];
        System.arraycopy(faces[0], 0, greenCopy, 0, faces[0].length);
        System.arraycopy(faces[1], 0, blueCopy, 0, faces[1].length);
        System.arraycopy(faces[2], 0, whiteCopy, 0, faces[2].length);
        System.arraycopy(faces[3], 0, yellowCopy, 0, faces[3].length);
        System.arraycopy(faces[4], 0, orangeCopy, 0, faces[4].length);
        System.arraycopy(faces[5], 0, redCopy, 0, faces[5].length);
        green = greenCopy;
        blue = blueCopy;
        white = whiteCopy;
        yellow = yellowCopy;
        orange = orangeCopy;
        red = redCopy;

        populateEdges();
        updateEdgesOrientation();
    }

    public Cube(char[][] faces, int depthLevel) {
        char[] greenCopy = new char[9];
        char[] blueCopy = new char[9];
        char[] whiteCopy = new char[9];
        char[] yellowCopy = new char[9];
        char[] orangeCopy = new char[9];
        char[] redCopy = new char[9];
        System.arraycopy(faces[0], 0, greenCopy, 0, faces[0].length);
        System.arraycopy(faces[1], 0, blueCopy, 0, faces[1].length);
        System.arraycopy(faces[2], 0, whiteCopy, 0, faces[2].length);
        System.arraycopy(faces[3], 0, yellowCopy, 0, faces[3].length);
        System.arraycopy(faces[4], 0, orangeCopy, 0, faces[4].length);
        System.arraycopy(faces[5], 0, redCopy, 0, faces[5].length);
        green = greenCopy;
        blue = blueCopy;
        white = whiteCopy;
        yellow = yellowCopy;
        orange = orangeCopy;
        red = redCopy;

        populateEdges();
        updateEdgesOrientation();
        this.depthLevel = depthLevel;
    }

    public boolean isEdgeOriented(char[] edge) {
        if (edge[0] == 'R' || edge[0] == 'O') {
            return false;
        } else return edge[1] != 'W' && edge[1] != 'Y';
    }

    // 4 is the max number of edges that can become oriented with one move
    public float getEdgeHeuristic() {
        return nonorientedEdges / 4;
    }

    private void updateEdgesOrientation() {
        int edgeCount = 0;
        char[][] edges = {UB, UR, UF, UL, FR, FL, BR, BL, DF, DR, DB, DL};
        for (char[] edge : edges) {
            if (isEdgeOriented(edge)) {
                edgeCount++;
            }
        }
        nonorientedEdges = NUM_EDGES - edgeCount;
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
        populateEdges();
        updateEdgesOrientation();
    }

    private void populateEdges() {
        // U Layer Edges
        UB = new char[]{white[1], blue[1]};
        UR = new char[]{white[5], red[1]};
        UF = new char[]{white[7], green[1]};
        UL = new char[]{white[3], orange[1]};

        // Middle Edges
        FR = new char[]{green[5], red[3]};
        FL = new char[]{green[3], orange[5]};
        BR = new char[]{blue[3], red[5]};
        BL = new char[]{blue[5], orange[3]};

        // D layer Edges
        DF = new char[]{yellow[1], green[7]};
        DR = new char[]{yellow[5], red[7]};
        DB = new char[]{yellow[7], blue[7]};
        DL = new char[]{yellow[3], orange[7]};
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
        populateEdges();
        updateEdgesOrientation();
    }

    public float getFVal() {
        return depthLevel + getEdgeHeuristic();
    }

    // getCubes
    public void generateChildren() {
        UCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        UPrimeCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        FCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        FPrimeCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        BCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        BPrimeCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        RCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        RPrimeCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        LCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        LPrimeCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        DCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);
        DPrimeCube = new Cube(new char[][]{green, blue, white, yellow, orange, red}, this.depthLevel + 1);

        UCube.doU();
        UPrimeCube.doUPrime();
        FCube.doF();
        FPrimeCube.doFPrime();
        BCube.doB();
        BPrimeCube.doBPrime();
        RCube.doR();
        RPrimeCube.doRPrime();
        LCube.doL();
        LPrimeCube.doLPrime();
        DCube.doD();
        DPrimeCube.doDPrime();
    }

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