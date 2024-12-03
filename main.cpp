#include "cube.hpp"
#include <SDL2/SDL.h>
#include <stdio.h>
#include <stdlib.h>

// example scramble: R2 U2 R2 B D2 L2 R2 B2 F U2 F' D2 R' F' R2 D U' R' U B L

int main(int argc, char **argv) {

  // initiate SDL library
  /* SDL_Init(SDL_INIT_VIDEO); */

  char white[9] = {G, G, G, G, W, B, B, B, Y};
  char orange[9] = {O, W, Y, B, O, O, B, W, B};
  char green[9] = {O, R, R, Y, G, O, Y, R, R};
  char red[9] = {G, Y, R, W, R, G, W, R, O};
  char blue[9] = {W, Y, W, O, B, O, G, G, O};
  char yellow[9] = {R, W, B, B, Y, Y, W, R, Y};
  /* Cube *cube = new Cube(white, orange, green, red, blue, yellow); */
  Cube *cube = new Cube(white, orange, green, red, blue, yellow);
  cube->print_cube();
  /* printf("\n"); */
  /* cube->turn(cube->white_face, cube->green_face, cube->orange_face, cube->blue_face, */
  /*            red); // TODO: render a GUI image of the cube after each turn */
  /* printf("\n"); */
  /* cube->print_cube(); */
  delete cube;
  return 0;
};
