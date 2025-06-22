import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;

/**
 * A class intended for students to implement their solutions in.
 */
public class StudentSolutionForExerciseImplementation implements StudentSolutionForExercise {

    /**
     * Collects and returns information about the student working on solving the instance sets.
     * This method is called automatically.
     * @return First name, last name, and matriculation number collected in a {@link StudentInformation} instance.
     */
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Jakob", // Vorname
                "Plangger", // Nachname
                "12418810" // Matrikelnummer
        );
    }

    // Start, end and node are indices given in inorder traversal
    private int recursiveConstructionPreorder(int[] inOrderTraversal, int[] preOrderTraversal, int[] reconstructedTree, int start, int end, int node, int preorderNode, int nodeIndex) {
        int lastLeftChild = preorderNode;

        if (node > start) {
            int leftChild = preorderNode + 1;
            reconstructedTree[2 * nodeIndex + 1] = preOrderTraversal[leftChild];
            lastLeftChild = recursiveConstructionPreorder(inOrderTraversal, preOrderTraversal, reconstructedTree, start, node - 1, findIndex(inOrderTraversal, preOrderTraversal[leftChild]), leftChild, 2 * nodeIndex + 1);
        }
        if (node < end) {
            // lastLeftChild is an index given in preorder traversal
            int rightChild = lastLeftChild + 1;
            reconstructedTree[2 * nodeIndex + 2] = preOrderTraversal[rightChild];
            lastLeftChild = recursiveConstructionPreorder(inOrderTraversal, preOrderTraversal,reconstructedTree, node + 1, end, findIndex(inOrderTraversal, preOrderTraversal[rightChild]), rightChild, 2 * nodeIndex + 2);
        }
        return lastLeftChild;
    }

    // may return -1 if array does not contain element
    private int findIndex(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    // Implementieren Sie hier Ihre Rekonstruktion aus gegebener In- und Präordnung
    public void reconstructFromInAndPreOrder(int[] inOrderTraversal, int[] preOrderTraversal, int[] reconstructedTree) {
        int start = 0, end = inOrderTraversal.length - 1;
        int node = findIndex(inOrderTraversal, preOrderTraversal[0]);
        reconstructedTree[0] = preOrderTraversal[0];
        recursiveConstructionPreorder(inOrderTraversal, preOrderTraversal, reconstructedTree, start, end, node, 0, 0);
    }

    // Start, end and node are indices given in inorder traversal
    private int recursiveConstructionPostorder(int[] inOrderTraversal, int[] postOrderTraversal, int[] reconstructedTree, int start, int end, int node, int postorderNode, int nodeIndex) {
        int firstRightChild = postorderNode;

        if (node < end) {
            int rightChild = postorderNode - 1;
            reconstructedTree[2 * nodeIndex + 2] = postOrderTraversal[rightChild];
            firstRightChild = recursiveConstructionPostorder(inOrderTraversal, postOrderTraversal, reconstructedTree, node + 1, end, findIndex(inOrderTraversal, postOrderTraversal[rightChild]), rightChild, 2 * nodeIndex + 2);
        }
        if (node > start && firstRightChild > 0) {
            // firstRightChild is an index given in postorder traversal
            int leftChild = firstRightChild - 1;
            reconstructedTree[2 * nodeIndex + 1] = postOrderTraversal[leftChild];
            firstRightChild = recursiveConstructionPostorder(inOrderTraversal, postOrderTraversal,reconstructedTree, start, node - 1, findIndex(inOrderTraversal, postOrderTraversal[leftChild]), leftChild, 2 * nodeIndex + 1);
        }
        return firstRightChild;
    }

    // Implementieren Sie hier Ihre Rekonstruktion aus gegebener In- und Postordnung
    public void reconstructFromInAndPostOrder(int[] inOrderTraversal, int[] postOrderTraversal, int[] reconstructedTree) {
        int start = 0, end = inOrderTraversal.length - 1;
        int node = findIndex(inOrderTraversal, postOrderTraversal[end]);
        reconstructedTree[0] = postOrderTraversal[end];
        recursiveConstructionPostorder(inOrderTraversal, postOrderTraversal, reconstructedTree, start, end, node, end, 0);
    }
}
