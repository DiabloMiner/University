# Copyright TU Wien (2022) - EVC: Task5
# Institute of Computer Graphics and Algorithms.

import numpy as np
from numpy.matlib import repmat

from MeshVertex import MeshVertex
from Framebuffer import Framebuffer
from MeshVertex import MeshVertex

def fill_rasterization(mesh : MeshVertex, framebuffer : Framebuffer):
    """ applies the fill rasterization algorithm. Draws a mesh to the Framebuffer."""

    for i in range(mesh.faces.shape[0]):
        v1 = mesh.get_face(i).get_vertex(0)
        for j in range(mesh.faces[i][0]-1):
            i, j = np.array(i).reshape(np.asarray(i).size), np.array(j).reshape(np.asarray(j).size)

            v2 = mesh.get_face(i).get_vertex(j)
            v3 = mesh.get_face(i).get_vertex(j+1)
            draw_triangle(framebuffer, v1, v2, v3)


def line_eq(A : float, B : float, C : float, x : float, y : float) -> float:
    """defines the line equation described by the provided parameters and
        returns the distance of a point (x, y) to this line.
        A    ... line equation parameter 1
        B    ... line equation parameter 2
        C    ... line equation parameter 3
        x    ... x coordinate of point to test against the line
        y    ... y coordinate of point to test against the line
        res  ... distance of the point (x, y) to the line (A, B, C)."""

    ### STUDENT CODE
    # TODO 3:   Implement this function.
    # NOTE:     The following lines can be removed. They prevent the framework
    #           from crashing.

    res = A * x + B * y + C

    ### END STUDENT CODE


    return res

def draw_triangle(framebuffer : Framebuffer, v1 : MeshVertex, v2 : MeshVertex, v3 : MeshVertex):
    """ draws a triangle defined by v1,v2,v3 to the given framebuffer"""
    
    x1, y1, depth1 = v1.get_screen_coordinates()
    x2, y2, depth2 = v2.get_screen_coordinates()
    x3, y3, depth3 = v3.get_screen_coordinates()

    col1 = v1.get_color()
    col2 = v2.get_color()
    col3 = v3.get_color()

    # calc triangle area * 2
    a = ((x3-x1)*(y2-y1) - (x2-x1)*(y3-y1))

    if not np.isclose(a, 0):
        # Swap order of clockwise triangle to make them counter-clockwise
        if a < 0:
            t = x2
            x2 = x3 
            x3 = t

            t = y2
            y2 = y3
            y3 = t

            t = depth2
            depth2 = depth3
            depth3 = t

            t = col2
            col2 = col3
            col3 = t

        ### STUDENT CODE
        # TODO 3: Implement this function.
        # HINT:   Don't forget to implement the function lineEq!
        #         Read the instructions and tutorial.py for further explanations!
        # BONUS:  Solve this task without using loops.

        # TODO: Use mix properly in linerasterize
        # TODO: Vectorize this class

        # Define needed variables
        pos1 = np.array([x1[0], y1[0]])
        pos2 = np.array([x2[0], y2[0]])
        pos3 = np.array([x3[0], y3[0]])

        # Compute line parameters
        e1 = pos3 - pos2
        e2 = pos1 - pos3
        e3 = pos2 - pos1
        n1 = np.array([-e1[1], e1[0]])
        n2 = np.array([-e2[1], e2[0]])
        n3 = np.array([-e3[1], e3[0]])
        c1 = -(n1[0] * x2[0] + n1[1] * y2[0])
        c2 = -(n2[0] * x3[0] + n2[1] * y3[0])
        c3 = -(n3[0] * x1[0] + n3[1] * y1[0])

        # Precompute f values
        f1 = 1 / line_eq(n1[0], n1[1], c1, x1, y1)
        f2 = 1 / line_eq(n2[0], n2[1], c2, x2, y2)
        f3 = 1 / line_eq(n3[0], n3[1], c3, x3, y3)
        
        # Define bounding box
        xmin = int(np.floor(np.min([x1, x2, x3])))
        xmax = int(np.ceil(np.max([x1, x2, x3])))
        ymin = int(np.floor(np.min([y1, y2, y3])))
        ymax = int(np.ceil(np.max([y1, y2, y3])))

        # Compute meshgrid
        x_vec = np.arange(xmin, xmax + 1, 1)
        y_vec = np.arange(ymin, ymax + 1, 1)
        X,Y = np.meshgrid(x_vec, y_vec)

        # Determine distances from edges and determine if pixel is inside
        vec_line_eq = np.vectorize(line_eq)
        d1 = vec_line_eq(n1[0], n1[1], c1, X, Y)
        d2 = vec_line_eq(n2[0], n2[1], c2, X, Y)
        d3 = vec_line_eq(n3[0], n3[1], c3, X, Y)
        is_inside = (d1 <= 0) & (d2 <= 0 ) & (d3 <= 0)

        # Compute all barycentric coordinates
        alpha = d1[is_inside] * f1
        beta = d2[is_inside]  * f2
        gamma = d3[is_inside]  * f3
        # Add axes to perform barycentric mixing correctly
        alpha = alpha[:, np.newaxis]
        beta = beta[:, np.newaxis]
        gamma = gamma[:, np.newaxis]

        # Interpolate color and depth
        col = MeshVertex.barycentric_mix(col1, col2, col3, alpha, beta, gamma)
        depth = MeshVertex.barycentric_mix(depth1, depth2, depth3, alpha, beta, gamma)

        # Flatten coordinates and remove axis from dept
        flattened_X = X[is_inside].flatten()
        flattened_Y = Y[is_inside].flatten()
        depth = depth[:,0]

        # Set pixel
        framebuffer.set_pixel(flattened_X,flattened_Y, depth, col)

        ### END STUDENT CODE

