#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// arbitrarily
#define W 0x00
#define O 0x01
#define G 0x02
#define R 0x03
#define B 0x04
#define Y 0x05

// 48 bytes for 48 stickers on the cube
// 1 face looks like 0x 00 00 00 00 00 00 00 00 = 8 bytes = 64 bits
struct Cube {
  long long white_face;
  long long orange_face;
  long long green_face;
  long long red_face;
  long long blue_face;
  long long yellow_face;
};

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
// index is a range from 0-7
void get_color(long long face, short index) {
  short shift_amount = 56 - (index * 8);
  char color = (face >> shift_amount) & 0xff;
  print_color_name(color);
}


int main() {
  struct Cube *cube = malloc(sizeof(struct Cube));
  // random face state
  cube->white_face =
      0x0203010004030500; // 0x 02 03 01 00 04 03 05 00 === G R O W __ B R Y W
  get_color(cube->white_face, 0);
  get_color(cube->white_face, 1);
  get_color(cube->white_face, 2);
  get_color(cube->white_face, 3);
  get_color(cube->white_face, 4);
  get_color(cube->white_face, 5);
  get_color(cube->white_face, 6);
  get_color(cube->white_face, 7);
};
