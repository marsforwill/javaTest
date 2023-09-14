import java.util.Stack;

// 非递归就是用栈来模拟
// 可递归的话就是考虑递归出口和抽出重复操作
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

    // 非递归后序遍历：左-右-根 ：
    public static void postorderTraversal(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        // 创建一个指针指向上一次访问的节点，初始为null
        TreeNode prev = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            // 查看（但不弹出）栈顶节点
            TreeNode node = stack.peek();
            // 如果当前节点没有左右孩子，或者上一次访问的节点是当前节点的左右孩子之一，
            // 那么就可以访问当前节点，并弹出它，并更新上一次访问的节点为当前节点
            if ((node.left == null && node.right == null) ||
                    (prev != null && (prev == node.left || prev == node.right))) {
                System.out.println(node.value);
                stack.pop();
                prev = node;
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
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

    // todo : linkedList <> 中序遍历 <> BST // 互相转化

    public static void main(String[] args) {
        System.out.println("test tree");
        // 2
        // 3 1
        // 4 5
        TreeNode tree = new TreeNode(2, new TreeNode(3, new TreeNode(4, null, null), new TreeNode(5, null, null)),
                new TreeNode(1, null, null));
        // preorderTraversal(tree);
        postorderTraversal(tree);
    }
}
