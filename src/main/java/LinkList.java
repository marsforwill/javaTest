import java.util.*;

public class LinkList {
    // common skill: dummy head, double pointer

    public static class Node {
        public Node next;
        public int value;

        public Node(int v, Node n) {
            this.value = v;
            this.next = n;
        }

        public Node() {

        }
    }

    public static Node reverseList(Node head) {
        Node l = head;
        Node r = head.next;
        if (l == null || r == null) {
            return head;
        }
        // head -> l1 -> r1
        // <- l2 -> r2(temp next)
        while (l != null && r != null) {
            // 反转当前
            Node temp = r.next;
            r.next = l;
            // iterate next
            l = r;
            r = temp;
        }
        head.next = null;
        return l;
    }

    // array list and map usage
    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
    // Write your code here
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> strmap = new HashMap<>();
        for(String s : stringList){
            strmap.put(s, strmap.getOrDefault(s, 0) + 1);
        }  
        for (String s : queries) {
            ans.add(strmap.getOrDefault(s, 0));
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, null)));
        Node ans = reverseList(head);
        System.out.println("linked list");
        while (ans != null) {
            System.out.println(ans.value);
            ans = ans.next;
        }

    }
}
