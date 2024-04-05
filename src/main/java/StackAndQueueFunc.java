import java.util.*;

// new Stack<>(); .push .pop .peek
//  Queue<String> queue = new LinkedList<>();  .add .poll .peak
public class StackAndQueueFunc {

    // https://www.hackerrank.com/challenges/balanced-brackets/copy-from/363806293
    public static String isBalanced(String s) {
    // Write your code here
        if(s == null || s.length() % 2 != 0) return "NO";
        Stack<Character> sta = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '('){
                sta.push(c);
            } else {
                if (sta.isEmpty()){
                    return "NO";
                }
                
                if (c == '}' && sta.peek() != '{'){
                    return "NO";
                }
                if (c == ']' && sta.peek() != '['){
                    return "NO";
                }
                if (c == ')' && sta.peek() != '('){
                    return "NO";
                }
                sta.pop();
            }
        }
        return sta.isEmpty() ? "YES" : "NO";

    }

    // https://www.hackerrank.com/challenges/queue-using-two-stacks/copy-from/363808856
    public static void twoStackQueue(){
        Stack <Integer> stk1 = new Stack <Integer> ();
        Stack <Integer> stk2 = new Stack <Integer> ();
        Scanner in = new Scanner(System.in);
        int length;
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int query = in.nextInt();
            if (query == 1){
                Integer number = in.nextInt();
                stk1.add(number);
            } 
            else if (query == 2){
                if (stk2.isEmpty()){
                    while(!stk1.isEmpty()){
                        stk2.add(stk1.pop());
                    }
                } 
                stk2.pop();
            } else {
                if (stk2.isEmpty()){
                    while(!stk1.isEmpty()){
                        stk2.add(stk1.pop());
                    }
                }
                System.out.println(stk2.peek());
            }
        }
    }

    // calculater : Input: " 3+5/2 "Output: 5
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        s = s.strip().replace(" ", "");
        int i = 0;
        while (i < s.length()) {
            int cur = 0;
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                char operator = s.charAt(i);
                i++;
                // get next number
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    cur = cur * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                if (operator == '+') {
                    stack.push(cur);
                } else if (operator == '-') {
                    stack.push(-cur);
                } else if (operator == '*') {
                    stack.push(stack.pop() * cur);
                } else if (operator == '/') {
                    stack.push(stack.pop() / cur);
                }
            } else {
                // get first number
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    cur = cur * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                stack.push(cur);
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
    

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        // Scanner sc = new Scanner(System.in);
        // try{
        //   int numTest = Integer.parseInt(sc.nextLine());
        //   for(int i = 0; i < numTest; i++){
        //    String ans = isBalanced(sc.nextLine()); 
        //    System.out.println(ans);
        //   }
        // } catch(Exception e){
        //     System.out.println("Invalid Input");
        // }

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        System.out.println(calculate(" 3+5 / 2 "));
    }
}
