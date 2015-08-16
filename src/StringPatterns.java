/**
 * Created by Phil on 8/15/2015.
 */
public class StringPatterns {

    public static String removeDuplicatePatterns(String input) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < input.length(); i++) {
            builder.append(input.charAt(i));
            for(int j = i+1; j < input.length(); j++) {
                if(input.charAt(i) == input.charAt(j)) {
                    int k;
                    for (k = 0; k < j-i; k++) {
                        if(input.charAt(i+k) != input.charAt(j+k)) {
                            break;
                        }
                    }
                    if(k == j-1) {
                        i += k;
                        break;
                    }

                }
            }
        }

        System.out.println(builder.toString());
        return null;
    }

    public static void main(String[] args) {
        StringPatterns.removeDuplicatePatterns("abcbcd");
        StringPatterns.removeDuplicatePatterns("abc3bcd");
        StringPatterns.removeDuplicatePatterns("12223334");
        StringPatterns.removeDuplicatePatterns("xxxyzyz");
        StringPatterns.removeDuplicatePatterns("xyxyz");
        StringPatterns.removeDuplicatePatterns("abcdebcde12");

    }
}
