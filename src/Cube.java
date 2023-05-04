public class Cube {

    private char[] green = new char[9];
    private char[] blue = new char[9];
    private char[] white = new char[9];
    private char[] yellow = new char[9];
    private char[] orange = new char[9];
    private char[] red = new char[9];

    static public String sampleScramble = "F' R' B2 F2 D' L2 D' R2 D' F2 D' U2 B2 L' D2 L' B F' L' U2 L2";

    // edges
    public char[] UB;
    public char[] UR = {white[1], red[1]};
    public char[] UF = {white[1], green[1]};
    public char[] UL = {white[1], orange[1]};
    public char[] FR = {green[5], red[3]};
    public char[] FL = {green[3], orange[5]};
    public char[] BR = {blue[3], red[5]};
    public char[] BL = {blue[5], orange[3]};
    public char[] FD = {green[7], yellow[1]};
    public char[] RD = {red[7], yellow[5]};
    public char[] BD = {blue[7], yellow[7]};
    public char[] LD = {orange[7], yellow[3]};


    private char[][] edges = new char[12][2];



    enum Color {
        G, B, W, Y, O, R,
    }

//    private char[][] faces = {green, blue, white, yellow, orange, red};

    public Cube() {
        this.green = null;
        this.blue = null;
        this.white = null;
        this.yellow = null;
        this.orange = null;
        this.red = null;
    }

    public Cube(char[][] faces) {
        green = faces[0];
        blue = faces[1];
        white = faces[2];
        yellow = faces[3];
        orange = faces[4];
        red = faces[5];

        populateEdges();
    }

    // all moves are assuming green front, white top orientation
    public void move(String actions) {
        String[] moves = actions.split("\\s+");
        for (String move : moves) {
            switch (move) {
                case "U" -> doU();
                case "U'" -> {
                    doU();
                    doU();
                    doU();
                }
                case "U2" -> {
                    doU();
                    doU();
                }
                case "F" -> doF();
                case "F'" -> {
                    doF();
                    doF();
                    doF();
                }
                case "F2" -> {
                    doF();
                    doF();
                }
                case "R" -> doR();
                case "R'" -> {
                    doR();
                    doR();
                    doR();
                }
                case "R2" -> {
                    doR();
                    doR();
                }
                case "L" -> doL();
                case "L'" -> {
                    doL();
                    doL();
                    doL();
                }
                case "L2" -> {
                    doL();
                    doL();
                }
                case "B" -> doB();
                case "B'" -> {
                    doB();
                    doB();
                    doB();
                }
                case "B2" -> {
                    doB();
                    doB();
                }
                case "D" -> doD();
                case "D'" -> {
                    doD();
                    doD();
                    doD();
                }
                case "D2" -> {
                    doD();
                    doD();
                }
            }
        }
        populateEdges();
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
        FD = new char[]{green[7], yellow[1]};
        RD = new char[]{red[7], yellow[5]};
        BD = new char[]{blue[7], yellow[7]};
        LD = new char[]{orange[7], yellow[3]};
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
    }

    public void doU() {
        String mainFace = "white";
        String[] sideFaces = {"green", "red", "blue", "orange"};
        int[][] changeIndices = {{0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doF() {
        String mainFace = "green";
        String[] sideFaces = {"yellow", "red", "white", "orange"};
        int[][] changeIndices = {{0, 1, 2}, {6, 3, 0}, {8, 7, 6}, {2, 5, 8}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doR() {
        String mainFace = "red";
        String[] sideFaces = {"green", "yellow", "blue", "white"};
        int[][] changeIndices = {{2, 5, 8}, {2, 5, 8}, {6, 3, 0}, {2, 5, 8}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doL() {
        String mainFace = "orange";
        String[] sideFaces = {"green", "white", "blue", "yellow"};
        int[][] changeIndices = {{0, 3, 6}, {0, 3, 6}, {8, 5, 2}, {0, 3, 6}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doB() {
        String mainFace = "blue";
        String[] sideFaces = {"white", "red", "yellow", "orange"};
        int[][] changeIndices = {{0, 1, 2}, {2, 5, 8}, {8, 7, 6}, {6, 3, 0}};
        turn(mainFace, sideFaces, changeIndices);
    }

    public void doD() {
        String mainFace = "yellow";
        String[] sideFaces = {"green", "orange", "blue", "red"};
        int[][] changeIndices = {{6, 7, 8}, {6, 7, 8}, {6, 7, 8}, {6, 7, 8}};
        turn(mainFace, sideFaces, changeIndices);
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