package com.example.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class test2 {

    @Test
    public void testMaxValue() {
//        int[][] grid = new int[][]{{3,8,6,0,5,9,9,6,3,4,0,5,7,3,9,3},{0,9,2,5,5,4,9,1,4,6,9,5,6,7,3,2},{8,2,2,3,3,3,1,6,9,1,1,6,6,2,1,9},{1,3,6,9,9,5,0,3,4,9,1,0,9,6,2,7},{8,6,2,2,1,3,0,0,7,2,7,5,4,8,4,8},{4,1,9,5,8,9,9,2,0,2,5,1,8,7,0,9},{6,2,1,7,8,1,8,5,5,7,0,2,5,7,2,1},{8,1,7,6,2,8,1,2,2,6,4,0,5,4,1,3},{9,2,1,7,6,1,4,3,8,6,5,5,3,9,7,3},{0,6,0,2,4,3,7,6,1,3,8,6,9,0,0,8},{4,3,7,2,4,3,6,4,0,3,9,5,3,6,9,3},{2,1,8,8,4,5,6,5,8,7,3,7,7,5,8,3},{0,7,6,6,1,2,0,3,5,0,8,0,8,7,4,3},{0,4,3,4,9,0,1,9,7,7,8,6,4,6,9,5},{6,5,1,9,9,2,2,7,4,2,7,2,2,3,7,2},{7,1,9,6,1,2,7,0,9,6,6,4,4,5,1,0},{3,4,9,2,8,3,1,2,6,9,7,0,2,4,2,0},{5,1,8,8,4,6,8,5,2,4,1,6,2,2,9,7}};
//        System.out.println("结果是："+maxValue(grid));

//        System.out.println("结果是："+lengthOfLongestSubstring("aabaab!bb"));
//        long start = System.currentTimeMillis();
//        System.out.println("结果是："+nthUglyNumber(326));
//        System.out.println("耗时："+ (System.currentTimeMillis()-start));

        System.out.println("结果是：" + firstUniqChar("abaccdeff"));

    }

    public char firstUniqChar(String s) {
        if (s == null || "".equals(s)) {
            return ' ';
        }

        LinkedHashMap<Character, Integer> hashMap = new LinkedHashMap<Character, Integer>(0, 0.75f, true);
        char[] chars = s.toCharArray();
        for (char c : chars) {
            Integer count = hashMap.get(c);
            if (count == null) {
                hashMap.put(c, 1);
            } else {
                hashMap.put(c, count + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }


    public int nthUglyNumber(int n) {
        if (n <= 6) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[1] = 1;
        nums[2] = 2;
        nums[3] = 3;
        nums[4] = 4;
        nums[5] = 5;
        nums[6] = 6;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);

        int start = 7;
        for (int i = start; i <= n; i++) {
            for (int j = 2; j < start; j++) {
                if (nums[i - 1] % nums[j] == 0) {
                    int shang = nums[i - 1] / nums[j];
                    int pos = hashMap.get(shang);
                    int s = j + 1;
                    int last = 0;
                    while (s <= pos) {
                        int res = nums[s] * nums[pos];
                        if (nums[i - 1] < res) {
                            last = res;
                            pos--;
                        } else if (nums[i - 1] >= res) {
                            s++;
                        }
                    }
                    hashMap.put(nums[start], start);
                    break;
                }
            }
        }
        return nums[n];
    }


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int max = 0;
        HashSet<Character> hashSet = new HashSet<>();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (hashSet.contains(chars[i])) {
                if (max < hashSet.size()) {
                    max = hashSet.size();
                }
                for (; start <= i; start++) {
                    hashSet.remove(chars[start]);
                    if (chars[start] == chars[i]) {
                        start++;
                        break;
                    }
                }
            }
            hashSet.add(chars[i]);
            if (max < hashSet.size()) {
                max = hashSet.size();
            }
        }
        return max;
    }


    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (x == 0 && y - 1 >= 0) {
                    dp[x][y] = dp[x][y - 1] + grid[x][y];
                    continue;
                }
                if (y == 0 && x - 1 >= 0) {
                    dp[x][y] = dp[x - 1][y] + grid[x][y];
                    continue;
                }
                int top = 0;
                if (x - 1 >= 0) {
                    top = dp[x - 1][y];
                }
                int left = 0;
                if (y - 1 >= 0) {
                    left = dp[x][y - 1];
                }
                dp[x][y] = left == 0 ? top : left + grid[x][y];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    @Test
    public void testTranslateNum() {
        System.out.println("结果是：" + translateNum(12258));
    }

    /**
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }
        int length = 0;
        int temp = num;
        while (temp != 0) {
            length++;
            temp = temp / 10;
        }
        circleNUm(num, 0, length);
        return count;
    }

    int count = 0;

    public void circleNUm(int num, int start, int length) {
        if (start == length - 1 || start == length) {
            count++;
            return;
        } else if (start > length) {
            return;
        }

        if (isValidate(num, start, 1, length)) {
            circleNUm(num, start + 1, length);
        }
        if (isValidate(num, start, 2, length)) {
            circleNUm(num, start + 2, length);
        }
    }

    public boolean isValidate(int num, int start, int step, int length) {
        int v = (int) (num % Math.pow(10, length - start));
        if (v / (int) Math.pow(10, length - start - 1) == 0 && step == 2) {
            return false;
        }
        int left = (int) (v / Math.pow(10, length - start - step));
        return left <= 25;
    }

    @Test
    public void testCountNum() {
        int[] num;
        System.out.println("结果是：" + count(1000));
        Integer[] a = new Integer[]{};
        Arrays.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 + "" + o2).compareTo((o2 + "" + o1));
            }
        });
    }

    public int getMaxRange(int length) {
        if (length == 1) {
            return 10;
        }
        if (length == 2) {
            return 90 * 2 + 10;
        }
        return (int) (length * Math.pow(10, length - 1) * 9 + getMaxRange(length - 1));
    }

    public int count(int num) {
        int last = 0;
        int temp = num;
        int pos = 0;
        int total = 0;
        while (temp != 0) {
            int yuShu = temp % 10;
            int num10 = (int) Math.pow(10, pos);
            int chenShu = (pos <= 0 ? 0 : last);
            if (yuShu > 1) {
                total += yuShu * chenShu + num10;
            } else if (yuShu == 1) {
                total += chenShu + (num % num10 + 1);
            }
            temp = temp / 10;

            if (pos == 0) {
                last = 1;
            } else if (pos == 1) {
                last = 20;
            } else {
                last = (int) (last * 10 + Math.pow(10, pos));
            }

            pos++;
        }
        return total;
    }

    public class MedianFinder {
        LinkedList<Integer> list;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            list = new LinkedList<>();
        }

        public void addNum(int num) {
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) > num) {
                        list.add(i, num);
                        return;
                    }
                }
            }
            list.add(num);
        }

        public double findMedian() {
            if (list.size() == 0) {
                return 0;
            }
            int mid = list.size() / 2;
            int yuShu = list.size() % 2;
            if (yuShu != 0) { //奇数
                return list.get(mid);
            } else {
                return (list.get(mid) + list.get(mid - 1)) * 1.000d / 2;
            }
        }
    }

    @Test
    public void testMaxSubArrat() {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArray(array);
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(nums[i] + res, nums[i]);
            max = Math.max(res, max);
        }
        return res;
    }

    @Test
    public void testKMAx() {
        int[] arr = new int[]{3, 2, 1};
        int k = 2;
        stack = new int[k];
        for (int i = 0; i < k; i++) {
            stack[i] = Integer.MAX_VALUE;
        }
        for (int num : arr) {
            inStack(num);
        }
    }

    int[] stack;

    public void inStack(int num) {
        if (num > stack[0]) {
            return;
        }
        stack[0] = num;
        int pos = 0;
        while (pos < stack.length) {
            int left = 2 * (pos + 1) - 1;
            if (left >= stack.length) {
                return;
            }
            int right = 2 * (pos + 1);
            int next = 0;
            if (right < stack.length && stack[left] < stack[right]) {
                next = right;
            } else {
                next = left;
            }
            if (stack[next] < stack[pos]) {
                return;
            } else {
                int temp = stack[pos];
                stack[pos] = stack[next];
                stack[next] = temp;
                pos = next;
            }
        }
    }

    public void printList(List<String> list) {
        System.out.println("-----------------\n");
        for (String n : list) {
            System.out.print("," + n);
        }
        System.out.println("\n-----------------");
    }

    public String[] permutation(String s) {
        List<String> list = new ArrayList<>();
        String[] strings = new String[list.size()];
        StringBuilder stringBuilder = new StringBuilder();
        paiLieAll(list, s.toCharArray(), 0, stringBuilder);
        return list.toArray(strings);
    }

    public void paiLieAll(List<String> list, char[] str, int start, StringBuilder stringBuilder) {
        if (start == str.length) {
            stringBuilder.setLength(0);
            for (char c : str) {
                stringBuilder.append(c);
            }
            list.add(stringBuilder.toString());
        }
        char temp;
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = start; i < str.length; i++) {
            if (hashSet.contains(str[i])) continue;
            hashSet.add(str[i]);
            if (i > start) {
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
            }
            paiLieAll(list, str, 1 + start, stringBuilder);
            if (i > start) {
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
            }
        }
    }


    int mMultiTime = 0;
    int mAddTime = 0;

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }

    @Test
    public void testMultiply() {
//        System.out.println("结果是：" + multi("9519", 9));
//
//        System.out.println("结果是：" + addStr("997999", "97748"));

        System.out.println("结果是：" + multiply("9979999979999979999979997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999" +
                "9999799999799999799999799999799999799999799999799999799999799999799999799999799999799999799999799999799999799" +
                "99997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999" +
                "99997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999997999" +
                "999979999979999979999979999979999979999979999979999979999979999979999979999979999979999979999979999979999979999979999979999997999997999" +
                "", "977489774897748977489774897748977489774897748977489774897748" +
                "97748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489" +
                "77489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977" +
                "774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748" +
                "774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748" +
                "7748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977484897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748" +
                "9774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748977489774897748"));
        System.out.println("multiTime :：" + mMultiTime + ", addTime :" + mAddTime);
    }

    public String multiply(String num1, String num2) {
        if (num2 == null || num1 == null) {
            return "";
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int length2 = num2.length();
        String result = "";
        for (int i = length2 - 1; i >= 0; i--) {
            String part = multi(num1, num2.charAt(i) - '0') + suffix(length2 - 1 - i);
            result = addStr(result, part);
        }
        return result;
    }

    public String suffix(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            stringBuilder.append("0");
        }
        return stringBuilder.toString();
    }

    public String addStr(String num1, String num2) {
        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        int nextNum = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        for (; i >= 0 || j >= 0; i--, j--) {
            int left = 0, right = 0;
            if (i >= 0) {
                left = (num1.charAt(i) - '0');
            }
            if (j >= 0) {
                right = (num2.charAt(j) - '0');
            }
            int res = left + right;
            int localNum = (res % 10 + nextNum);
            stringBuilder.append(localNum % 10);
            nextNum = localNum / 10 + res / 10;
        }
        if (nextNum != 0) {
            stringBuilder.append(nextNum);
        }
        String temp = stringBuilder.reverse().toString();
        mAddTime += System.currentTimeMillis() - start;
        return temp;
    }

    public String multi(String num, int c) {
        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        int length = num.length();
        int nextNum = 0;
        for (int i = length - 1; i >= 0; i--) {
            int res = (num.charAt(i) - '0') * c;
            int localNum = (res % 10 + nextNum);
            stringBuilder.append(localNum % 10);
            nextNum = localNum / 10 + res / 10;
        }
        if (nextNum != 0) {
            stringBuilder.append(nextNum);
        }
        String temp = stringBuilder.reverse().toString();
        mMultiTime += System.currentTimeMillis() - start;
        return temp;
    }


    @Test
    public void test() {
        Node t3 = new Node(3);
        Node t5 = new Node(5);
        Node t1 = new Node(1);
        Node t6 = new Node(6);
        Node t2 = new Node(2);
        Node t0 = new Node(0);
        Node t8 = new Node(8);
        Node t7 = new Node(7);
        Node t4 = new Node(4);
        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t2.left = t7;
        t2.right = t4;
        t1.left = t0;
        t1.right = t8;

        Node first = t3;
        while (first.left != null) {
            first = first.left;
        }

        treeToDoublyListV2(t3, new HashMap<Node, Integer>());

        System.out.println("双向链表：" + first.val);
    }

    public Node treeToDoublyList(Node root) {
        LinkedList<Node> linkedList = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node first = root;
        while (!stack.isEmpty() || first != null) {
            while (first != null) {
                stack.push(first);
                first = first.left;
            }
            first = stack.pop();
            linkedList.add(first);
            first = first.right;
        }
        printList(linkedList);

        first = linkedList.getFirst();
        for (int i = 0; i < linkedList.size(); i++) {
            if (i == 0) {
                linkedList.get(i).left = linkedList.getLast();
            } else {
                linkedList.get(i).left = linkedList.get(i - 1);
            }
            if (i == linkedList.size() - 1) {
                linkedList.get(i).right = linkedList.getFirst();
            } else {
                linkedList.get(i).right = linkedList.get(i + 1);
            }
        }
        return first;
    }

    public void treeToDoublyListV2(Node root, HashMap<Node, Integer> hashMap) {
        if (root == null) {
            return;
        }
        Node left = root.left;
        Node right = root.right;

        Node pre = root.left;
        if (pre != null) {
            Node back = null;
            while (pre != null) {
                back = pre;
                pre = pre.right;
            }
            back.right = root;
            add(hashMap, back, 2);
            root.left = back;
            add(hashMap, root, 1);
        }

        Node suffix = root.right;
        if (suffix != null) {
            Node back = null;
            while (suffix != null) {
                back = suffix;
                suffix = suffix.left;
            }
            back.left = root;
            add(hashMap, back, 1);
            root.right = back;
            add(hashMap, root, 2);
        }

        treeToDoublyListV2(left, hashMap);
        treeToDoublyListV2(right, hashMap);
    }

    class status {
        boolean left;
        boolean right;
    }

    public void add(HashMap<Node, Integer> map, Node node, int num) {
        if (map.get(node) != null) {
            map.put(node, map.get(node) + num);
        }
    }

    public void printList(LinkedList<Node> list) {
        System.out.println("-----------------\n");
        for (Node n : list) {
            System.out.print("," + n.val);
        }
        System.out.println("\n-----------------");
    }
}
