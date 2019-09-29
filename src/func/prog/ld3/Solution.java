package func.prog.ld3;

public class Solution {

    static boolean palindrome(String s) {
        s = s.toLowerCase();
        if (s.length() == 1 || (s.length() == 2 && s.charAt(0) == s.charAt(1))) {
            return true;
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return palindrome(s.substring(1, s.length()-1));
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String[] input = {
                "abcba",
                "argentinamanitnegra",
                "Sapalsarītadēdatīraslapas",
                "abccb",
                "stirna",
        };

        for (String s : input){
            System.out.println(s + " | " + palindrome(s));
        }
    }
}
