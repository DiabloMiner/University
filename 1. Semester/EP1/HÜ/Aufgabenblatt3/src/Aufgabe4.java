/*
    Aufgabe 4) Rekursion
*/
public class Aufgabe4 {

    private static boolean isStartAndEndSeq(String text, String sequence) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // Base Cases: Check if text can actually have two sequences otherwise immediately return false
        if (text.length() == sequence.length()) {
            return text.isEmpty();
        } else if (text.length() < 2 * sequence.length()) {
            return false;
        } else if (text.length() == 2 * sequence.length()) {
            // At this point text should consist of sequence repeated two times
            // Therefore check if the first chars of the first and second half of text match the first char of sequence
            // And then cut all first chars off and return to this base case until text and sequence are empty

            if (sequence.charAt(0) == text.charAt(0) && sequence.charAt(0) == text.charAt(sequence.length())) {
                String cutoffString = text.substring(1, sequence.length()) + text.substring(sequence.length() + 1);
                return isStartAndEndSeq(cutoffString, sequence.substring(1));
            } else {
                return false;
            }
        }

        // Recursive case: Remove all chars that might be between the two sequences
        String shortenedString = text.substring(0, sequence.length()) + text.substring(text.length() - sequence.length());
        return isStartAndEndSeq(shortenedString, sequence); //Zeile kann geändert oder entfernt werden.
    }

    public static void main(String[] args) {

        System.out.println(isStartAndEndSeq("", "1"));
        System.out.println(isStartAndEndSeq("AA", "A"));
        System.out.println(isStartAndEndSeq("ABBAB", "AB"));
        System.out.println(isStartAndEndSeq("ABBBA", "AB"));
        System.out.println(isStartAndEndSeq("ottootto", "otto"));
        System.out.println(isStartAndEndSeq("otto", "otto"));
        System.out.println(isStartAndEndSeq("ottotto", "otto"));
        System.out.println(isStartAndEndSeq("ottoottt", "otto"));
        System.out.println(isStartAndEndSeq("test1234test", "test"));
        System.out.println(isStartAndEndSeq("NEN", "NEEN"));

        //DIE NACHFOLGENDEN ZEILEN SIND ZUM TESTEN DER METHODE.
        //**********************************************************************
        assert (isStartAndEndSeq("", "1") == false);
        assert (isStartAndEndSeq("AA", "A") == true);
        assert (isStartAndEndSeq("ABBAB", "AB") == true);
        assert (isStartAndEndSeq("ABBBA", "AB") == false);
        assert (isStartAndEndSeq("ottootto", "otto") == true);
        assert (isStartAndEndSeq("otto", "otto") == false);
        assert (isStartAndEndSeq("ottotto", "otto") == false);
        assert (isStartAndEndSeq("ottoottt", "otto") == false);
        assert (isStartAndEndSeq("test1234test", "test") == true);
        assert (isStartAndEndSeq("NEN", "NEEN") == false);
        //**********************************************************************
    }
}
