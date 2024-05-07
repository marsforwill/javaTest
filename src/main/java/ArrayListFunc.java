import java.util.*;

public class ArrayListFunc {
    // new ArrayList<>() / .add / .get
    // Arrays.sort() Arrays.copyOf(srcArray,n)  Arrays.fill(srcArray, value)


  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        // Write your code here
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> strmap = new HashMap<>();
        for (String s : stringList) {
            strmap.put(s, strmap.getOrDefault(s, 0) + 1);
        }
        for (String s : queries) {
            ans.add(strmap.getOrDefault(s, 0));
        }
        return ans;
    }

    // 树的层次遍历 无语法手写真难
    public static ListNode[] listOfDepth(TreeNode tree) {
        List<TreeNode> temp = new ArrayList<>();
        List<ListNode> ans = new ArrayList<>();
        temp.add(tree);
        while(temp.size() > 0){
            // temp - > iter
            List<TreeNode> iter = new ArrayList<>();
            ListNode listNode = new ListNode(-1);
            ListNode head = listNode;
            for(TreeNode inode : temp){
                if(inode.left != null){
                    iter.add(inode.left);
                }
                if (inode.right != null){
                    iter.add(inode.right);
                }
                listNode.next = new ListNode(inode.val);
                listNode = listNode.next;
            }

            // iter -> ans,temp
            ans.add(head.next);
            temp = iter;
        }

        ListNode[] output = new ListNode[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            output[i] = ans.get(i);
        }
        return output;
    }

    public static void main(String[] args) {

    }
}
