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

 // quarter turn of the cube
  void turn(char *main_face, 
		char *side_face_one, char *side_face_two, char *side_face_three, 
		char *side_face_four);

  // prints the entire face
  void print_cube();
};
