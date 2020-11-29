import com.auth0.jwt.algorithms.Algorithm;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Stack;

public class Main {

    public static int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            for (;s.size() + nums.length - i > k && !s.empty() && nums[i] < s.peek() ;) {
                s.pop();
            }
            s.push(nums[i]);
        }
        int[] ans = new int[s.size()];
        for (int i = s.size()-1; i >=0 ; i--) {
            ans[i] = s.pop();
        }
        int[] f = new int[k];
        for (int i = 0; i < k; i++) {
            f[i] = ans[i];
        }
        return f;
    }

    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(mostCompetitive(new int[]{71,18,52,29,55,73,24,42,66,8,80,2}, 3));
//        //HMAC
//        Algorithm algorithmHS = Algorithm.HMAC256("Ac0vkWwbSgBuOqJKGUw8uKlCWv7v0FNP");
//
//        //RSA
//        RSAPublicKey publicKey = null;//Get the key instance
//        RSAPrivateKey privateKey = null;//Get the key instance
//        Algorithm algorithmRS = Algorithm.RSA256(publicKey, privateKey);
    }
}
