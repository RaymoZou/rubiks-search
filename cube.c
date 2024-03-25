// 48 bytes for 48 stickers on the cube
// 1 face looks like 0x 00 00 00 00 00 00 00 00 = 8 bytes = 64 bits
struct Cube {
  unsigned char white_face[8];
  unsigned char orange_face[8];
  unsigned char green_face[8];
  unsigned char red_face[8];
  unsigned char blue_face[8];
  unsigned char yellow_face[8];
};
