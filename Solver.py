from CubeClass import Cube

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

curr_solution = []

def iddfs_group0(cube):
    # dfs_threshold = sys.maxsize
    dfs_threshold = 10
    for i in range(dfs_threshold):
        if dfs_group0(cube, i) != None:
            return cube
        print("finish searching depth: " + str(i))
    return None


def dfs_group0(cube: Cube, depth: int):
    # if cube is solution - return cube
    # if dfs_treshold reached, return None
    # else generate children and recurse
    if depth == 0:
        if cube.is_group_1_goal():
            print("group 0 solution found")
            return cube
        else:
            return None
    # generate children nodes
    children = cube.get_children_nodes()
    for cube in children:
        if cube != None:
            curr_solution.append(cube.last_move)
            result = dfs_group0(cube, depth-1)
            if result != None:
                return result
            curr_solution.pop()


# Group 0 -> Group 1
iddfs_group0(scrambled_cube)
print(curr_solution)
