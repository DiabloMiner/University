import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;
import exercise.Point;
import exercise.ClosestPair;

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

    // Implementieren Sie hier Ihre Lösung für die Maximumsuche
    public int findMax(int[] numbers) {
        if (numbers == null || numbers.length <= 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    private double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    // Implementieren Sie hier Ihre Lösung für das dichteste Punktepaar
    public void findClosestPair(Point[] points, ClosestPair closestPair) {
        int n = points.length;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dist(points[i], points[j]) < min) {
                    min = dist(points[i], points[j]);
                    closestPair.setPoint1(points[i]);
                    closestPair.setPoint2(points[j]);
                }
            }
        }
    }

    // Implementieren Sie hier Ihre Lösung für die Teilsummen
    public boolean hasSubsetSum(int sum, int[] numbers) {
        // Search over all subsets of current numbers if they are like sum (only if numbers.length == 1)
        if (numbers.length == 1) {
            return numbers[0] == sum;
        } else {
            // Remove first element from numbers and call recursive subset sum again
            int[] newNumbers = new int[numbers.length - 1];
            for (int i = 0; i < newNumbers.length; i++) {
                newNumbers[i] = numbers[i + 1];
            }

            return numbers[0] == sum || hasSubsetSum(sum, newNumbers) || hasSubsetSum(sum - numbers[0], newNumbers);
        }
    }
}
