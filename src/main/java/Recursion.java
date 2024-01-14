import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args){

    }
}
