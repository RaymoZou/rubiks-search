from CubeClass import Cube
import numpy as np
import Solver as solver

w, y, r, o, g, b = 'w', 'y', 'r', 'o', 'g', 'b'

# sample scramble: F L2 U2 F' R2 U2 F D2 U2 B' F' R2 L' U' L F2 U B R2 U L
sample_cube = [
    [y, r, g, o, g, g, o, w, g],
    [o, b, b, y, b, y, b, b, g],
    [r, b, w, r, r, g, y, o, w],
    [o, w, r, o, o, g, r, w, b],
    [y, g, o, b, y, w, y, r, r],
    [w, o, g, r, w, y, b, y, w]
]

def test_constructor():
    solved_cube = Cube()
    np.testing.assert_array_equal(solved_cube.green, np.repeat(g, 9))
    np.testing.assert_array_equal(solved_cube.blue, np.repeat(b, 9))
    np.testing.assert_array_equal(solved_cube.red, np.repeat(r, 9))
    np.testing.assert_array_equal(solved_cube.orange, np.repeat(o, 9))
    np.testing.assert_array_equal(solved_cube.yellow, np.repeat(y, 9))
    np.testing.assert_array_equal(solved_cube.white, np.repeat(w, 9))

    custom_cube = Cube(sample_cube)
    np.testing.assert_array_equal(
        custom_cube.green, [y, r, g, o, g, g, o, w, g])
    np.testing.assert_array_equal(
        custom_cube.blue, [o, b, b, y, b, y, b, b, g])
    np.testing.assert_array_equal(custom_cube.red, [r, b, w, r, r, g, y, o, w])
    np.testing.assert_array_equal(
        custom_cube.orange, [o, w, r, o, o, g, r, w, b])
    np.testing.assert_array_equal(
        custom_cube.yellow, [y, g, o, b, y, w, y, r, r])
    np.testing.assert_array_equal(
        custom_cube.white, [w, o, g, r, w, y, b, y, w])


def test_do_u():
    custom_cube = Cube(sample_cube)
    custom_cube.turn_U(1)
    np.testing.assert_array_equal(
        custom_cube.green, [r, b, w, o, g, g, o, w, g])
    np.testing.assert_array_equal(custom_cube.red, [o, b, b, r, r, g, y, o, w])
    np.testing.assert_array_equal(
        custom_cube.blue, [o, w, r, y, b, y, b, b, g])
    np.testing.assert_array_equal(
        custom_cube.orange, [y, r, g, o, o, g, r, w, b])
    np.testing.assert_array_equal(
        custom_cube.white, [b, r, w, y, w, o, w, y, g])


def test_do_u_prime():
    custom_cube = Cube(sample_cube)
    custom_cube.turn_U(-1)
    np.testing.assert_array_equal(
        custom_cube.green, [o, w, r, o, g, g, o, w, g])
    np.testing.assert_array_equal(custom_cube.red, [y, r, g, r, r, g, y, o, w])
    np.testing.assert_array_equal(
        custom_cube.blue, [r, b, w, y, b, y, b, b, g])
    np.testing.assert_array_equal(
        custom_cube.orange, [o, b, b, o, o, g, r, w, b])
    np.testing.assert_array_equal(
        custom_cube.white, [g, y, w, o, w, y, w, r, b])


def test_scramble():
    scrambled_cube = Cube()
    scrambled_cube.turn_F(1)
    scrambled_cube.turn_L(2)
    scrambled_cube.turn_U(2)
    scrambled_cube.turn_F(-1)
    scrambled_cube.turn_R(2)
    scrambled_cube.turn_U(2)
    scrambled_cube.turn_F(1)
    scrambled_cube.turn_D(2)
    scrambled_cube.turn_U(2)
    scrambled_cube.turn_B(-1)
    scrambled_cube.turn_F(-1)
    scrambled_cube.turn_R(2)
    scrambled_cube.turn_L(-1)
    scrambled_cube.turn_U(-1)
    scrambled_cube.turn_L(1)
    scrambled_cube.turn_F(2)
    scrambled_cube.turn_U(1)
    scrambled_cube.turn_B(1)
    scrambled_cube.turn_R(2)
    scrambled_cube.turn_U(1)
    scrambled_cube.turn_L(1)
    np.testing.assert_array_equal(
        scrambled_cube.green, [y, r, g, o, g, g, o, w, g])
    np.testing.assert_array_equal(
        scrambled_cube.blue, [o, b, b, y, b, y, b, b, g])
    np.testing.assert_array_equal(
        scrambled_cube.white, [w, o, g, r, w, y, b, y, w])
    np.testing.assert_array_equal(
        scrambled_cube.yellow, [y, g, o, b, y, w, y, r, r])
    np.testing.assert_array_equal(
        scrambled_cube.red, [r, b, w, r, r, g, y, o, w])
    np.testing.assert_array_equal(
        scrambled_cube.orange, [o, w, r, o, o, g, r, w, b])


def test_group_1_edges():
    cube = Cube()
    assert cube.get_unsolved_group_0_edges() == 0
    cube.turn_F(1)
    assert cube.get_unsolved_group_0_edges() == 4
    cube.turn_F(-1)
    assert cube.get_unsolved_group_0_edges() == 0
    cube.turn_F(-1)
    assert cube.get_unsolved_group_0_edges() == 4
    cube.turn_F(-1)
    assert cube.get_unsolved_group_0_edges() == 0
    scrambled_cube = Cube(sample_cube)
    # unsolved edges are ub, ul, df, db, dl, fl
    assert scrambled_cube.get_unsolved_group_0_edges() == 6

def test_iddfs_group1():
    # solved cube
    solver.reset_node_count()
    solved_cube = Cube()
    cube1 = solver.iddfs_group0(solved_cube)
    assert cube1.get_unsolved_group_0_edges() == 0
    assert solver.node_count == 1

    solver.reset_node_count()
    r2_cube = Cube()
    r2_cube.turn_R(2)
    solver.iddfs_group0(r2_cube)
    assert solver.node_count == 1

    solver.reset_node_count()
    f_cube = Cube()
    f_cube.turn_F(1)
    solver.iddfs_group0(f_cube)
    assert solver.node_count == 14

