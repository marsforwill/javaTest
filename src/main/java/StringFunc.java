import java.util.*;

public class StringFunc {

    class Node {
        public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;
    }

    // use append to add char with StringBuilder
    void decode(String s, Node root) {
        StringBuilder sb = new StringBuilder();
        Node cur = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1'){
                    cur = cur.right;
                }else if (c == '0'){
                    cur = cur.left;
                }
                
            if (cur.left == null && cur.right == null){
                // ans = ans + cur.data;
                sb.append(cur.data);
                cur = root;
            }
            // System.out.println(sb + "---" + c + "---" + i);
        }
       System.out.println(sb);
    }

    // https://www.hackerrank.com/challenges/sherlock-and-valid-string/copy-from/364735865
    // char character
    public static String isValid(String s) {
    // Write your code here
        char[] sc = s.toCharArray();
        int[] f = new int[26];
        for(char c : sc){
            f[c-'a']++;
        }
        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i]);
        }
        if(sc.length == 1){
            return ("YES");
        }
        // iter all the 26 char, to see how many difference char need to delete for keep same as char[i]
        for(int i = 0;i < 26;i++){
            long need = 0;
            for(int j = 0;j < 26;j++){
                if(f[i] < f[j]){
                    need += f[j]-f[i];
                }else if(f[j] < f[i]){
                    need += f[j];
                }
                System.out.println(need);
            }
            if(need <= 1){
               return "YES";
            }
        }
        return "NO";
    }


    // KMP 
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

    // https://leetcode.cn/problems/text-justification/ 琐碎繁杂的字符串处理计算
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList();
        for (int i = 0; i < words.length; i++) {
          int count = words[i].length();
          int j = i+1;
          while(j<words.length && count + words[j].length() + 1 <= maxWidth){
            count += words[j].length() + 1;
            j++;
          }
          j--;
          // 变量迭代 每一行取数组里面[i:j]
          StringBuilder line = new StringBuilder();
          System.out.println("i:" + i + "j:" + j);
          if(j < words.length - 1){ // 最后一行规则不同
            int space = maxWidth - count;
            if (j == i){ // 只有一个单词的edge case
                line.append(words[i]);
                line.append(" ".repeat(maxWidth - words[i].length()));
            } else {
                int avg = space/(j-i);
                int extra = space%(j-i);
                for (int k = i; k <= j; k++) {
                    line.append(words[k]);
                    if(k != j){ // 最后一个单词无空格
                        if (k < i + extra){
                            line.append(" ".repeat(avg+2));
                        } else {
                            line.append(" ".repeat(avg+1));
                        }
                    }
                }
            }
          } else {
            for (int k = i; k < words.length; k++) {
              line.append(words[k]);
              if(k != words.length -1){
                 line.append(" ");
              }
            }
            line.append(" ".repeat(maxWidth - line.length()));
          }
          System.out.println(line.toString());
          ans.add(line.toString());
          i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println("atty");
//        int[] ans = getNext("ababc");
//        for (int i = 0; i < ans.length; i++) {
//            System.out.print(ans[i] + " ");
//        }
        // System.out.println(strStr("hello","ll"));
        System.out.println(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
    }
}
