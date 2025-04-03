from typing import Tuple
import numpy as np
    
def define_structures() -> Tuple[np.ndarray, np.ndarray, np.ndarray]:
    """
        Defines the two vectors v1 and v2 as well as the matrix M determined by your matriculation number.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    #                 ABCDEFGH
    # Matrikelnummer: 12418810

    v1 = np.array(object=[1, 1, 4])
    v2 = np.array(object=[8, 2, 8])
    M = np.array(object=[(1,2,4), (2,1,1), (8, 0, 8)])
    
    ### END STUDENT CODE

    return v1, v2, M

def sequence(M : np.ndarray) -> np.ndarray:
    """
        Defines a vector given by the minimum and maximum digit of your matriculation number. Step size = 0.25.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

	# NOTE: The following lines can be removed. They prevent the framework
    #       from crashing.

    # Find min and max of my matrikelNummer
    matrikelNummer = (1, 2, 4, 1, 8, 8, 1, 0)
    min = 10.0
    max = -10.0
    for i in range(0, len(matrikelNummer)):
        digit = matrikelNummer[i]
        if (digit < min):
            min = digit
        if (digit > max):
            max = digit

    # Create a vector with elements in a range from min to max with a stepsize of 0.25
    M = np.arange(min, max + 0.25, 0.25)

    result = M

    ### END STUDENT CODE


    return result

def matrix(M : np.ndarray) -> np.ndarray:
    """
        Defines the 15x9 block matrix as described in the task description.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    # Create matrix filled with zero
    zeroMat = np.zeros(M.shape)
    # Create the two types of needed rows
    type1 = np.hstack(tup=[M, zeroMat, M])
    type2 = np.hstack(tup=[zeroMat, M, zeroMat])
    # Stack the rows according to the given pattern
    r = np.vstack(tup=[type1, type2, type1, type2, type1])

    ### END STUDENT CODE

    return r


def dot_product(v1:np.ndarray, v2:np.ndarray) -> float:
    """
        Dot product of v1 and v2.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

	# NOTE: The following lines can be removed. They prevent the framework
    #       from crashing.

    r = 0
    for i in range(0, len(v1)):
        r += v1[i] * v2[i]


    ### END STUDENT CODE

    return r

def cross_product(v1:np.ndarray, v2:np.ndarray) -> np.ndarray:
    """
        Cross product of v1 and v2.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    x = v1[1]*v2[2] - v1[2]*v2[1]
    y = v1[2]*v2[0] - v1[0]*v2[2]
    z = v1[0]*v2[1] - v1[1]*v2[0]

    # r = np.zeros(shape=v1.shape)
    r = np.array(object=[x, y, z])
    ### END STUDENT CODE

    return r

def vector_X_matrix(v:np.ndarray, M:np.ndarray) -> np.ndarray:
    """
        Defines the vector-matrix multiplication v*M.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    r = np.zeros(v.shape[0])
    for i in range(0, len(r)):
        sum = 0
        for j in range(0, M.shape[1]):
            sum += v[j] * M[j, i]
        r[i] = sum

    ### END STUDENT CODE

    return r

def matrix_X_vector(M:np.ndarray, v:np.ndarray) -> np.ndarray:
    """
        Defines the matrix-vector multiplication M*v.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    r = np.zeros(v.shape[0])
    for i in range(0, len(r)):
        sum = 0
        for j in range(0, M.shape[1]):
            sum += M[i, j] * v[j]
        r[i] = sum
            
    ### END STUDENT CODE

    return r

def matrix_X_matrix(M1:np.ndarray, M2:np.ndarray) -> np.ndarray:
    """
        Defines the matrix multiplication M1*M2.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    r = np.zeros((M1.shape[0], M2.shape[1]))
    for i in range(0, M1.shape[0]):
        for k in range(0, M2.shape[1]):
            sum = 0
            for j in range(0, M2.shape[0]):
                sum += M1[i, j] * M2[j, k]
            r[i, k] = sum
            
    ### END STUDENT CODE

    return r

def matrix_Xc_matrix(M1:np.ndarray, M2:np.ndarray) -> np.ndarray:
    """
        Defines the element-wise matrix multiplication M1*M2 (Hadamard Product).
    """
    ### STUDENT CODE
    # TODO: Implement this function.
    r = np.zeros((M1.shape[0], M2.shape[1]))
    for i in range(0, M1.shape[0]):
        for j in range(0, M2.shape[1]):
            r[i, j] = M1[i, j] * M2[i, j]

    ### END STUDENT CODE
    

    return r
