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
        temp_arr = [s.green[:3], s.orange[:3], s.blue[:3], s.red[:3]]
        temp_arr = np.roll(temp_arr, shift=turn_num, axis=0)
        s.green[:3] = temp_arr[0]
        s.orange[:3] = temp_arr[1]
        s.blue[:3] = temp_arr[2]
        s.red[:3] = temp_arr[3]
        # set main face corners
        corners = s.white[[0, 2, 8, 6]]
        s.white[[0, 2, 8, 6]] = np.roll(corners, shift=turn_num)
        # set main face edges
        edges = s.white[[1, 5, 7, 3]]
        s.white[[1, 5, 7, 3]] = np.roll(edges, shift=turn_num)

    # def turn_D(self, turn_num):
    #     s = self
    #     # set side faces
    #     temp_arr = [s.green[]]
