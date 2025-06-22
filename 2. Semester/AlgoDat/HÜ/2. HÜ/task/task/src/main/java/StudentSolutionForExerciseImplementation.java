import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;

import exercise.graph.Graph;
import exercise.Heuristic;

/**
 * A class intended for students to implement their solutions in.
 */
public class StudentSolutionForExerciseImplementation implements StudentSolutionForExercise {

    private class IntegerListNode {

        private int element;
        private IntegerListNode next;

        public IntegerListNode(int element) {
            this.element = element;
            this.next = null;
        }

        public IntegerListNode next() {
            // May return null
            return next;
        }

        public void setNext(IntegerListNode next) {
            this.next = next;
        }

        public int element() {
            return element;
        }
    }

    private class IntegerList {

        private IntegerListNode head;
        private int size;

        public IntegerList() {
            size = 0;
            head = null;
        }

        public void addInFront(int element) {
            if (head == null) {
                head = new IntegerListNode(element);
            } else {
                IntegerListNode newHead = new IntegerListNode(element);
                newHead.setNext(head);
                this.head = newHead;
            }
            size++;
        }

        public int removeFromFront() {
            if (head == null) {
                return -1;
            } else {
                size--;
                IntegerListNode head = this.head;
                this.head = head.next();
                return head.element();
            }
        }

        public int size() {
            return size;
        }
    }


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

    private int[] addFirstToList(int[] priority, int element) {
        int[] newPriority = new int[priority.length];
        for (int i = 0; i < priority.length; i++) {
            if (priority[i] != 0) {
                newPriority[i + 1] = priority[i];
            }
        }
        newPriority[0] = element;
        return newPriority;
    }

    private int[] removeFirstFromList(int[] priority) {
        int[] newPriority = new int[priority.length];
        for (int i = 1; i < priority.length; i++) {
            if (priority[i] != 0) {
                newPriority[i - 1] = priority[i];
            }
        }
        return newPriority;
    }

    private int[] getListElements(int[] listPriority) {
        int number = 0;
        for (int element : listPriority) {
            if (element != 0) {
                number++;
            }
        }

        int[] elements = new int[number];
        int j = 0;
        for (int k : listPriority) {
            if (k != 0) {
                elements[j++] = k;
            }
        }

        return elements;
    }

    // Implementieren Sie hier einen Algorithmus der ueberprueft, ob der Graph azyklisch ist.
    public boolean isAcyclic(Graph g) {
        int[] vertices = g.getVertices();
        int maxId = 0;
        for (int vertex : vertices) {if (vertex > maxId) {maxId = vertex;}}

        int[] count = new int[maxId + 1];
        IntegerList list = new IntegerList();

        for (int i = 0; i < maxId + 1; i++) {
            count[i] = 0;
        }
        for (int vertex : vertices) {
            for (int successor : g.getSuccessors(vertex)) {
                count[successor] += 1;
            }
        }
        for (int vertex : vertices) {
            if (count[vertex] == 0) {
                list.addInFront(vertex);
            }
        }
        int counter = 0;

        while (list.size() != 0) {
            int vertex = list.removeFromFront();
            counter++;
            for (int successor : g.getSuccessors(vertex)) {
                count[successor] -= 1;
                if (count[successor] == 0) {
                    list.addInFront(successor);
                }
            }
        }
        return counter == vertices.length;
    }

    // Implementieren Sie hier die erste Heuristik.
    public double heuristic1(Graph g, int vertex) {
        return g.inDegree(vertex) + (double) g.outDegree(vertex);
    }

    // Implementieren Sie hier die zweite Heuristik.
    public double heuristic2(Graph g, int vertex) {
        return g.inDegree(vertex) * (double) g.outDegree(vertex);
    }

    // Implementieren Sie hier die dritte Heuristik.
    public double heuristic3(Graph g, int vertex) {
        return g.inDegree(vertex) + (double) g.outDegree(vertex) -
                0.3 * Math.abs(g.inDegree(vertex) - (double) g.outDegree(vertex));
    }

    // Implementieren Sie hier optional eine vierte Heuristik.
    public double heuristic4(Graph g, int vertex) {
        return 0.0;
    }

    // Returns first vertex if no vertex is suitable
    private int findMaxVertex(Graph g, double[] heuristic) {
        boolean start = true;
        int maxVertex = g.getVertices()[0];
        int[] vertices = g.getVertices();
        double max = heuristic[maxVertex];

        for (int vertex : vertices) {
            double val = heuristic[vertex];
            if (val > max || start) {
                maxVertex = vertex;
                max = val;
                start = false;
            }
        }

        return maxVertex;
    }

    // Implementieren Sie hier den Greedy-Algorithmus, der eine Menge von Knoten findet ohne
    // denen der Graph azyklisch ist.
    public void greedyFeedbackVertexSet(Graph g, Heuristic h, int[] feedbackVertexSet) {
        int[] vertices = g.getVertices();

        for (int i = 0; i < feedbackVertexSet.length; i++) {
            feedbackVertexSet[i] = - 1;
        }

        int maxId = 0;
        for (int vertex : vertices) {if (vertex > maxId) {maxId = vertex;}}

        double[] heuristic = new double[maxId + 1];
        for (int vertex : vertices) {heuristic[vertex] = h.eval(g, vertex);}

        int j = 0;
        while (!isAcyclic(g)) {
            int vertex = findMaxVertex(g, heuristic);
            feedbackVertexSet[j++] = vertex;

            // Remove vertex and update heuristic array
            int[] successors = g.getSuccessors(vertex), predecessors = g.getPredecessors(vertex);
            g.removeVertex(vertex);

            for (int successor : successors) {
                heuristic[successor] = h.eval(g, successor);
            }
            for (int predecessor : predecessors) {
                heuristic[predecessor] = h.eval(g, predecessor);
            }
        }
    }
}
