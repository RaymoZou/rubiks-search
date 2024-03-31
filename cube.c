#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// WHITE = 0
// ORANGE = 1
// GREEN = 2
// RED = 3
// BLUE = 4
// YELLOW = 5
enum colors { W, O, G, R, B, Y };

// TODO: replace this with just an array of 48 chars?
struct Cube {
  unsigned char white_face[8];
  unsigned char orange_face[8];
  unsigned char green_face[8];
  unsigned char red_face[8];
  unsigned char blue_face[8];
  unsigned char yellow_face[8];
};

// each array has 8 elements
struct Cube *createCube(unsigned char white[8], unsigned char orange[8],
                        unsigned char green[8], unsigned char red[8],
                        unsigned char blue[8], unsigned char yellow[8]) {
  struct Cube *cube = malloc(sizeof(struct Cube));
  memcpy(&cube->white_face, white, 8);
  memcpy(&cube->orange_face, orange, 8);
  memcpy(&cube->green_face, green, 8);
  memcpy(&cube->red_face, red, 8);
  memcpy(&cube->blue_face, blue, 8);
  memcpy(&cube->yellow_face, yellow, 8);
  return cube;
};

void print_color_name(unsigned char color) {
  switch (color) {
  case W:
    printf("%c", 'W');
    break;
  case O:
    printf("%c", 'O');
    break;
  case G:
    printf("%c", 'G');
    break;
  case R:
    printf("%c", 'R');
    break;
  case B:
    printf("%c", 'B');
    break;
  case Y:
    printf("%c", 'Y');
    break;
  }
};

// first 8 bytes are the main face
// side faces are 3 bytes each
// so 8 + 4(3) = 20 byte buffer
// 
void turn(struct Cube *cube) {
    unsigned char buffer[20];
    memcpy(&buffer, &cube->white_face, 8);
    cube->white_face[0] = buffer[5];
    cube->white_face[1] = buffer[3];
    cube->white_face[2] = buffer[0];
    cube->white_face[3] = buffer[6];
    cube->white_face[4] = buffer[1];
    cube->white_face[5] = buffer[7];
    cube->white_face[6] = buffer[4];
    cube->white_face[7] = buffer[2];
};

void print_cube(struct Cube *cube) {
  for (int i = 0; i < 48; i++) {
    print_color_name(cube->white_face[i]);
  };
};
