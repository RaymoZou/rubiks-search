#include "cube.c"
#include <stdio.h>
#include <stdlib.h>

void print_color_name(unsigned char color) {
  switch (color) {
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
void get_color(const struct Cube *cube, int index) {
  print_color_name(cube->white_face[index]);
};

int main() {
  struct Cube *cube = malloc(sizeof(struct Cube));
  cube->white_face[0] = G;
  cube->white_face[1] = R;
  cube->white_face[2] = O;
  cube->white_face[3] = W;
  cube->white_face[4] = B;
  cube->white_face[5] = R;
  cube->white_face[6] = Y;
  cube->white_face[7] = W;
  // TODO: function for printing a face
  print_color_name(cube->white_face[0]);
  print_color_name(cube->white_face[1]);
  print_color_name(cube->white_face[2]);
  print_color_name(cube->white_face[3]);
  print_color_name(cube->white_face[4]);
  free(cube);
  return 0;
};
