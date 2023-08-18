# Iterative Depth First Search (IDFS) Cube Solver
## Version 1.0.0

This is a Rubik's Cube Solver that implements Thistletwaite's Algorithm using a series of iterative depth-first searches to solve sequential subgroups. Cube states are represented as an array of chars and actions correspond to quarter and half turns of each face.

Thistlethwaite's algorithm consists of 4 subgroups: G<sub>0</sub>, G<sub>1</sub>, G<sub>2</sub>, G<sub>3</sub>, G<sub>4</sub>.

### G<sub>0</sub>
represents a scrambled cube and has a branching factor of 18.

### G<sub>1</sub>
 is a cube with all its edges orientated and has a branching factor of 14 (minus quarter turns of the F and B faces in either direction). We are able to reduce our branching factor from 18 to 14 since it is possible to solve the cube at this stage without F, F', B, and B' given that all its edges are orientated.

### G<sub>2</sub>
 is a cube with all its corners orientated as well as all the E slices edges in the correct orientation. The branching factor is 10.

### G<sub>3</sub>
 is a cube that has all its edges and corners in the correct orbit - that is, all pieces can be moved to its solved position with half turns of any face. The branching factor is 6.

### G<sub>4</sub>
 is a solved cube.

## Example scrambles
### B2 F2 D R2 F2 L2 R2 U' B2 L2 D2 F2 B' D2 R' B' L' D B2 L2 R2
- Moves required for Group 0 = 3
- Moves required for Group 1 = 8
- Moves required for Group 2 = 6
- Moves required for Group 3 = 6
- Total moves required to solve cube = 3 + 8 + 6 + 6 = 23


## Known Issues
- The solver is currently only able to find only some solutions which are at shallow depths within a reasonable amount of time.

