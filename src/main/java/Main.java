import java.util.*;

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

    // leetcode competetion
    public static int minimumOperationsToWriteY(int[][] grid) {
        Map<Integer, Integer> yMap = new HashMap<>();
        yMap.put(0, 0);
        yMap.put(1, 0);
        yMap.put(2, 0);

        Map<Integer, Integer> other = new HashMap<>();
        other.put(0, 0);
        other.put(1, 0);
        other.put(2, 0);
        int n = grid.length;
        int center = n/2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == j && i <= center) || (i == center && j >= center) || (i+j == n-1 && i <= center)){
                    int value = grid[i][j];
                    yMap.put(value, yMap.getOrDefault(value, 0) + 1);
                }
                else {
                    int value = grid[i][j];
                    other.put(value, other.getOrDefault(value, 0) + 1);
                }
            }
        }

        int ans = 49 * 49;
        for (Integer i : yMap.keySet()) {
            for (Integer j : other.keySet()) {
                if (i != j){
                    int ci = 0;
                    for (Integer i2 : yMap.keySet()) {
                        if (i2 != i){
                            ci += yMap.get(i2);
                        }
                    }
                    int co = 0;
                    for (Integer j2 : other.keySet()){
                        if (j2 != j) {
                            co += other.get(j2);
                        }
                    }
                    ans = Math.min(ans, ci + co);
                }
            }
        }
        return ans;
    }

    // 16.22. Langtons Ant LCCI
    public static List<String> printKMoves(int K) {
        // 初始化边界记录
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        String d = "RDLU";
        int x = 0, y = 0, p = 0;
        Set<List<Integer>> black = new HashSet<>();
        while (K-- > 0) {
            List<Integer> t = List.of(x, y);
            // 记录黑色坐标状态, set<List> 判断
            if (black.add(t)) {
                p = (p + 1) % 4;
            } else {
                black.remove(t);
                p = (p + 3) % 4;
            }
            // 模拟移动的坐标变化
            x += dirs[p];
            y += dirs[p + 1];
            // 记录边界
            x1 = Math.min(x1, x);
            y1 = Math.min(y1, y);
            x2 = Math.max(x2, x);
            y2 = Math.max(y2, y);
        }
        int m = x2 - x1 + 1;
        int n = y2 - y1 + 1;
        char[][] g = new char[m][n];
        for (char[] row : g) {
            Arrays.fill(row, '_');
        }
        for (List<Integer> t : black) {
            int i = t.get(0) - x1;
            int j = t.get(1) - y1;
            g[i][j] = 'X';
        }
        g[x - x1][y - y1] = d.charAt(p);
        List<String> ans = new ArrayList<>();
        for (char[] row : g) {
            ans.add(String.valueOf(row));
        }
        return ans;
    }

    // 求无重复字符的最长连续字串长度
    public static int deal(String str){
        int left = 0;
        int right = 0;
        int n = str.length();
        Set<Character> s = new HashSet();
        int ans = 0;
        while(left < n && right < n){
            if (s.contains(str.charAt(right))){
                int i = 0;
                for(i = left; str.charAt(left) != str.charAt(right) && i < n; i++){
                    s.remove(str.charAt(i));
                    left++;
                    System.out.println(s);
                    System.out.println("---" + left + "---" + right);
                }
                left = i+1;
                right++;
                System.out.println(s);
            } else {
                s.add(str.charAt(right));
                if (s.size() > ans){
                    ans = s.size();
                }
                right++;
                System.out.println(s);
            }
        }     
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println("hello");
//        System.out.println("test online editor");
//        System.out.println(mostCompetitive(new int[]{71,18,52,29,55,73,24,42,66,8,80,2}, 3));
//        System.out.println(pourWater(new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1}, 5 ,5));//[1,2,3,4,3,4,3,3,3,4,3,2,1]
        System.out.println("test");
        System.out.println(deal("pwwkew"));
    }
}
