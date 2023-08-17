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

node_count = 0
curr_solution = []


def reset_node_count():
    global node_count
    node_count = 0


def iddfs_group0(cube) -> Cube:
    # dfs_threshold = sys.maxsize
    dfs_threshold = 10
    global node_count
    for i in range(dfs_threshold):
        result = dfs_group0(cube, i)
        if result != None:
            return result
        print("finish searching depth: " + str(i))
        reset_node_count()
    return None


def dfs_group0(node: Cube, depth: int):
    global node_count
    node_count += 1
    if depth == 0:
        if node.is_group_0_goal():
            curr_solution.append(node.last_move)
            print(str(node_count) + " nodes searched...")
            return node
        else:
            return None
    # generate children nodes
    if node.last_move != None:
        curr_solution.append(node.last_move)
    children = node.get_children_nodes()
    # prune children
    if node.last_move == "R" or node.last_move == "R'" or node.last_move == "R2":
        children[0] = None
        children[1] = None
        children[2] = None
    if node.last_move == "L" or node.last_move == "L'" or node.last_move == "L2":
        children[3] = None
        children[4] = None
        children[5] = None
    if node.last_move == "U" or node.last_move == "U'" or node.last_move == "U2":
        children[6] = None
        children[7] = None
        children[8] = None
    if node.last_move == "D" or node.last_move == "D'" or node.last_move == "D2":
        children[9] = None
        children[10] = None
        children[11] = None
    if node.last_move == "F" or node.last_move == "F'" or node.last_move == "F2":
        children[12] = None
        children[13] = None
        children[14] = None
    if node.last_move == "B" or node.last_move == "B'" or node.last_move == "B2":
        children[15] = None
        children[16] = None
        children[17] = None

    for c in children:
        if c != None:
            result = dfs_group0(c, depth-1)
            if result != None:
                return result

    if len(curr_solution) != 0:
        curr_solution.pop()


def iddfs_group1(cube) -> Cube:
    # dfs_threshold = sys.maxsize
    dfs_threshold = 10
    global node_count
    for i in range(dfs_threshold):
        result = dfs_group1(cube, i)
        if result != None:
            return result
        print("finish searching depth: " + str(i))
    return None


def dfs_group1(node: Cube, depth: int):
    global node_count
    node_count += 1
    if depth == 0:
        if node.is_group_1_goal():
            curr_solution.append(node.last_move)
            print(str(node_count) + " nodes searched...")
            return node
        else:
            return None
    # generate children nodes
    curr_solution.append(node.last_move)
    children = node.get_children_nodes()
    # prune children
    if node.last_move == "R" or node.last_move == "R'" or node.last_move == "R2":
        children[0] = None
        children[1] = None
        children[2] = None
    if node.last_move == "L" or node.last_move == "L'" or node.last_move == "L2":
        children[3] = None
        children[4] = None
        children[5] = None
    if node.last_move == "U" or node.last_move == "U'" or node.last_move == "U2":
        children[6] = None
        children[7] = None
        children[8] = None
    if node.last_move == "D" or node.last_move == "D'" or node.last_move == "D2":
        children[9] = None
        children[10] = None
        children[11] = None
    if node.last_move == "F" or node.last_move == "F'" or node.last_move == "F2":
        children[12] = None
        children[13] = None
        children[14] = None
    if node.last_move == "B" or node.last_move == "B'" or node.last_move == "B2":
        children[15] = None
        children[16] = None
        children[17] = None

    # remove quarter turns of F and B from children
    # 12, 13, 14, 15
    children[12] = None
    children[14] = None
    children[15] = None
    children[17] = None

    for c in children:
        if c != None:
            result = dfs_group1(c, depth-1)
            if result != None:
                return result

    if len(curr_solution) > 0:
        curr_solution.pop()


# Group 0 -> Group 1
scrambled_cube = Cube(sample_state)
scrambled_cube.last_move = ""
cube1 = iddfs_group0(scrambled_cube)
print(curr_solution)
curr_solution = []
reset_node_count()
cube2 = iddfs_group1(cube1)
print(curr_solution)
