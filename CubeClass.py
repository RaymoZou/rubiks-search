import numpy as np
import copy


class Cube:

    def __init__(self, value=None):
        if value is None:
            self.green = np.repeat('g', 9)
            self.blue = np.repeat('b', 9)
            self.red = np.repeat('r', 9)
            self.orange = np.repeat('o', 9)
            self.yellow = np.repeat('y', 9)
            self.white = np.repeat('w', 9)
        else:
            self.green = np.array(value[0])
            self.blue = np.array(value[1])
            self.red = np.array(value[2])
            self.orange = np.array(value[3])
            self.yellow = np.array(value[4])
            self.white = np.array(value[5])

        self.last_move = None

    def set_face(self, face, newFace):
        if face == 'green':
            self.green = newFace
        elif face == 'blue':
            self.blue = newFace
        elif face == 'red':
            self.red = newFace
        elif face == 'orange':
            self.orange = newFace
        elif face == 'yellow':
            self.yellow = newFace
        elif face == 'white':
            self.white = newFace

    def turn_U(self, turn_num):
        s = self
        # set side faces
        temp_arr = np.concatenate(
            (s.green[:3], s.orange[:3], s.blue[:3], s.red[:3]))
        temp_arr = np.roll(temp_arr, shift=turn_num*3, axis=0)
        s.green[:3] = temp_arr[:3]
        s.orange[:3] = temp_arr[3:6]
        s.blue[:3] = temp_arr[6:9]
        s.red[:3] = temp_arr[9:12]
        # set main face corners
        corners = s.white[[0, 2, 8, 6]]
        s.white[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.white[[1, 5, 7, 3]]
        s.white[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

        # set last move
        if turn_num == 1:
            s.last_move = "U"
        elif turn_num == -1:
            s.last_move = "U'"
        elif turn_num == 2:
            s.last_move = "U2"

    def turn_D(self, turn_num):
        s = self
        # set side faces
        temp_arr = np.concatenate(
            (s.green[-3:], s.red[-3:], s.blue[-3:], s.orange[-3:]))
        temp_arr = np.roll(temp_arr, shift=turn_num*3, axis=0)
        s.green[-3:] = temp_arr[:3]
        s.red[-3:] = temp_arr[3:6]
        s.blue[-3:] = temp_arr[6:9]
        s.orange[-3:] = temp_arr[9:12]
        # set main face corners
        corners = s.yellow[[0, 2, 8, 6]]
        s.yellow[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.yellow[[1, 5, 7, 3]]
        s.yellow[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

        if turn_num == 1:
            s.last_move = "D"
        elif turn_num == -1:
            s.last_move = "D'"
        elif turn_num == 2:
            s.last_move = "D2"

    def turn_R(self, turn_num):
        s = self
        # set side faces
        temp_arr = np.concatenate(
            (s.green[[8, 5, 2]], s.white[[8, 5, 2]], s.blue[[0, 3, 6]], s.yellow[[8, 5, 2]],))
        temp_arr = np.roll(temp_arr, shift=turn_num*3, axis=0)
        s.green[[8, 5, 2]] = temp_arr[:3]
        s.white[[8, 5, 2]] = temp_arr[3:6]
        s.blue[[0, 3, 6]] = temp_arr[6:9]
        s.yellow[[8, 5, 2]] = temp_arr[9:12]
        # set main face corners
        corners = s.red[[0, 2, 8, 6]]
        s.red[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.red[[1, 5, 7, 3]]
        s.red[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

        if turn_num == 1:
            s.last_move = "R"
        elif turn_num == -1:
            s.last_move = "R'"
        elif turn_num == 2:
            s.last_move = "R2"

    def turn_L(self, turn_num):
        s = self
        # set side faces
        temp_arr = np.concatenate((s.green[[0, 3, 6]],
                                   s.yellow[[0, 3, 6]],
                                   s.blue[[8, 5, 2]],
                                   s.white[[0, 3, 6]],))
        temp_arr = np.roll(temp_arr, shift=turn_num*3, axis=0)
        s.green[[0, 3, 6]] = temp_arr[:3]
        s.yellow[[0, 3, 6]] = temp_arr[3:6]
        s.blue[[8, 5, 2]] = temp_arr[6:9]
        s.white[[0, 3, 6]] = temp_arr[9:12]
        # set main face corners
        corners = s.orange[[0, 2, 8, 6]]
        s.orange[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.orange[[1, 5, 7, 3]]
        s.orange[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

        if turn_num == 1:
            s.last_move = "L"
        elif turn_num == -1:
            s.last_move = "L'"
        elif turn_num == 2:
            s.last_move = "L2"

    def turn_F(self, turn_num):
        s = self
        temp_arr = np.concatenate(
            (s.white[[6, 7, 8]], s.red[[0, 3, 6]], s.yellow[[2, 1, 0]], s.orange[[8, 5, 2]]))
        temp_arr = np.roll(temp_arr, shift=turn_num*3, axis=0)
        s.white[[6, 7, 8]] = temp_arr[:3]
        s.red[[0, 3, 6]] = temp_arr[3:6]
        s.yellow[[2, 1, 0]] = temp_arr[6:9]
        s.orange[[8, 5, 2]] = temp_arr[9:12]
        # set main face corners
        corners = s.green[[0, 2, 8, 6]]
        s.green[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.green[[1, 5, 7, 3]]
        s.green[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

        if turn_num == 1:
            s.last_move = "F"
        elif turn_num == -1:
            s.last_move = "F'"
        elif turn_num == 2:
            s.last_move = "F2"

    def turn_B(self, turn_num):
        s = self
        # set side faces
        temp_arr = np.concatenate(
            (s.white[[2, 1, 0]], s.orange[[0, 3, 6]], s.yellow[[6, 7, 8]], s.red[[8, 5, 2]]))
        temp_arr = np.roll(temp_arr, shift=turn_num*3, axis=0)
        s.white[[2, 1, 0]] = temp_arr[:3]
        s.orange[[0, 3, 6]] = temp_arr[3:6]
        s.yellow[[6, 7, 8]] = temp_arr[6:9]
        s.red[[8, 5, 2]] = temp_arr[9:12]
        # set main face corners
        corners = s.blue[[0, 2, 8, 6]]
        s.blue[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.blue[[1, 5, 7, 3]]
        s.blue[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

        if turn_num == 1:
            s.last_move = "B"
        elif turn_num == -1:
            s.last_move = "B'"
        elif turn_num == 2:
            s.last_move = "B2"

    def print_faces(self):
        print('\n'.join(''.join(self.white[i:i+3])
                        for i in range(0, len(self.white), 3)))
        print('------------')
        print('\n'.join(''.join(self.green[i:i+3])
                        for i in range(0, len(self.green), 3)))
        print('------------')
        print('\n'.join(''.join(self.red[i:i+3])
                        for i in range(0, len(self.red), 3)))
        print('------------')
        print('\n'.join(''.join(self.blue[i:i+3])
                        for i in range(0, len(self.blue), 3)))
        print('------------')
        print('\n'.join(''.join(self.orange[i:i+3])
                        for i in range(0, len(self.orange), 3)))
        print('------------')
        print('\n'.join(''.join(self.yellow[i:i+3])
                        for i in range(0, len(self.yellow), 3)))

    def is_group_0_edge(self, edge) -> bool:
        if edge[0] == 'r' or edge[0] == 'o':
            return False
        elif edge[1] == 'w' or edge[1] == 'y':
            return False
        return True

    def get_unsolved_group_0_edges(self) -> int:
        unsolved_edges = 0

        ub = np.array([self.white[1], self.blue[1]])
        ur = np.array([self.white[5], self.red[1]])
        uf = np.array([self.white[7], self.green[1]])
        ul = np.array([self.white[3], self.orange[1]])

        df = np.array([self.yellow[1], self.green[7]])
        dr = np.array([self.yellow[5], self.red[7]])
        db = np.array([self.yellow[7], self.blue[7]])
        dl = np.array([self.yellow[3], self.orange[7]])

        fr = np.array([self.green[5], self.red[3]])
        fl = np.array([self.green[3], self.orange[5]])
        br = np.array([self.blue[3], self.red[5]])
        bl = np.array([self.blue[5], self.orange[3]])

        edges = [ub, ur, uf, ul, df, dr, db, dl, fr, fl, br, bl]
        for edge in edges:
            if self.is_group_0_edge(edge) == False:
                unsolved_edges += 1

        return unsolved_edges

    def is_group_0_goal(self) -> bool:
        return self.get_unsolved_group_0_edges() == 0

    def get_unsolved_group_1_pieces(self) -> int:
        solved_pieces = 0

        # corners
        ubl = np.array([self.white[0], self.blue[2], self.orange[0]])
        ubr = np.array([self.white[2], self.blue[0], self.red[2]])
        ufl = np.array([self.white[6], self.green[0], self.orange[2]])
        ufr = np.array([self.white[8], self.green[2], self.red[0]])

        dfl = np.array([self.yellow[0], self.green[6], self.orange[8]])
        dfr = np.array([self.yellow[2], self.green[8], self.red[6]])
        dbl = np.array([self.yellow[6], self.blue[8], self.orange[6]])
        dbr = np.array([self.yellow[8], self.blue[6], self.red[8]])
        corners = np.array([ubl, ubr, ufl, ufr, dfl, dfr, dbl, dbr])
        for corner in corners:
            if corner[0] == "y" or corner[0] == "w":
                solved_pieces += 1

        # edges
        fl = np.array([self.green[3], self.orange[5]])
        fr = np.array([self.green[5], self.red[3]])
        bl = np.array([self.blue[5], self.orange[3]])
        br = np.array([self.blue[3], self.red[5]])
        edges = np.array([fl, fr, bl, br])
        for edge in edges:
            if edge[0] == "g" or edge[0] == "b":
                if edge[1] == "o" or edge[1] == "r":
                    solved_pieces += 1

        return 12 - solved_pieces

    def is_group_1_goal(self) -> bool:
        return self.get_unsolved_group_1_pieces() == 0

    def do_moves(self, moves: str):
        for i in range(len(moves)):
            if moves[i] == 'R':
                self.turn_R(1)
            elif moves[i] == "R'":
                self.turn_R(-1)
            elif moves[i] == "R2":
                self.turn_R(2)
            elif moves[i] == "L":
                self.turn_L(1)
            elif moves[i] == "L'":
                self.turn_L(-1)
            elif moves[i] == "L2":
                self.turn_L(2)
            elif moves[i] == "U":
                self.turn_U(1)
            elif moves[i] == "U'":
                self.turn_U(-1)
            elif moves[i] == "U2":
                self.turn_U(2)
            elif moves[i] == "D":
                self.turn_D(1)
            elif moves[i] == "D'":
                self.turn_D(-1)
            elif moves[i] == "D2":
                self.turn_D(2)
            elif moves[i] == "F":
                self.turn_F(1)
            elif moves[i] == "F'":
                self.turn_F(-1)
            elif moves[i] == "F2":
                self.turn_F(2)
            elif moves[i] == "B":
                self.turn_B(1)
            elif moves[i] == "B'":
                self.turn_B(-1)
            elif moves[i] == "B2":
                self.turn_B(2)

    # TODO: make more readable
    def get_children_nodes(self):
        r_cube = copy.deepcopy(self)
        r2_cube = copy.deepcopy(self)
        r_prime_cube = copy.deepcopy(self)
        l_cube = copy.deepcopy(self)
        l2_cube = copy.deepcopy(self)
        l_prime_cube = copy.deepcopy(self)
        u_cube = copy.deepcopy(self)
        u2_cube = copy.deepcopy(self)
        u_prime_cube = copy.deepcopy(self)
        d_cube = copy.deepcopy(self)
        d2_cube = copy.deepcopy(self)
        d_prime_cube = copy.deepcopy(self)
        f_cube = copy.deepcopy(self) # 12
        f2_cube = copy.deepcopy(self) 
        f_prime_cube = copy.deepcopy(self) # 14
        b_cube = copy.deepcopy(self) # 15
        b2_cube = copy.deepcopy(self)
        b_prime_cube = copy.deepcopy(self) # 17

        # r moves
        r_cube.turn_R(1)
        r_prime_cube.turn_R(-1)
        r2_cube.turn_R(2)
        # l moves
        l_cube.turn_L(1)
        l_prime_cube.turn_L(-1)
        l2_cube.turn_L(2)
        # u moves
        u_cube.turn_U(1)
        u_prime_cube.turn_U(-1)
        u2_cube.turn_U(2)
        # d moves
        d_cube.turn_D(1)
        d_prime_cube.turn_D(-1)
        d2_cube.turn_D(2)
        # f moves
        f_cube.turn_F(1)
        f_prime_cube.turn_F(-1)
        f2_cube.turn_F(2)
        # b moves
        b_cube.turn_B(1)
        b_prime_cube.turn_B(-1)
        b2_cube.turn_B(2)

        return np.array([
            r_cube, r_prime_cube, r2_cube,
            l_cube, l_prime_cube, l2_cube,
            u_cube, u_prime_cube, u2_cube,
            d_cube, d_prime_cube, d2_cube,
            f_cube, f_prime_cube, f2_cube,
            b_cube, b_prime_cube, b2_cube,
        ])
