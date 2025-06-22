# Copyright TU Wien (2022) - EVC: Task 5
# Institute of Computer Graphics and Algorithms.

import numpy as np
from numpy.matlib import repmat

from Mesh import Mesh
from Framebuffer import Framebuffer
from MeshVertex import MeshVertex

def line_rasterization(mesh : Mesh, framebuffer : Framebuffer):
    """ iterates over all faces of mesh and draws lines between
        their vertices.
        mesh                  ... mesh object to rasterize
        framebuffer           ... framebuffer"""

    for i in range(mesh.faces.shape[0]):
        for j in range(mesh.faces[i][0]):
            i, j = np.array(i).reshape(np.asarray(i).size), np.array(j).reshape(np.asarray(j).size)

            v1 = mesh.get_face(i).get_vertex(j)
            v2 = mesh.get_face(i).get_vertex(np.remainder(j + 1, mesh.faces[i]))
            draw_line(framebuffer, v1, v2)

def draw_line(framebuffer : Framebuffer, v1 : MeshVertex, v2 : MeshVertex):
    """ draws a line between v1 and v2 into the framebuffer using the
        DDA algorithm.
        framebuffer           ... framebuffer
        v1                    ... vertex 1
        v2                    ... vertex 2"""

    x1, y1, depth1 = v1.get_screen_coordinates()
    x2, y2, depth2 = v2.get_screen_coordinates()

    ### STUDENT CODE
    ### TO DO 1: Implement the DDA algorithms to draw a line from v1 to v2 to the given Framebuffer

    dy = float(y2) - float(y1)
    dx = float(x2) - float(x1)#

    # avoid division through zero
    epsilon = 0.00000000001
    if (dx >= -epsilon and dx <= epsilon):
        if (dx == 0):
            dx = epsilon
        dx = np.sign(dx) * epsilon

    m = (dy) / (dx)

    if (m <= 1 and m >= -1):
        # computing basic coordinates (para is used as a basis for computing y_vec - analogous to index k in an array)
        para = np.arange(start=0,stop=(int(np.abs(np.ceil(dx))))+np.abs(np.sign(dx)))
        x_vec = np.arange(x1,x2+np.sign(dx), np.sign(dx))
        y_vec = np.round(y1 + para * np.abs(m) * np.sign(int(dy)))

        # compute t and interpolate depth
        t = MeshVertex.mix(np.repeat(x1,len(x_vec)), np.repeat(x2,len(x_vec)), x_vec)
        depth = depth1 * (1 - t) + depth2 * t
        # change t to be a column vector for interpolating the color
        t = t[:, np.newaxis]
        color = (1 - t) * v1.get_color() + t * v2.get_color()

        # setting pixels
        framebuffer.set_pixel(np.array(x_vec),np.array(y_vec),depth, color)
    else:
        # computing basic coordinates (para is used as a basis for computing x_vec - analogous to index k in an array)
        para = np.arange(start=0,stop=(int(np.abs(np.ceil(dy))))+np.abs(np.sign(dy)))
        y_vec = np.arange(y1,y2+np.sign(dy), np.sign(dy))
        x_vec = np.round(x1 + para * (1 / np.abs(m) * np.sign(int(dx))))

        # compute t and interpolate depth
        t = MeshVertex.mix(np.repeat(y1,len(x_vec)), np.repeat(y2,len(x_vec)), y_vec)
        depth = depth1 * (1 - t) + depth2 * t
        # change t to be a column vector for interpolating the color
        t = t[:, np.newaxis]
        color = v1.get_color() * (1 - t) + v2.get_color() * t

        # setting pixels
        framebuffer.set_pixel(np.array(x_vec),np.array(y_vec),depth, color)

        
    
    ### END STUDENT CODE

