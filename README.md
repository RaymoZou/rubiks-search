# Iterative Depth First Search (IDFS) Cube Solver
## Version 1.0.0

This is a Rubik's Cube Solver that implements Thistletwaite's Algorithm using a series of iterative depth-first searches to solve sequential subgroups. Cube states are represented as an array of chars and actions correspond to quarter and half turns of each face. This version of the solver is created using Python and the numpy library.

## Example scrambles
### B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2
- Moves required for Group 0 = 3
- Moves required for Group 1 = 8
- Moves required for Group 2 = 6
- Moves required for Group 3 = 6
- Total moves required to solve cube = 3 + 8 + 6 + 6 = 23


## Known Issues
- The solver is currently only able to find only some solutions which are at shallow depths within a reasonable amount of time.

