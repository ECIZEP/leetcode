package killOffer;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}

class Solution {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}

public class Q03 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(0);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        head.next = one;
        one.next = two;
        ArrayList<Integer> arrayList = solution.printListFromTailToHead(head);
        for (Integer j : arrayList) {
            System.out.println(j);
        }
    }
}


