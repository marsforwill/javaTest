import java.util.*;

// Arrays.sort() , Collections.sort() ，可重写比较函数  Collections.sort(x, (a, b) -> Integer.compare(b, a));
// 
public class Sort {

    // 269 火星词典 拓扑排序
    public static String alienOrder(String[] words) {
        int len = words.length;
        // 节点入度
        int[] degree = new int[26];
        // 存26个字母节点的指向图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        int cnt = 0;
        Arrays.fill(degree, -1);
        // init cnt and degree
        for (char i = 0; i < words[0].length(); i++) {
            if (degree[words[0].charAt(i) - 'a'] == -1) {
                cnt++;
                degree[words[0].charAt(i) - 'a'] = 0;
            }
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < words[i + 1].length(); j++) {
                if (degree[words[i + 1].charAt(j) - 'a'] == -1) {
                    cnt++;
                    degree[words[i + 1].charAt(j) - 'a'] = 0;
                }
            }

            String l = words[i];
            String r = words[i + 1];
            for (int j = 0; j < l.length(); j++) {
                if (j == r.length()) {
                    return "";
                }
                // 把相邻两个单词第一个不同的字母偏序存入有向图
                if (l.charAt(j) != r.charAt(j)) {
                    // l[j] > r[j]
                    graph.get(l.charAt(j) - 'a').add(r.charAt(j) - 'a');
                    degree[r.charAt(j) - 'a']++;
                    break;
                }
            }
        }
        // 拓扑排序
        String ans = "";
        Queue<Integer> queue = new LinkedList<>();
        // 入度为0的节点入队
        for (int i = 0; i < 26; i++) {
            if (degree[i] == 0) {
                ans = ans + (char) (i + 'a');
                queue.add(i);
            }
        }
        // 当队列非空，处理入度为0的节点from，
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int from = queue.poll();
                // graph里取出对应出度的节点 入度-1
                for (int to : graph.get(from)) {
                    if (degree[to] == 1) {
                        queue.add(to);
                        ans = ans + (char) (to + 'a');
                    }
                    degree[to]--;
                }
            }
        }

        return ans.length() == cnt ? ans : "";
    }

    public static void quicksort(int[] nums, int left, int right) {
        if (left < right) {
            int idx = partition(nums, left, right);
            quicksort(nums, left, idx - 1);
            quicksort(nums, idx + 1, right);
        }
        return;
    }

    public static int partition(int[] arr, int left, int right) {
        int i = left;
        // 拿最后一个元素作为基准比较，i j 快慢指针从左往右，通过j找出比基准小的元素交换到i前面,j遍历全部后保证i前面都是小的
        for (int j = i; j < right; j++) {
            if (arr[j] < arr[right]) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // https://www.hackerrank.com/challenges/hackerland-radio-transmitters/copy-from/364336352
    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        // Write your code here
        Collections.sort(x);
        int n = x.size();
        int count = 0;
        for (int i = 0; i < x.size();) {
            int j = i;
            for (j = i; j < n; j++) {
                if (x.get(j) - x.get(i) > k) {
                    break;
                }
            }
            j--;
            // System.out.println(j);
            count++;
            int k2 = 0;
            for (k2 = j; k2 < n; k2++) {
                if (x.get(k2) - x.get(j) > k) {
                    break;
                }
            }
            i = k2;
            // System.out.println(i + "---");
        }
        System.out.println(count);
        return count;
    }

    // https://leetcode.cn/problems/rank-teams-by-votes/
    public static String rankTeams(String[] votes) {
        // dw[i][j] 表示 第i个字母在j位置上的计数
        int[][] dw = new int[27][27];
        for(String str : votes){
            for(int i = 0; i < str.length(); i++){
                dw[str.charAt(i) - 'A'][i]++;
                dw[str.charAt(i) - 'A'][26] = 26 - (str.charAt(i) - 'A');
            }
        }
        // 重写比较函数
        Arrays.sort(dw, (a,b) -> {
            for (int i = 0; i < 27; i++) {
                if (a[i] != b[i]){
                    return Integer.compare(b[i], a[i]);
                }
            }
            return 0;
        });
        for (int i = 0; i < dw.length; i++) {
            for (int j = 0; j < dw[i].length; j++) {
                System.out.print(dw[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < dw.length; i++) {
            if(dw[i][26] != 0){
                int del = (26 - dw[i][26]);
                Character temp = (char)('A' + del); // 数字转字符可强转
                ans.append(temp);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        // System.out.println(alienOrder(new String[]{"wrt", "wrtkj" }));
        // int[] nums = new int[] { 2, 9, 3, 7, 6 };
        // quicksort(nums, 0, 4);
        // for (int i : nums) {
        //     System.out.println(i);
        // }

        List<Item> items = new ArrayList<Item>();
        items.add(new Item(3, 6));
        items.add(new Item(2, 7));
        items.add(new Item(5, 8));
        Collections.sort(items);
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).start); // 2,3,5
        }
        System.out.println(rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"}));
    }
}

// amazon OA 2, without enough time
class Item implements Comparable<Item> {
    int start;
    int end;

    Item(int s, int e) {
        this.start = s;
        this.end = e;
    }

    public int compareTo(Item other) {
        if (this.start == other.start) {
            return this.end > other.end ? 1 : -1;
        }
        return this.start > other.start ? 1 : -1;
    }
}
