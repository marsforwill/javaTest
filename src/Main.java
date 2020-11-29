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

    // 理解题意的意思是： 是包括大于等于来落的，但是最后落下的一定是 尽量低 ，再次是尽量靠近落点 的题意 就很奇葩
    public static int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            if (K-1 >= 0 && heights[K-1] <= heights[K]) {
                int temp = K,j=K;
                while (j-1 >= 0 && heights[j-1] <= heights[j]) {
                    if (heights[j-1] < heights[j]) {
                        temp=j-1;
                    }
                    j--;
                }
                if (heights[temp] < heights[K]) {
                    heights[temp]++;
                    continue;
                }
            }
            if (K+1 < heights.length && heights[K+1] <= heights[K]) {
                int temp = K, j=K;
                while (j+1 < heights.length && heights[j+1] <= heights[j]) {
                    if (heights[j+1] < heights[j]) {
                        temp=j+1;
                    }
                    j++;
                }
                if (heights[temp] < heights[K]) {
                    heights[temp]++;
                    continue;
                }
            }
            heights[K]++;

        }
        return heights;
    }

    public static int[] pourWater2(int[] H, int V, int K) {
        while (V-- > 0) droplet: {
            for (int d = -1; d <= 1; d += 2) {
                int i = K, best = K;
                while (0 <= i+d && i+d < H.length && H[i+d] <= H[i]) {
                    if (H[i+d] < H[i]) best = i + d;
                    i += d;
                }
                if (H[best] < H[K]) {
                    H[best]++;
                    break droplet;
                }
            }
            H[K]++;
        }
        return H;
    }


    public static void main(String[] args) {
        System.out.println("hello");
//        System.out.println(mostCompetitive(new int[]{71,18,52,29,55,73,24,42,66,8,80,2}, 3));
        System.out.println(pourWater2(new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1}, 5 ,5));//[1,2,3,4,3,4,3,3,3,4,3,2,1]
    }
}
