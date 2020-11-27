import java.util.Vector;

public class Kmp {

    public static int[] getNext(String pattern) {
        int n = pattern.length();
        int[] next = new int[n];
        int j = 0;
        if (n > 0) {
            next[0] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (;pattern.charAt(i) != pattern.charAt(j) && j > 0;) {
                j = next[j-1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int strStr(String haystack, String needle) {
        if(needle.length()==0) {
            return 0;
        }
        int[] next = getNext(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            for (;haystack.charAt(i)!= needle.charAt(j) && j > 0;) {
                j = next[j-1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j== needle.length()) {
                return i-j+1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
//        System.out.println("atty");
//        int[] ans = getNext("ababc");
//        for (int i = 0; i < ans.length; i++) {
//            System.out.print(ans[i] + " ");
//        }
        System.out.println(strStr("hello","ll"));
    }
}
