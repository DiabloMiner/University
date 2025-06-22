# Copyright TU Wien (2022) - EVC: Task5
# Institute of Computer Graphics and Algorithms.

import numpy as np


class MeshVertex:

    def __init__(self, mesh, index):
        """initializes mesh vertex"""

        self.mesh = mesh
        self.index = np.array(index, dtype=int)

    def get_position(self):
        """returns position of the mesh vertex"""
        return self.mesh.V_position[self.index]

    def get_color(self):
        """returns color of the mesh vertex"""

        return self.mesh.V_color[self.index]

    def get_screen_coordinates(self):
        """returns screen position of the mesh vertex"""

        x = self.mesh.V_screen_position[self.index, 0]
        y = self.mesh.V_screen_position[self.index, 1]
        z = self.mesh.V_screen_position[self.index, 2]
        return x, y, z

    @staticmethod
    def mix(a : np.ndarray, b : np.ndarray, t : np.ndarray) -> np.ndarray:
        """Interpolates the line defined by a,b at position t and returns the result."""
        
        ### STUDENT CODE
        # TO DO 3: Implement the interpolation of the line defined by a and b, the interpolation coefficient is given by t.
        
        # compute res in a vectorized manner by computing per element factors
        total_dist = np.sqrt((b - a) ** 2)
        current_dist = np.sqrt((t - a) ** 2)
        res = current_dist / total_dist

        # clamp per element to ensure that t is in [0,1]
        res = np.maximum(np.repeat([0], len(t)), np.minimum(np.repeat([1], len(t)), res))

        ### END STUDENT CODE

        return res

    @staticmethod
    def barycentric_mix(a : np.ndarray, b : np.ndarray, c : np.ndarray, alpha : int, beta : int, gamma : int) -> np.ndarray:
        """Interpolates the triangle defined by a,b,c at barycentric coordinates alpha, beta, gamma and returns the result."""

        ### STUDENT CODE
        # TO DO 3: Implement the barycentric interpolation of the triangle defined by a, b, c. The interpolation coefficients are given by alpha, beta, gamma.

        res = a * alpha + b * beta + c * gamma

        ### END STUDENT CODE

        return res

