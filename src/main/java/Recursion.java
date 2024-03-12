import java.util.*;

public class Recursion {

     // https://www.hackerrank.com/challenges/the-power-sum/problem?isFullScreen=true 
    public static int powerSum(int X, int N) {
    // Write your code here
        int count = 0;
        for (int i = 1; i < X; i++) {
            count += canSum(i, X, N);
        }
        return count;
    }
    
    public static int canSum(int i, int x, int n){
        int rest = x - (int)Math.pow(i, n);
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        int r = 0;
        for (int j = i+1; j <rest; j++) {
            r += canSum(j, rest, n);
        }
        return r;
    }

    // 字符串全排列
    public static Set<String> f(String code){
        int length = code.length();
        if (length <= 1){
            Set<String> s = new HashSet<String>();
            s.add(code);
            return s;
        }
 
        Set<String> answer = new HashSet<String>();
        for(int i = 0; i < length; i++) {
            String rest = code.subSequence(0, i).toString() + code.subSequence(i+1, length);
            Set<String> temp = f(rest);
            for (String str : temp) {
                answer.add(code.charAt(i) + str);
            }
        }
        return answer;
    }

    // 用回溯的算法求字符串的全排列, 包含重复字符
    public static void backtracked(String str, int start, String now, List<String> ans){
        // for output
        if (start == str.length()){
            ans.add(now);
            return; 
        }

        // iterate for fill next match one
        for (int i = start; i < str.length(); i++) {
            // fill
            now = now + str.charAt(i);
            // do search 
            backtracked(str, start + 1, now, ans);
            // unfill
            now = now.substring(0, now.length() - 1);
        }
    }

    public static void backtrack(char[] arr, int start, List<String> permutations) {
        if (start == arr.length - 1) {
            permutations.add(new String(arr));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i); // Swap characters
            backtrack(arr, start + 1, permutations); // Recurse
            swap(arr, start, i); // Backtrack (restore original order)
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        List<String> ans = new ArrayList<>();
        String str = "ccat";
        backtracked("cat", 0, "", ans);
        System.out.println(ans.toString());
    }
}
