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

    public static void main(String[] args){

    }
}
