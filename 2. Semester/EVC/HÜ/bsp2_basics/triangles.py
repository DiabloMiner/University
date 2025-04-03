from typing import List, Tuple

import numpy as np

def define_triangle() -> Tuple[np.ndarray, np.ndarray, np.ndarray]:
    ### STUDENT CODE
    # TODO: Implement this function.

    #                 ABCDEFGH
    # Matrikelnummer: 12418810

    P1 = np.array(object=[(1 + 4), -(1 + 1), -(1 + 8)])
    P2 = np.array(object=[-(1 + 1), -(1 + 2), -(1 + 0)])
    P3 = np.array(object=[-(1 + 1), -(1 + 8), -(1 + 2)])
    ### END STUDENT CODE

    return P1, P2, P3

def define_triangle_vertices(P1:np.ndarray, P2:np.ndarray, P3:np.ndarray) -> Tuple[np.ndarray, np.ndarray, np.ndarray]:
    ### STUDENT CODE
    # TODO: Implement this function.

    P1P2 = np.subtract(P2, P1)
    P2P3 = np.subtract(P3, P2)
    P3P1 = np.subtract(P1, P3)
    ### END STUDENT CODE

    return P1P2, P2P3, P3P1

def compute_lengths(P1P2:np.ndarray, P2P3:np.ndarray, P3P1:np.ndarray) -> List[float]:
    ### STUDENT CODE
    # TODO: Implement this function.

	# NOTE: The following lines can be removed. They prevent the framework
    #       from crashing.

    # Compute norm for P1P2
    norm1 = 0
    for i in range(0, len(P1P2)):
        norm1 += P1P2[i] ** 2
    norm1 = np.sqrt(norm1)

    # Compute norm for P2P3
    norm2 = 0
    for i in range(0, len(P2P3)):
        norm2 += P2P3[i] ** 2
    norm2 = np.sqrt(norm2)

    # Compute norm for P3P1
    norm3 = 0
    for i in range(0, len(P3P1)):
        norm3 += P3P1[i] ** 2
    norm3 = np.sqrt(norm3)

    norms = [norm1, norm2, norm3]
    ### END STUDENT CODE

    return norms

def compute_normal_vector(P1P2:np.ndarray, P2P3:np.ndarray, P3P1:np.ndarray) -> Tuple[np.ndarray, np.ndarray]:
    ### STUDENT CODE
    # TODO: Implement this function.

    n = np.cross(P1P2, -P3P1)
    n_normalized = np.divide(n, np.linalg.norm(n))
    ### END STUDENT CODE

    return n, n_normalized

def compute_triangle_area(n:np.ndarray) -> float:
    ### STUDENT CODE
    # TODO: Implement this function.

    # This works because A = a * h_a and h_a / b = sin(theta) and |n| = a * b * sin(theta)
    #  where theta is the angle between a and b and a is P1P2 and b is -P3P1
    area = 0.5 * np.linalg.norm(n)

    ### END STUDENT CODE

    return area

def compute_angles(P1P2:np.ndarray,P2P3:np.ndarray,P3P1:np.ndarray) -> Tuple[float, float, float]:
    ### STUDENT CODE
    # TODO: Implement this function.

    # This works because |a x b| = |a| * |b| * sin(theta) where theta is the angle between a and b
    alpha = np.rad2deg(np.arcsin(np.linalg.norm(np.cross(P1P2, -P3P1)) / (np.linalg.norm(P1P2) * np.linalg.norm(-P3P1))))
    beta = np.rad2deg(np.arcsin(np.linalg.norm(np.cross(P2P3, -P1P2)) / (np.linalg.norm(P2P3) * np.linalg.norm(-P1P2))))
    gamma = np.rad2deg(np.arcsin(np.linalg.norm(np.cross(P3P1, -P2P3)) / (np.linalg.norm(P3P1) * np.linalg.norm(-P2P3))))

    ### END STUDENT CODE

    return alpha, beta, gamma

