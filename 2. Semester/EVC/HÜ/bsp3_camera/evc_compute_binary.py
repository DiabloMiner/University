# Copyright TU Wien (2022) - EVC: Task3
# Computer Vision Lab
# Institute of Computer Graphics and Algorithms

import numpy as np

def evc_compute_binary(input_image: np.ndarray, x: float, top: int) -> np.ndarray:
    """ evc_compute_binary computes a binary image with the specified threshold x.
    
      INPUT
      input_image ... RGB image
      x           ... scalar threshold
      top         ... if 0, the output should be inverted such that 0 becomes 1
                      and 1 becomes 0.
      OUTPUT
      result      ... binary RGB image which must contain either zeros or ones. The
                      result has to be of type float! Make sure that all three
                      channels are preserved (the operation has to be performed on
                      every channel)."""


    ### STUDENT CODE
    # TODO: Compute a binary image with the threshold x. Values less or equal
    #       to x are mapped to 0, values greater than x are mapped to 1.
    #       If top == 0 the output should be inverted such that 0 becomes 1
    #       and 1 becomes 0 (swap 0 and 1).
    # NOTE: The following line can be removed. It prevents the framework from
    #       crashing.

    
    result = np.zeros(input_image.shape)

    # mask red
    red_mask_1 = input_image[:, :, 0] <= x
    red_mask_2 = input_image[:, :, 0] > x
    result[red_mask_1] = 0.0 if top else 1.0
    result[red_mask_2] = 1.0 if top else 0.0

    # mask green
    green_mask_1 = input_image[:, :, 1] <= x
    green_mask_2 = input_image[:, :, 1] > x
    result[green_mask_1] = 0.0 if top else 1.0
    result[green_mask_2] = 1.0 if top else 0.0

    # mask blue
    blue_mask_1 = input_image[:, :, 2] <= x
    blue_mask_2 = input_image[:, :, 2] > x
    result[blue_mask_1] = 0.0 if top else 1.0
    result[blue_mask_2] = 1.0 if top else 0.0

    result.astype(float)

    ### END STUDENT CODE


    return result
