#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// WHITE = 0x00
// ORANGE = 1
// GREEN = 2
// RED = 3
// BLUE = 4
// YELLOW = 5
enum colors { W = 0x00, O = 0x01, G = 0x02, R = 0x03, B = 0x04, Y = 0x05 };

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
// 3(4) = 12 stickers for side faces
void turn(struct Cube *cube) {
  unsigned char *buffer = malloc(sizeof(unsigned char) * 20);
  memcpy(buffer, &cube->white_face, 8);       // copy main face
  memcpy(buffer + 8, &cube->green_face, 3);   // side face 1
  memcpy(buffer + 11, &cube->orange_face, 3); // side face 2
  memcpy(buffer + 14, &cube->blue_face, 3);   // side face 3
  memcpy(buffer + 17, &cube->red_face, 3);    // side face 4
  cube->white_face[0] = buffer[5];
  cube->white_face[1] = buffer[3];
  cube->white_face[2] = buffer[0];
  cube->white_face[3] = buffer[6];
  cube->white_face[4] = buffer[1];
  cube->white_face[5] = buffer[7];
  cube->white_face[6] = buffer[4];
  cube->white_face[7] = buffer[2];

  cube->orange_face[0] = buffer[8];
  cube->orange_face[1] = buffer[9];
  cube->orange_face[2] = buffer[10];

  cube->blue_face[0] = buffer[11];
  cube->blue_face[1] = buffer[12];
  cube->blue_face[2] = buffer[13];

  cube->red_face[0] = buffer[14];
  cube->red_face[1] = buffer[15];
  cube->red_face[2] = buffer[16];

  cube->green_face[0] = buffer[17];
  cube->green_face[1] = buffer[18];
  cube->green_face[2] = buffer[19];
  free(buffer);
};

void print_cube(struct Cube *cube) {
  for (int i = 0; i < 48; i++) {
    print_color_name(cube->white_face[i]);
  };
};
