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

// generalized turn method that takes in a mainface and 4 side faces to be
// turned CLOCKWISE
// TODO: currently defauts to a U turn - generalize for all possible turns
// first 8 bytes are the main face
// 3(4) = 12 stickers for side faces
void Cube::turn(char *main_face, 
		char *side_face_one, char *side_face_two, char *side_face_three, 
		char *side_face_four) {
  // this is used to hold the current layer being turned
  char buffer[20];
  memcpy(buffer, main_face, 8);       // main face
  memcpy(buffer + 8, side_face_one, 3);   // side face 1
  memcpy(buffer + 11, side_face_two, 3); // side face 2
  memcpy(buffer + 14, side_face_three, 3);   // side face 3
  memcpy(buffer + 17, side_face_four, 3);    // side face 4

  // do the swaps
  main_face[0] = buffer[5];
  main_face[1] = buffer[3];
  main_face[2] = buffer[0];
  main_face[3] = buffer[6];
  main_face[4] = buffer[1];
  main_face[5] = buffer[7];
  main_face[6] = buffer[4];
  main_face[7] = buffer[2];

  side_face_one[0] = buffer[8];
  side_face_one[1] = buffer[9];
  side_face_one[2] = buffer[10];

  side_face_two[0] = buffer[11];
  side_face_two[1] = buffer[12];
  side_face_two[2] = buffer[13];

  side_face_three[0] = buffer[14];
  side_face_three[1] = buffer[15];
  side_face_three[2] = buffer[16];

  side_face_four[0] = buffer[17];
  side_face_four[1] = buffer[18];
  side_face_four[2] = buffer[19];
};

void Cube::print_cube() {
  for (int i = 0; i < 48; i++) {
    print_color_name(white_face[i]);
  };
};
