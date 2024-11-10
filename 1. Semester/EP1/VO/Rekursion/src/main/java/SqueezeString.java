public class SqueezeString {

    // s != null
    private static String squeeze(String s, char c) {
        if (s.isEmpty()) {
            return s;
        }
        if (s.charAt(0) == c) {
            return squeeze(s.substring(1), c);
        }
        return s.charAt(0) + squeeze(s.substring(1), c);
    }

    public static void main(String[] args) {
        System.out.println(squeeze("abcbcbdbf", 'b'));
        System.out.println(squeeze("Hallo EP1 World", 'l'));
        System.out.println(squeeze("aaaa", 'a'));
        System.out.println(squeeze("a b c d", ' '));
    }

}