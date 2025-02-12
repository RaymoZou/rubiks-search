enum colors { W = 'W', O = 'O', G = 'G', R = 'R', B = 'B', Y = 'Y' };

class Cube {

public:
  // TODO: replace this with just an array of 48 chars?
  char white_face[9];
  char orange_face[9];
  char green_face[9];
  char red_face[9];
  char blue_face[9];
  char yellow_face[9];

  Cube(char white[9], char orange[9], char green[9], char red[9], char blue[9],
       char yellow[9]);

  // prints the color name
  void print_color_name(char color);

  // TODO: implement this with passing in different faces to turn()
  void doU(); // Quarter turn of the top face counter-clockwise
  void doU2(); // Half turn of the top face
  void doUPrime(); // Quarter turn of the top face clockwise
  void doD(); // Quarter turn of the bottom face clockwise
  void doD2(); // Half turn of the bottom face
  void doDPrime(); // Quarter turn of the bottom face counter-clockwise
  void doR(); // TODO: ...
  void doR2();
  void doRPrime();
  void doL();
  void doL2();
  void doLPrime();
  void doF();
  void doF2();
  void doFPrime();
  void doB();
  void doB2();
  void doBPrime();

  // quarter turn of the cube
  void turn(char *main_face, char *side_face_one, char *side_face_two,
            char *side_face_three, char *side_face_four);

  // prints the entire face
  void print_cube();
};
