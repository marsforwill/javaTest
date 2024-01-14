import java.util.*;

// new PriorityQueue<>(); .peek .add .poll
public class HeapFunc {


    // https://www.hackerrank.com/challenges/jesse-and-cookies/submissions/code/363666568
    public static int cookies(int k, List<Integer> A) {
    // Write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Integer item : A) {
            pq.add(item);
        }
        int count = 0;
        while(pq.peek() < k){
            Integer num1 = pq.poll();
            if (pq.size() <= 0){
                return -1;
            }
            Integer num2 = pq.poll();
            pq.add(num1 + num2 * 2);
            count ++;
        }
        return count;
    }
    

    public static void main(String[] args) {
    }
}
