import numpy as np
import scipy.ndimage
from PIL import Image

import utils


def read_img(inp:str) -> Image.Image:
    """
        Returns a PIL Image given by its input path.
    """
    img =  Image.open(inp)
    return img

def convert(img:Image.Image) -> np.ndarray:
    """
        Converts a PIL image [0,255] to a numpy array [0,1].
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    out = np.array(img)
    out = np.divide(out, float(255))

    ### END STUDENT CODE
    return out

def switch_channels(img:np.ndarray) -> np.ndarray:
    """
        Swaps the red and green channel of a RGB image given by a numpy array.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    out = np.array(img)

    for i in range(img.shape[0]):
        for j in range(img.shape[1]):
            a = float(out[i, j, 0])
            out[i, j, 0] = float(out[i, j, 1])
            out[i, j, 1] = a

    

    ### END STUDENT CODE

    return out

def image_mark_green(img:np.ndarray) -> np.ndarray:
    """
        returns a numpy-array (HxW) with 1 where the green channel of the input image is greater or equal than 0.7, otherwise zero.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    mask = np.zeros((512, 512, 3))
    mask[:,:,0] = img[:,:,1] >= 0.7
    mask[:,:,1] = img[:,:,1] >= 0.7
    mask[:,:,2] = img[:,:,1] >= 0.7

    print(mask[0, 0])

    ### END STUDENT CODE

    return mask


def image_masked(img:np.ndarray, mask:np.ndarray) -> np.ndarray:
    """
        sets the pixels of the input image to zero where the mask is 1.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    out = np.zeros(img.shape)
    out = np.multiply(img, np.abs(np.subtract(mask, 1)))

    ### END STUDENT CODE

    return out

def grayscale(img:np.ndarray) -> np.ndarray:
    """
        Returns a grayscale image of the input. Use utils.rgb2gray().
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    out = utils.rgb2gray(img)

    ### END STUDENT CODE

    return out

def cut_and_reshape(img_gray:np.ndarray) -> np.ndarray:
    """
        Cuts the image in half (x-dim) and stacks it together in y-dim.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

    half1 = img_gray[:,:int(img_gray.shape[0] / 2)]
    half2 = img_gray[:,int(img_gray.shape[0] / 2):]
    out = np.vstack([half2, half1])


    ### END STUDENT CODE

    return out

def filter_image(img:np.ndarray) -> np.ndarray:
    """
        filters the image with the gaussian kernel given below. 
    """
    gaussian = utils.gauss_filter(5, 2)

    ### STUDENT CODE
    # TODO: Implement this function.

    out = np.zeros(img.shape)
    paddedImg = np.zeros(img.shape)
    paddedImg = np.pad(img, [[5, 5], [5, 5], [0, 0]], 'constant', constant_values=0).astype(float)

    for i in range(5, paddedImg.shape[0] - 5):
        for j in range(5, paddedImg.shape[1] - 5):
            color = np.array([0.0, 0.0, 0.0])

            imgBlock0 = paddedImg[i - 2:i + 3, j - 2:j + 3, 0]
            imgBlock1 = paddedImg[i - 2:i + 3, j - 2:j + 3, 1]
            imgBlock2 = paddedImg[i - 2:i + 3, j - 2:j + 3, 2]

            col0 = np.sum(a=(gaussian * imgBlock0), axis=None)
            col1 = np.sum(a=(gaussian * imgBlock1), axis=None)
            col2 = np.sum(a=(gaussian * imgBlock2), axis=None)

            out[i - 5, j - 5] = [col0, col1, col2]

    ### END STUDENT CODE

    return out

def horizontal_edges(img:np.ndarray) -> np.ndarray:
    """
        Defines a sobel kernel to extract horizontal edges and convolves the image with it.
    """
    ### STUDENT CODE
    # TODO: Implement this function.

	# NOTE: The following lines can be removed. They prevent the framework
    #       from crashing.

    sobel = np.array([[1, 2, 1],
                      [0, 0, 0],
                      [-1, -2, -1]])
    

    out = np.zeros(img.shape)

    print(img.shape)
    print(sobel.shape)
    out = scipy.ndimage.correlate(input=img,weights=sobel,mode='constant',cval=0)

    ### END STUDENT CODE

    return out
