#include "cube.hpp"
#include <SDL2/SDL.h>
#include <stdio.h>
#include <stdlib.h>

// example scramble: R2 U2 R2 B D2 L2 R2 B2 F U2 F' D2 R' F' R2 D U' R' U B L

int main(int argc, char **argv) {

  // initiate SDL library
  SDL_Init(SDL_INIT_VIDEO);

  char white[8] = {G, G, G, G, B, B, B, Y};
  char orange[8] = {O, W, Y, B, O, B, W, B};
  char green[8] = {O, R, R, Y, O, Y, R, R};
  char red[8] = {G, Y, R, W, G, W, R, O};
  char blue[8] = {W, Y, W, O, O, G, G, O};
  char yellow[8] = {R, W, B, B, Y, W, R, Y};
  /* struct Cube *cube = createCube(white, orange, green, red, blue, yellow); */
  Cube *cube = new Cube(white, orange, green, red, blue, yellow);
  cube->print_cube();
  printf("\n");
  cube->turn(); // TODO: render a GUI image of the cube after each turn
  printf("\n");
  cube->print_cube();
  free(cube);
  return 0;
};
