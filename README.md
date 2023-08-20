# Iterative Depth First Search (IDFS) Cube Solver
## Version 1.0.0

This is a Rubik's Cube Solver that implements Thistletwaite's Algorithm using a series of iterative depth-first searches to solve sequential subgroups. Cube states are represented as an array of chars and actions correspond to quarter and half turns of each face. See [here](https://ruwix.com/the-rubiks-cube/notation/) for a guide in interpreting the Rubik's Cube turn notation. The project was written and compiled using java version 19.0.1.

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
### Scramble 1: D' B2 F2 L' F2 L' F2 L' B2 R2 D2 R' U2 D F' D R2 F' L2 F2 
- Moves required for Group 0 = 3: U R2 F
- Moves required for Group 1 = 8: R' U F2 R' L F2 U' R
- Moves required for Group 2 = 6: U F2 U R2 F2 D
- Moves required for Group 3 = 6: L2 U2 L2 U2 B2 L2
- Total moves required to solve cube = 3 + 8 + 6 + 6 = 23

### Scramble 2: U B U' R2 F2 R D R2 F U2 F2 U2 L2 D2 B L2 F' D2 B R
- Moves required for Group 0 = 3: U D F
- Moves required for Group 1 = 8: F2 R' U' D2 B2 R F2 L
- Moves required for Group 2 = 8: U2 B2 R2 U' R2 U L2 D
- Moves required for Group 3 = 10: U2 R2 U2 B2 D2 R2 B2 L2 F2 R2
- Total moves required to solve cube = 3 + 8 + 8 + 10 = 29


## Known Issues
- The solver is currently only able to find only some solutions which are at shallow depths within a reasonable amount of time.
    - From my observations, it appears that the solver often gets stalled at the final IDDFS (Group 3 -> Group 4)

## Setup
1. Clone the repository:
    ```
    git clone https://github.com/RaymoZou/rubiks-search.git
    ```

2. Navigate to project directory and compile the solver using:
    ```
    javac Solver.java
    ```

3. Run the executable with:
    ```
    java Solver
    ```