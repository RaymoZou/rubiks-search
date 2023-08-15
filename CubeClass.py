import numpy as np


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

    def is_group_1_edge(self, edge):
        if edge[0] == 'r' or edge[0] == 'o':
            return False
        elif edge[1] == 'w' or edge[1] == 'y':
            return False
        return True
        

    def get_unsolved_group_1_edges(self):
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
            if self.is_group_1_edge(edge) == False:
                unsolved_edges += 1

        return unsolved_edges

    def is_group_1_goal(self):
        return self.get_unsolved_group_1_edges() == 0
