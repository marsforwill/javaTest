import java.util.Arrays;

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

    public static void main(String[] args) {
        System.out.println(superEggDrop(8,2000));
    }


}
