package com.example.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class test {
    @Test
    public void testCopyRandomList() {
        Node n7 = new Node(7);
        Node n13 = new Node(13);
        Node n11 = new Node(11);
        Node n10 = new Node(10);
        Node n1 = new Node(1);

        n7.next = n13;
        n7.random = null;
        n13.next = n11;
        n13.random = n7;
        n11.next = n10;
        n11.random = n1;
        n10.next = n1;
        n10.random = n11;
        n1.next = null;
        n1.random = n7;
        copyRandomListV3(n7);

//        Node nf1 = new Node(-1);
//        nf1.random = nf1;
//        copyRandomListV3(nf1);
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            Node node = new Node(head.val);
            if (head.random == head) {
                node.random = node;
            } else {
                node.random = null;
            }
            return node;
        }
        Map<Node, Node> map = new HashMap<>();
        Node newHead = null, newFront = null, newBack = null;
        Node first = head;
        while (first != null) {
            newFront = new Node(first.val);
            newFront.random = first.random;
            map.put(first, newFront);

            if (newHead == null) {
                newHead = newFront;
            }
            if (newBack == null) {
                newBack = newFront;
            } else {
                newBack.next = newFront;
                newBack = newFront;
            }
            first = first.next;
        }

        newFront = newHead;
        while (newFront != null) {
            newFront.random = map.get(newFront.random);
            newFront = newFront.next;
        }
        return newHead;
    }

    public Node copyRandomListV2(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node first = head;
        while (first != null) {
            map.put(first, new Node(first.val));
            first = first.next;
        }

        first = head;
        Node newHead = map.get(first);
        while (first != null) {
            map.get(first).next = map.get(first.next);
            map.get(first).random = map.get(first.random);
            first = first.next;
        }
        return newHead;
    }

    public Node copyRandomListV3(Node head) {
        if (head == null) {
            return null;
        }
        Node first = head;
        //拷贝节点
        while (first != null) {
            Node copyNode = new Node(first.val);
            copyNode.next = first.next;
            first.next = copyNode;
            first = copyNode.next;
        }

        //链接random节点
        first = head;
        while (first != null) {
            first.next.random = first.random == null ? null : first.random.next;
            first = first.next.next;
        }

        //拆分链表
        first = head;
        Node copyHead = first.next;
        Node copyFirst = first.next;
        while (first != null) {
            first.next = first.next.next;
            first = first.next;
            if (first != null) {
                copyFirst.next = first.next;
                copyFirst = copyFirst.next;
            }
        }

        return copyHead;
    }


    @Test
    public void levelOrder() {
        TreeNode t3 = new TreeNode(3);
        TreeNode t5 = new TreeNode(5);
        TreeNode t1 = new TreeNode(1);
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t0 = new TreeNode(0);
        TreeNode t8 = new TreeNode(8);
        TreeNode t7 = new TreeNode(7);
        TreeNode t4 = new TreeNode(4);

        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t2.left = t7;
        t2.right = t4;
        t1.left = t0;
        t1.right = t8;


        System.out.println("层次遍历：" + Arrays.toString(levelOrder(t3)));
    }

    public int[] levelOrder(TreeNode root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.push(root);
        while (!list.isEmpty()) {
            TreeNode treeNode = list.removeFirst();
            arrayList.add(treeNode.val);
            if (treeNode.left != null) {
                list.addLast(treeNode.left);
            }
            if (treeNode.right != null) {
                list.addLast(treeNode.right);
            }
        }
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    @Test
    public void validateStackSequences() {
        boolean res = validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
        System.out.println("是否是正确顺序：" + res);
    }


    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) {
            return false;
        }
        if (pushed.length != popped.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int pushPos = 0, popPos = 0;
        while (pushPos < pushed.length && popPos < popped.length) {
            if (pushed[pushPos] != popped[popPos]) {
                stack.push(pushed[pushPos]);
                pushPos++;
            } else {
                pushPos++;
                popPos++;
                while (!stack.isEmpty() && popPos < popped.length) {
                    if (stack.peek() == popped[popPos]) {
                        stack.pop();
                        popPos++;
                    } else {
                        break;
                    }
                }
            }
        }
        while (!stack.isEmpty() && popPos < popped.length) {
            if (stack.peek() != popped[popPos]) {
                return false;
            } else {
                stack.pop();
                popPos++;
            }
        }
        return true;
    }

    public int kthSmallest(TreeNode root, int k) {
        int[] nums = new int[k];
        return 0;
    }

    public void findKthNum(TreeNode root, int k) {
        TreeNode index = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (index != null) {
                stack.push(index);
                index = index.left;
            }
            index = stack.pop();
            if (index != null) {
                System.out.print(index.val);
            }
            index = index.right;
        }
    }

    @Test
    public void un() {
        TreeNode t3 = new TreeNode(3);
        TreeNode t5 = new TreeNode(5);
        TreeNode t1 = new TreeNode(1);
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t0 = new TreeNode(0);
        TreeNode t8 = new TreeNode(8);
        TreeNode t7 = new TreeNode(7);
        TreeNode t4 = new TreeNode(4);

        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t2.left = t7;
        t2.right = t4;
        t1.left = t0;
        t1.right = t8;

        findKthNum(t3, 0);

    }

    public String reverseStr(String str) {
        List<String> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                list.add(stringBuffer.toString());
                stringBuffer.setLength(0);
                continue;
            }
            stringBuffer.append(str.charAt(i));
        }

        stringBuffer.setLength(0);
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuffer.append(" ");
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.substring(1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> oneList = new LinkedList<>();
        LinkedList<TreeNode> twoList = new LinkedList<>();
        boolean left = findNode(oneList, root, p);
        boolean right = findNode(twoList, root, q);
        if (!left || !right) {
            return null;
        }

        TreeNode temp = null;
        while (!oneList.isEmpty() && !twoList.isEmpty()) {
            TreeNode one = oneList.pollLast();
            TreeNode two = twoList.pollLast();
            if (one == two) {
                temp = one;
            } else {
                return temp;
            }
        }
        return temp;
    }

    public boolean findNode(LinkedList<TreeNode> oneList, TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            oneList.add(root);
            return true;
        }

        boolean left = findNode(oneList, root.left, p);
        if (left) {
            oneList.add(root);
        }
        boolean right = findNode(oneList, root.right, p);
        if (right) {
            oneList.add(root);
        }

        return left || right;
    }

    public TreeNode lowestCommonAncestorSortTress(TreeNode root, TreeNode p, TreeNode q) {
//        LinkedList<TreeNode> oneList = new LinkedList<>();
//        LinkedList<TreeNode> twoList = new LinkedList<>();
        TreeNode tempP = root, tempQ = root;
        TreeNode last = root;
        while (tempP != null && tempQ != null) {
            if (tempP.val > p.val) {
                tempP = tempP.left;
            } else if (tempP.val < p.val) {
                tempP = tempP.right;
            } else if (tempP.val == p.val) {
                tempP = p;
            }

            if (tempQ.val > q.val) {
                tempQ = tempQ.left;
            } else if (tempQ.val < q.val) {
                tempQ = tempQ.right;
            } else if (tempQ.val == q.val) {
                tempQ = q;
            }

            if (tempP != tempQ) {
                return last;
            } else {
                last = tempP;
            }
        }
        return null;
    }


    public int uniquePaths(int m, int n) {
        return (int) (Ax(m + n - 2, n - 1) / Ax(n - 1, n - 1));
    }

    public long Ax(int x, int y) {
        long res = 1;
        for (int i = x; i > x - y; i--) {
            res *= i;
        }
        return res;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int length = (x + "").length();
        if (length == 1) return true;
        boolean isOu = false;
        if (length % 2 == 0) isOu = true;
        int temp = 0;
        for (int i = 0; i < length; i++) {
            if (!isOu && length / 2 == i) {
                x = x / 10;
                continue;
            }
            if (length / 2 < i) break;
            if (i < (length + 1) / 2) {
                temp = temp * 10 + x % 10;
            }
            x = x / 10;
        }
        return temp == x;
    }

    public boolean isPalindromeV2(int x) {
        if (x < 0) {
            return false;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int num = 0;
        while (x > 0) {
            num = x % 10;
            x = x / 10;
            queue.add(num);
        }
        while (!queue.isEmpty()) {
            int head = queue.pollFirst();
            if (queue.isEmpty()) {
                return true;
            }
            int tail = queue.pollLast();
            if (tail != head) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println("打印是否是回文数字：" + isPalindrome(10));
        System.out.println("打印序列：" + longestPalindrome("aacabdkacaa"));
    }

    static class POS {
        int x;
        int y;

        POS(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }
        Stack<POS> stack = new Stack<>();
        int length = s.length();
        int[][] matrix = new int[s.length() + 1][s.length() + 1];
        char[] original = s.toCharArray();
        char[] revert = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            revert[i] = original[original.length - 1 - i];
        }
        int x = 0, y = 0;
        int max = -1;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (revert[i - 1] == original[j - 1]) {
                    matrix[i][j] = 1 + matrix[i - 1][j - 1];
                    if (matrix[i][j] > max &&
                            (i - matrix[i][j] + j + 1) == matrix.length) {
                        max = matrix[i][j];
                        x = i;
                        y = j;
                        stack.push(new POS(x, y));
                    }
                }
            }
        }

        System.out.println("--------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print("" + matrix[i][j]);
            }
            System.out.println("");
        }
        System.out.println("--------------------");

        String re = "";
        while (!stack.isEmpty()) {
            POS p = stack.pop();
            x = p.x;
            y = p.y;
            if (x - matrix[x][y] + y + 1 == matrix.length) {
                int len = Math.min(x, y);
                for (; len > 0; len--) {
                    if (matrix[x][y] != 0) {
                        re += original[y - 1];
                        x--;
                        y--;
                    } else {
                        return re;
                    }
                }
                return re;
            }
        }
        return re;
    }

    @Test
    public void grayCode() {
        System.out.println("findKthLargest:" + findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < k; j++) {
                if (temp[j] < nums[i]) {
                    for (int z = k - 1; z > j; z--) {
                        temp[z] = temp[z - 1];
                    }
                    temp[j] = nums[i];
                    break;
                }
            }
        }
        return temp[k - 1];
    }
}
