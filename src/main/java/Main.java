import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;

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

    // 755. 倒水
    // 理解题意的意思是： 是包括大于等于来落的，但是最后落下的一定是 尽量低 ，再次是尽量靠近落点 的题意 就很奇葩
    //在 V 个单位的水落在索引 K 处以后
    public static int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            // 往左边落 小于等于的时候就可以往左
            if (K-1 >= 0 && heights[K-1] <= heights[K]) {
                int temp = K,j=K;
                // 小于等于的时候往左找 但是小于的时候才会落
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
            // 往右边落 小于等于的时候就可以往右
            if (K+1 < heights.length && heights[K+1] <= heights[K]) {
                int temp = K, j=K;
                // 小于等于的时候往右找 但是小于的时候才会落
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



    interface Master {
        public int guess(String word);
    }
    // 贪心猜单词 最大化 最小
    public static void findSecretWord(String[] wordlist, Master master) {
        int n = wordlist.length;
        int[][] dis = new int[n+1][n+1];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i,0);
            for (int j = i+1; j < n; j++) {
                dis[j][i] = getNum(wordlist,i,j);
                dis[i][j] = dis[j][i];
            }
        }
        for (int t = 0; t < 10; t++) {
            int max = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (map.containsKey(i)) {
                    int[] num = new int[7];
                    for (int j = 0; j < n; j++) {
                        if (i != j && map.containsKey(j)) {
                            num[dis[i][j]] += 1;
                        }
                    }
                    // 取每个元素里面最大值距离
                    int tmp = Arrays.stream(num).max().getAsInt();
                    // 的最小
                    if (tmp < max) {
                        max = tmp;
                        index = i;
                    }
                }
            }
            // guess
            int k = master.guess(wordlist[index]);
            map.remove(index);
            for (int j = 0; j < n; j++) {
                if (j==index) {
                    continue;
                }
                if (map.containsKey(j) && dis[index][j] != k) {
                    map.remove(j);
                }
            }
        }
    }

    public static int getNum(String[] wordlist, int i, int j) {
        int count = 0;
        for (int k = 0; k < wordlist[i].length(); k++) {
            if (wordlist[i].charAt(k) == wordlist[j].charAt(k)) {
                count++;
            }
        }
        return count;
    }

    // https://www.hackerrank.com/challenges/ctci-ransom-note/copy-from/363669434
    public static void checkMagazine(List<String> magazine, List<String> note) {
    // Write your code here
        Map<String, Integer> strMap = new HashMap<>();
        for (String str : magazine) {
            strMap.put(str, strMap.getOrDefault(str, 0) + 1);
        }
        for (String str : note) {
            if(!strMap.containsKey(str)){
                System.out.println("No");
                return;
            }
            Integer count = strMap.getOrDefault(str, 0);
            if (count > 1){
                strMap.put(str, count-1);
            }else {
                strMap.remove(str);
            }
        }
        System.out.println("Yes");
    }

    // https://www.hackerrank.com/challenges/magic-square-forming/submissions/code/363999920
    public static int formingMagicSquare(List<List<Integer>> s) {
    // Write your code here
        int[][][] ans ={
            {{8,3,4},{1,5,9},{6,7,2}},
            {{6,7,2},{1,5,9},{8,3,4}},
            {{4,3,8},{9,5,1},{2,7,6}},
            {{2,7,6},{9,5,1},{4,3,8}},
            
            {{8,1,6},{3,5,7},{4,9,2}},
            {{6,1,8},{7,5,3},{2,9,4}},
            {{4,9,2},{3,5,7},{8,1,6}},
            {{2,9,4},{7,5,3},{6,1,8}}
        };
        int min = 1000;
        for (int i = 0; i < 8; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (ans[i][j][k] > s.get(j).get(k)){
                        count += Math.abs(ans[i][j][k] - s.get(j).get(k));
                    } else {
                        count += Math.abs(s.get(j).get(k) - ans[i][j][k]);
                    }
                    //System.out.println(count);
                }
            }
            System.out.println(count);
            if (count < min){
                min = count;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println("test online editor");
//        System.out.println(mostCompetitive(new int[]{71,18,52,29,55,73,24,42,66,8,80,2}, 3));
        System.out.println(pourWater(new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1}, 5 ,5));//[1,2,3,4,3,4,3,3,3,4,3,2,1]
    }
}
