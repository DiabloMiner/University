import exercise.StudentSolutionForExercise;
import exercise.StudentInformation;

import exercise_framework.StudentSolution;
import exercise.helper.ChainElement;
import exercise.helper.HashTable;
import exercise.helper.HashTableWithChaining;
import exercise.helper.Probe;

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

    // Implementieren Sie hier Ihre Lösung für Verkettung der Überläufer
    public void insertVerkettung(HashTableWithChaining t, ChainElement chainElement, int m) {
        int index = chainElement.getKey() % m;
        if (!t.containsNoChainElement(index)) {
            ChainElement next = t.get(index);
            chainElement.setNext(next);
            t.replaceChainElement(chainElement, index);

        } else {
            t.insertChainElement(chainElement, index);
        }
    }

    // Implementieren Sie hier Ihre Lösung für die lineare Sondierung
    public int linearesSondieren(int key, int i, int m) {
        return (key % m + i) % m;
    }

    // Implementieren Sie hier Ihre Lösung für die quadratische Sondierung
    public int quadratischesSondieren(int key, int i, int m) {
        return (int) ((key % m + i * 0.5 + i * i * 0.5) % m);
    }

    // Implementieren Sie hier Ihre Lösung für Double Hashing
    public int doubleHashing(int key, int i, int m) {
        return (key + i * (1 + key % 5)) % m;
    }

    // Implementieren Sie hier Ihre Lösung für die Insert-Methode
    public void insert(HashTable t, Probe p, int key, int m) {
        int i = 0;
        while (i < m) {
            int pos = p.evaluate(key, i++);
            if (t.isFree(pos)) {
                t.insert(key, pos);
                break;
            }
        }
    }

    // Implementieren Sie hier Ihre Lösung für Verbesserung nach Brent
    public void insertVerbesserungNachBrent(HashTable t, int key, int m) {
        int i = 0;
        while (i < m) {
            int pos = (key + i++ * (1 + key % 5)) % m;
            int nextPos = (pos + (1 + key % 5)) % m;
            if (t.isFree(pos)) {
                t.insert(key, pos);
                break;
            } else if (!t.isFree(pos) && !t.isFree(nextPos)) {
                int otherKey = t.get(pos), nextOtherPos = (pos + (1 + otherKey % 5)) % m;
                if (t.isFree(nextOtherPos)) {
                    t.replace(key, pos);
                    t.insert(otherKey, nextOtherPos);
                    break;
                }
            }
        }
    }
}
