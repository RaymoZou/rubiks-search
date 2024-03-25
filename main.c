#include <stdio.h>
#include <stdlib.h>
#include "cube.c"

// arbitrarily
#define W 0x00
#define O 0x01
#define G 0x02
#define R 0x03
#define B 0x04
#define Y 0x05

void print_color_name(char number) {
  switch (number) {
  case W:
    printf("%c\n", 'W');
    break;
  case O:
    printf("%c\n", 'O');
    break;
  case G:
    printf("%c\n", 'G');
    break;
  case R:
    printf("%c\n", 'R');
    break;
  case B:
    printf("%c\n", 'B');
    break;
  case Y:
    printf("%c\n", 'Y');
    break;
  }
};

// returns the color of the first sticker
// index is a range from [0, 48]
void get_color(struct Cube *cube, int index) {
  print_color_name(cube->white_face[index]);
};

int main() {
  struct Cube *cube = malloc(sizeof(struct Cube));
  // random face state
  // cube->white_face =
  //     0x0203010004030500; // 0x 02 03 01 00 04 03 05 00 === G R O W __ B R Y W
  cube->white_face[0] = G;
  cube->white_face[1] = R;
  cube->white_face[2] = O;
  cube->white_face[3] = W;
  cube->white_face[4] = B;
  cube->white_face[5] = R;
  cube->white_face[6] = Y;
  cube->white_face[7] = W;
  get_color(cube, 0);

  free(cube);
};
