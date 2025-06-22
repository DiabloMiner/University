import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;
import exercise.CLCSInstance;
import exercise.DynamicProgrammingTable;

import java.util.*;

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
                "Jakob Joel", // Vorname
                "Plangger", // Nachname
                "12418810" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für isFeasible()
    public boolean isFeasible(CLCSInstance instance, char[] solution) {
        // Test if solution can even contain sp
        if (solution.length < instance.getNp()) {
            return false;
        }

        // Test if sp is a subsequence of solution
        int j = 0;
        char[] sp = instance.getSp();
        for (char c : sp) {
            if (CLCSInstance.getNextOccurence(solution, c, j) == solution.length) {
                return false;
            }
            j = CLCSInstance.getNextOccurence(solution, c, j) + 1;
        }

        // Test if solution is a subsequence of s1 and s2
        int k = 0, g = 0;
        char[] s1 = instance.getS1(), s2 = instance.getS2();
        for (char c : solution) {
            if (CLCSInstance.getNextOccurence(s1, c, k) == s1.length) {
                return false;
            }
            k = CLCSInstance.getNextOccurence(s1, c, k) + 1;
            if (CLCSInstance.getNextOccurence(s2, c, g) == s2.length) {
                return false;
            }
            g = CLCSInstance.getNextOccurence(s2, c, g) + 1;
        }

        return true;
    }

    private String charArrayToString(char[] arr) {
        StringBuilder res = new StringBuilder();
        for (char c : arr) {
            res.append(c);
        }
        return res.toString();
    }


    // Implementieren Sie hier Ihre Lösung für die Erstellung der Tabelle für die Dynamische Programmierung
    public void computeDynamicProgrammingTable(CLCSInstance instance, DynamicProgrammingTable table) {
        int n1 = instance.getN1(), n2 = instance.getN2(), np = instance.getNp();
        char[] s1 = instance.getS1(), s2 = instance.getS2(), sp = instance.getSp();

        for (int k = 0; k <= np; k++) {
            for (int i = 0; i <= n1; i++) {
                for (int j = 0; j <= n2; j++) {
                    if (i == 0 || j == 0) {
                        if (k == 0) {
                            table.set(k, i, j, 0);
                        } else {
                            // Use Integer Min Value for an invalid value as this case is not possible and should not be 'used' by the code
                            table.set(k, i, j, Integer.MIN_VALUE);
                        }
                    }
                    // If a correspondence is found that is the correct element of sp[k]
                    else if (k != 0 && s1[i - 1] == s2[j - 1] && s1[i - 1] == sp[k - 1]) {
                        table.set(k, i, j, 1 + table.get(k - 1, i - 1, j - 1));
                    }
                    // If a correspondence is found but it is not the latest element of sp or k = 0
                    else if (s1[i - 1] == s2[j - 1]) {
                        table.set(k, i, j, 1 + table.get(k ,i - 1, j - 1));
                    }
                    // If no correspondence exists just take one already established sequence
                    else if (s1[i - 1] != s2[j - 1]) {
                        table.set(k, i, j, Math.max(table.get(k, i - 1, j), table.get(k, i, j - 1)));
                    }
                }
            }
        }

        System.out.print("");

    }


    // Implementieren Sie hier Ihre Lösung für das Backtracking im CLCS
    public char[] backtrackingCLCS(CLCSInstance instance, DynamicProgrammingTable table) {
        char[] s1 = instance.getS1();

        StringBuilder solution = new StringBuilder();
        int current = table.get(instance.getNp(), instance.getN1(), instance.getN2());
        int i = instance.getN1(), j = instance.getN2(), k = instance.getNp();
        while (i > 0 && j > 0 && current > 0) {
            int lowerLevel = k == 0 ? Integer.MIN_VALUE : table.get(k - 1, i - 1, j - 1);
            int currentLevel = table.get(k, i - 1, j - 1);
            int left = table.get(k, i, j - 1);
            int down = table.get(k, i - 1, j);


            if (current == left) {
                j--;
            } else if (current == down) {
                i--;
            } else if (current == currentLevel + 1) {
                solution.append(s1[i - 1]);
                i--;
                j--;
            } else if (current == lowerLevel + 1) {
                solution.append(s1[i - 1]);
                i--;
                j--;
                k--;
            }

            current = table.get(k, i, j);
        }

        return solution.reverse().toString().toCharArray();
    }


    // Implementieren Sie hier Ihre Lösung für das Backtracking im LCS
    public char[] backtrackingLCS(CLCSInstance instance, DynamicProgrammingTable table) {
        char[] s1 = instance.getS1();

        StringBuilder solution = new StringBuilder();
        int current = table.get(0, instance.getN1(), instance.getN2());
        int i = instance.getN1(), j = instance.getN2();
        while (i > 0 && j > 0 && current > 0) {
            int left = table.get(0, i, j - 1);
            int down = table.get(0, i - 1, j);

            if (left == current) {
                j--;
            } else if (down == current) {
                i--;
            } else if (left + 1 == current && down + 1 == current) {
                solution.append(s1[i - 1]);
                i--;
                j--;
            }

            current = table.get(0, i, j);
        }
        return solution.reverse().toString().toCharArray();
    }
}
