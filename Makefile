CC = g++
CFLAGS = -lSDL2

main: main.cpp cube.cpp
	$(CC) $(CFLAGS) main.cpp cube.cpp -o main.exe
