import java.util.*;

public class TopoSort {


    public static String alienOrder(String[] words) {
        int len = words.length;
        // 节点入度
        int[] degree = new int[26];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        int cnt = 0;
        Arrays.fill(degree,-1);
        // init cnt and degree
        for (char i = 0; i < words[0].length(); i++) {
            if (degree[words[0].charAt(i) - 'a'] == -1) {
                cnt++;
                degree[words[0].charAt(i) - 'a'] = 0;
            }
        }
        for (int i = 0; i < len-1; i++) {
            for (int j = 0; j < words[i + 1].length(); j++) {
                if (degree[words[i+1].charAt(j) - 'a'] == -1) {
                    cnt++;
                    degree[words[i+1].charAt(j) - 'a'] = 0;
                }
            }

            String l = words[i];
            String r = words[i+1];
            for (int j = 0;j<l.length();j++) {
                if (j==r.length() && j< l.length()){
                    return "";
                }
                if (l.charAt(j) != r.charAt(j)) {
                    //l[j] > r[j]
                    graph.get(l.charAt(j)-'a').add(r.charAt(j)-'a');
                    degree[r.charAt(j)-'a']++;
                    break;
                }
            }
        }
        // 拓扑排序
        String ans = "";
        Queue<Integer> queue = new LinkedList<>();
        // 入度为0的节点入队
        for (int i = 0; i < 26; i++) {
            if (degree[i] == 0 ) {
                ans = ans + (char)(i + 'a');
                queue.add(i);
            }
        }
        // 当队列非空，处理入度为0的节点from，
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0 ; i--) {
                int from = queue.poll();
                // graph里取出对应出度的节点 入度-1
                for (int to : graph.get(from)) {
                    if (degree[to] == 1) {
                        queue.add(to);
                        ans = ans + (char)(to + 'a');
                    }
                    degree[to]--;
                }
            }
        }

        return ans.length()==cnt?ans:"";
    }

    public static void main(String[] args) {
        System.out.println(alienOrder(new String[]{"wrt", "wrtkj" }));
    }
}
