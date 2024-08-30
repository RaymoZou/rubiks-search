#include "cube.hpp"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Cube::Cube(char white[8], char orange[8], char green[8], char red[8],
           char blue[8], char yellow[8]) {
  memcpy(white_face, white, 8);
  memcpy(orange_face, orange, 8);
  memcpy(green_face, green, 8);
  memcpy(red_face, red, 8);
  memcpy(blue_face, blue, 8);
  memcpy(yellow_face, yellow, 8);
};

void Cube::print_color_name(char color) {
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

// TODO: currently defauts to a U turn - generalize for all possible turns
// first 8 bytes are the main face
// 3(4) = 12 stickers for side faces
void Cube::turn() {
  // this is used to hold the current layer being turned
  char buffer[20];
  memcpy(buffer, white_face, 8);       // copy main face
  memcpy(buffer + 8, green_face, 3);   // side face 1
  memcpy(buffer + 11, orange_face, 3); // side face 2
  memcpy(buffer + 14, blue_face, 3);   // side face 3
  memcpy(buffer + 17, red_face, 3);    // side face 4

  // do the swaps
  white_face[0] = buffer[5];
  white_face[1] = buffer[3];
  white_face[2] = buffer[0];
  white_face[3] = buffer[6];
  white_face[4] = buffer[1];
  white_face[5] = buffer[7];
  white_face[6] = buffer[4];
  white_face[7] = buffer[2];

  orange_face[0] = buffer[8];
  orange_face[1] = buffer[9];
  orange_face[2] = buffer[10];

  blue_face[0] = buffer[11];
  blue_face[1] = buffer[12];
  blue_face[2] = buffer[13];

  red_face[0] = buffer[14];
  red_face[1] = buffer[15];
  red_face[2] = buffer[16];

  green_face[0] = buffer[17];
  green_face[1] = buffer[18];
  green_face[2] = buffer[19];
};

void Cube::print_cube() {
  for (int i = 0; i < 48; i++) {
    print_color_name(white_face[i]);
  };
};
