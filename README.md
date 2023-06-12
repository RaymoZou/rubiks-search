# Iterative Depth First Search (IDFS) Cube Solver
# Version 1.0.0

This is a Rubik's Cube Solver that implements Thistletwaite's Algorithm using a series of iterative depth first searches to solve sequential subgroups.

## Group 0
This is the first group and is the initial state of the cube, all moves are permitted at this stage.


## Group 1
At this stage, all edges of the cube are oriented meaning the cube can be solved without quarter turns of the B and F faces which are not included in the search
tree for transitioning into the next phase.

## Group 2
At this stage, the E slice (horizontal layer between D and U faces) edges are solved and the corner pieces facing up/down are the same color as the U and D layers.
The cube can be solved without quarter turns of the F, B, R, and L faces at this stage.

## Group 3
The cube can be solved with only half turn of each face. Thus, the only moves that are required to solve the cube are R2, L2, U2, D2, B2, F2.

## Example scrambles
### B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2
- Moves required for Group 0 = 3
- Moves required for Group 1 = 8
- Moves required for Group 2 = 6
- Moves required for Group 3 = 6
- Total moves required to solve cube = 3 + 8 + 6 + 6 = 23


## Known Issues
- The solver is currently only able to find only some solutions which are at shallow depths within a reasonable amount of time.

