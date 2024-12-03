#include "cube.hpp"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// we use 8 because the center piece of each face does not move
Cube::Cube(char white[9], char orange[9], char green[9], char red[9],
           char blue[9], char yellow[9]) {
  memcpy(white_face, white, 9);
  memcpy(orange_face, orange, 9);
  memcpy(green_face, green, 9);
  memcpy(red_face, red, 9);
  memcpy(blue_face, blue, 9);
  memcpy(yellow_face, yellow, 9);
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

void Cube::turn(char *main_face, char *side_face_one, char *side_face_two,
                char *side_face_three, char *side_face_four) {
  // save the current state of the cube
  char buffer[21]; // 9 stickers on main face + 3 on each side face = 21
  memcpy(buffer, main_face, 9);            // main face
  memcpy(buffer + 9, side_face_one, 3);    // side face 1
  memcpy(buffer + 12, side_face_two, 3);   // side face 2
  memcpy(buffer + 15, side_face_three, 3); // side face 3
  memcpy(buffer + 18, side_face_four, 3);  // side face 4

  // do the swaps
  main_face[0] = buffer[6];
  main_face[1] = buffer[3];
  main_face[2] = buffer[0];
  main_face[3] = buffer[7];
  main_face[4] = buffer[4]; // center
  main_face[5] = buffer[1];
  main_face[6] = buffer[8];
  main_face[7] = buffer[5];
  main_face[8] = buffer[2];

  side_face_one[0] = buffer[18];
  side_face_one[1] = buffer[19];
  side_face_one[2] = buffer[20];

  side_face_two[0] = buffer[9];
  side_face_two[1] = buffer[10];
  side_face_two[2] = buffer[11];

  side_face_three[0] = buffer[12];
  side_face_three[1] = buffer[13];
  side_face_three[2] = buffer[14];

  side_face_four[0] = buffer[15];
  side_face_four[1] = buffer[16];
  side_face_four[2] = buffer[17];
};

void Cube::print_cube() {
  for (int i = 0; i < 54; i++) {
    if (i % 9 == 0) {
      printf("\n-----------");
    }
    if (i % 3 == 0) {
      printf("\n");
    }
    print_color_name(white_face[i]);
  };
  printf("\n");
};
