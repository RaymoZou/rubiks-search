from CubeClass import Cube
import numpy as np

w, y, r, o, g, b = 'w', 'y', 'r', 'o', 'g', 'b'
sample_cube = [
    [y, r, g, o, g, g, o, w, g],
    [o, b, b, y, b, y, b, b, g],
    [r, b, w, r, r, g, y, o, w],
    [o, w, r, o, o, g, r, w, b],
    [y, g, o, b, y, w, y, r, r],
    [w, o, g, r, w, y, b, y, w]
]
cube = Cube()
