import java.util.Stack;

public class Tree {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value, TreeNode l, TreeNode r) {
            this.value = value;
            this.left = l;
            this.right = r;
        }
    }

    // 非递归前序遍历：根-左-右
    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            System.out.println(cur.value);
            // left first, so put right to stack bottom
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    // 非递归中序遍历 左-根-右
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            // 一路向左放
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.value);
                cur = cur.right;

            }
        }
    }

    public static void main(String[] args) {
        System.out.println("test tree");
        // 2
        // 3 1
        // 4 5
        TreeNode tree = new TreeNode(2, new TreeNode(3, new TreeNode(4, null, null), new TreeNode(5, null, null)),
                new TreeNode(1, null, null));
        // preorderTraversal(tree);
        inorderTraversal(tree);
    }
}
