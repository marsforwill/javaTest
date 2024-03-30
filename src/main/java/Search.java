import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// BFS: Queue<Integer> q = new LinkedList<>(); q.add() q.poll()
public class Search {

     public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode() {}
             TreeNode(int val) { this.val = val; }
             TreeNode(int val, TreeNode left, TreeNode right) {
                 this.val = val;
                 this.left = left;
                 this.right = right;
             }
         }

    // 515. Find Largest Value in Each Tree Row
    // BFS
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null)
        {
            return new ArrayList<Integer>();
        }
        List<TreeNode> bfs = new ArrayList<TreeNode>();
        bfs.add(root);
        while(!bfs.isEmpty()){
            Integer max = bfs.get(0).val;
            int sz = bfs.size();
            while(sz-- > 0)
            {
                TreeNode node = bfs.get(0);
                max = Math.max(max, node.val);
                bfs.remove(0);
                if (node.left != null) bfs.add(node.left);
                if (node.right != null) bfs.add(node.right);
            }
            ans.add(max);
        }
        return ans;
    }

    // BFS 模板化
    public void BFS(int start){
        // 标记访问 队列
        boolean[] visited = new boolean[10];
        Queue<Integer> q = new LinkedList<>(); 
        
        // start point 初始化
        visited[start] = true;
        q.add(start);

        // 当队列非空循环
        while(!q.isEmpty()){
            // 取出当前头节点
            int current = q.poll();
            // process current
            System.out.println(current);
            // 处理循环下层节点
            for (int i = 1; i < 4; i++) {
                int next = current + i;
                // 如未标记 则进入队列
                if (visited[next] == false){
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }

    // 二分搜索模板 when search the sorted item
    public static int binarySearch(int[] nums, int target){
        int low = 0;
        int high = nums.length - 1;
        // <= 二分循环判断
        while (low <= high) {
            int mid = low + (high - low)/2;
            // check mid
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        System.out.println("hello");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(binarySearch(arr, 3));
        // System.out.println(largestValues(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
    }
}
