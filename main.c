#include "cube.c"
#include <stdio.h>
#include <stdlib.h>

// example scramble: R2 U2 R2 B D2 L2 R2 B2 F U2 F' D2 R' F' R2 D U' R' U B L

int main() {
  unsigned char white[8] = {G, G, G, G, B, B, B, Y};
  unsigned char orange[8] = {O, W, Y, B, O, B, W, B};
  unsigned char green[8] = {O, R, R, Y, O, Y, R, R};
  unsigned char red[8] = {G, Y, R, W, G, W, R, O};
  unsigned char blue[8] = {W, Y, W, O, O, G, G, O};
  unsigned char yellow[8] = {R, W, B, B, Y, W, R, Y};
  struct Cube *cube = createCube(white, orange, green, red, blue, yellow);
  print_cube(cube);
  printf("\n");
  turn(cube);
  printf("\n");
  print_cube(cube);
  free(cube);
  return 0;
};
