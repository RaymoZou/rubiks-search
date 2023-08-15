from CubeClass import Cube
import numpy as np
import sys

w, y, r, o, g, b = 'w', 'y', 'r', 'o', 'g', 'b'
sample_state = [
    [y, r, g, o, g, g, o, w, g],
    [o, b, b, y, b, y, b, b, g],
    [r, b, w, r, r, g, y, o, w],
    [o, w, r, o, o, g, r, w, b],
    [y, g, o, b, y, w, y, r, r],
    [w, o, g, r, w, y, b, y, w]
]

scrambled_cube = Cube(sample_state)

# takes a Cube and returns a Cube with orientated edges
def iddfs_group1(cube):
    print("solution found")
    # dfs_threshold = sys.maxsize
    dfs_threshold = 1000
    for i in range(dfs_threshold):
        if dfs_group1(cube, i) != None:
            return cube
    return None

def dfs_group1(cube: Cube, depth: int):
    # if cube is solution - return cube
    # if dfs_treshold reached, return None
        # else generate children and recurse
    if depth == 0:
        if cube.is_group_1_goal(): return cube
        else: return None

    # generate children nodes
    
    
# F L2 U2 F' R2 U2 F D2 U2 B' F' R2 L' U' L F2 U B R2 U L

# Group 0 -> Group 1
# iddfs_group1(scrambled_cube)
r_cube = Cube()
