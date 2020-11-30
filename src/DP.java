import java.util.*;

public class DP {
    // k个鸡蛋 n层楼 最小尝试次数
    public static int superEggDrop(int K, int N) {
        //// dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少实验的次数
        int dp[][] = new int[N+1][K+1];
        // init i层楼最多需要i次实验
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i],i);
        }
        dp[1][0] = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K ; j++) {
                // 选择在第k层楼丢鸡蛋 此处可以进一步二分优化
                for (int k = 1; k <= i ; k++) {
                    // 碎了，就需要往低层继续扔：层数少 1 ，鸡蛋也少 1
                    // 不碎，就需要往高层继续扔：层数是当前层到最高层的距离差，鸡蛋数量不少
                    // 两种情况都做了一次尝试，所以加 1
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k-1][j-1],dp[i-k][j]) + 1);
                }
            }
        }

        return dp[N][K];
    }


    public static   int superEggDrop2(int K, int N) {
        return dp(K,N);
    }
    public static Map<Integer,Integer> memo = new HashMap<Integer, Integer>();
    public static int dp(int k, int n) {
        if (!memo.containsKey(n*100 + k)) {
            int ans = n;
            if (n==0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
//                for (int i = 1; i <= n ; i++) {
//                    ans = Math.min(ans, Math.max(dp(k-1,i-1),dp(k,n-i)) + 1);
//                }
                //选择在第k层楼丢鸡蛋  根据此处可以进一步二分优化
                //注意 dp(K - 1, i - 1) 和 dp(K, N - i) 这两个函数，其中 i 是从 1 到 N 单增的，如果我们固定 K 和 N，把这两个函数看做关于 i 的函数，前者随着 i 的增加应该也是单调递增的，而后者随着 i 的增加应该是单调递减的
                int lo = 1;
                int hi = n;
                while (lo <= hi) {
                    int mid = (lo+hi)/2;
                    int broken = dp(k-1,mid-1);
                    int notBroken = dp(k, n-mid);
                    if (broken > notBroken) {
                        hi = mid - 1;
                        ans = Math.min(ans, broken+1);
                    } else {
                        lo = mid + 1;
                        ans = Math.min(ans, notBroken + 1);
                    }
                }
            }

            memo.put(n*100+k, ans);
        }
        return memo.get(n*100+k);
    }


    // 回文对
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<String> revWord = new ArrayList<>();
        Map<String,Integer> dict = new HashMap<>();
        int n = words.length;
        for (String word : words) {
            revWord.add(new StringBuffer(word).reverse().toString());
        }
        for (int i = 0; i < n; i++) {
            dict.put(revWord.get(i),i);
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            if (m == 0) {
                continue;
            }
            // todo detail
            for (int j = 0; j < m; j++) {
                if (isHuiwen(word,0,j)) {
                    int rightId = dict.getOrDefault(word.substring(j, m-1), -1);
                    if (rightId != -1 && rightId != i) {
                        ans.add(Arrays.asList(rightId,i));
                    }
                }
                if (isHuiwen(word,j,m-1)) {
                    int leftId = dict.getOrDefault(word.substring(0, j), -1);
                    if (leftId != -1 && leftId != i) {
                        ans.add(Arrays.asList(i,leftId));
                    }
                }
            }
        }
        return ans;
    }

    public static boolean isHuiwen(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
//        System.out.println(superEggDrop2(8,2000));
        System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }


}
