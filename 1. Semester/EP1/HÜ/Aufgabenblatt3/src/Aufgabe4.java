/*
    Aufgabe 4) Rekursion
*/
public class Aufgabe4 {

    private static boolean isStartAndEndSeq(String text, String sequence) {
        // Base Cases: Check if text can actually have two sequences otherwise immediately return false
        if (text.length() == sequence.length()) {
            return text.isEmpty();
        } else if (text.length() < 2 * sequence.length()) {
            return false;
        }

        // Recursive case:
        // Check the chars of text where first and last char of seq should be
        // If so split those chars off text (and remove all chars between start and end seq) and one off seq and return into recursion
        int secondSeqStart = text.length() - sequence.length();
        char firstSeqChar = sequence.charAt(0);
        String cutoffString = text.substring(1, sequence.length()) + text.substring(secondSeqStart + 1);

        return (firstSeqChar == text.charAt(0) && firstSeqChar == text.charAt(secondSeqStart)) && isStartAndEndSeq(cutoffString, sequence.substring(1));
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
