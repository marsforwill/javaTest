import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args){
        System.out.println("hello");
        System.out.println(largestValues(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
    }
}
