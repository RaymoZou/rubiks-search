enum colors { W = 'W', O = 'O', G = 'G', R = 'R', B = 'B', Y = 'Y' };

class Cube {


public:
  // TODO: replace this with just an array of 48 chars?
  char white_face[8];
  char orange_face[8];
  char green_face[8];
  char red_face[8];
  char blue_face[8];
  char yellow_face[8];

  Cube(char white[8], char orange[8], char green[8], char red[8], char blue[8],
       char yellow[8]);

 // prints the color name
  void print_color_name(char color);

 // quarter turn of the cube
  void turn(char *main_face, 
		char *side_face_one, char *side_face_two, char *side_face_three, 
		char *side_face_four);

  // prints the entire face
  void print_cube();
};
