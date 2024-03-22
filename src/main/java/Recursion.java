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
    public static void backtracked(char[] str, int start, List<String> ans){
        // for output
        if (start == str.length - 1){
            ans.add(new String(str));
            return; 
        }
        System.out.println(start + "   " + new String(str));
        // iterate for fill next match one
        // f(cat, 0) = f([c]at,1) + f([a]ct, 1) + f([t]ac, 1)
        for (int i = start; i < str.length; i++) {
            // 非重复的优化：排序加上额外的判断 if (visited[i] || (i > 0 && arr[i] == arr[i - 1] && !visited[i - 1])) {
            // swap
            swap(str, i, start);
            // do search 
            backtracked(str, start + 1, ans);
            // unswap
            swap(str, i, start);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        List<String> ans = new ArrayList<>();
        String str = "cat";
        backtracked(str.toCharArray(), 0, ans);
        System.out.println(ans.toString());
    }
}
