import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;
import exercise_framework.util.solver.Solver;

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

    // Implementieren Sie hier Ihre Lösung mit der Polynomialzeitreduktion.
    public boolean findExactCover(boolean[][] sets, Solver solver, boolean[] chosenSets) {
        StringBuilder sb = new StringBuilder();

        // Translate problem
        // Compute number of clauses needed
        int numOfClauses = 0;
        for (int u = 0; u < sets[0].length; u++) {
            numOfClauses++;
            for (int i = 0; i < sets.length; i++) {
                for (int j = (i + 1); j < sets.length; j++) {
                    if (sets[i][u] && sets[j][u]) {numOfClauses++;}
                }
            }
        }

        // Add dimacs header
        sb.append("p cnf " + sets.length + " " + numOfClauses + "\n");

        // For all u element of U, the following conditions have to hold
        for (int u = 0; u < sets[0].length; u++) {
            // For all sets that contain u at least one of them has to be contained in the set of chosenSets S
            for (int i = 0; i < sets.length; i++) {
                if (sets[i][u]) {
                    sb.append((i + 1) + " ");
                }
            }
            sb.append("0\n");

            // If one set that contains u is chosen, all others cannot be chosen
            for (int i = 0; i < sets.length; i++) {
                for (int j = (i + 1); j < sets.length; j++) {
                    if (sets[i][u] && sets[j][u]) {
                        sb.append(-(i + 1) + " " + -(j + 1) + " 0\n");
                    }
                }
            }
        }

        String result = solver.solve(sb.toString());

        // Translate result to boolean
        if (result.isEmpty()) {
            return false;
        } else {
            String[] variables = result.split(" ");
            for (String variable : variables) {
                int value = Integer.parseInt(variable);
                if (value != 0) {
                    chosenSets[Math.abs(value) - 1] = value >= 0;
                }
            }
            return true;
        }
    }    
}
