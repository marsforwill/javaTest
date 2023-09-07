import java.util.*;

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

    public static void main(String[] args) {
        // System.out.println(alienOrder(new String[]{"wrt", "wrtkj" }));
        int[] nums = new int[] { 2, 9, 3, 7, 6 };
        quicksort(nums, 0, 4);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
