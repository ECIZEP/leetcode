package offer;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by ECIZEP on 2017/5/6.
 */


/*
* 第四题：根据前中遍历，重构二叉树
* */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }

class Solution04 {
    // 前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, 1+i), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
            }
        }
        return root;
    }
}


/*
* 第五题：用两个栈来模拟队列
*
* */
class Solution05 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    // 第二个栈来pop, 如果第二个栈是空，则将第一个栈里面的东西全部pop进第二个栈
    // 这个时候就模拟了队列的先进先出
    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}

/*

        旋转之后的数组实际上可以划分成两个有序的子数组：前面子数组的大小都大于后面子数组中的元素
        注意到实际上最小的元素就是两个子数组的分界线。本题目给出的数组一定程度上是排序的，因此我们试着用二分查找法寻找这个最小的元素。
        思路：

        （1）我们用两个指针left,right分别指向数组的第一个元素和最后一个元素。按照题目的旋转的规则，第一个元素应该是大于最后一个元素的（没有重复的元素）。
        但是如果不是旋转，第一个元素肯定小于最后一个元素。
        （2）找到数组的中间元素。
        中间元素大于第一个元素，则中间元素位于前面的递增子数组，此时最小元素位于中间元素的后面。我们可以让第一个指针left指向中间元素。
        移动之后，第一个指针仍然位于前面的递增数组中。
        中间元素小于第一个元素，则中间元素位于后面的递增子数组，此时最小元素位于中间元素的前面。我们可以让第二个指针right指向中间元素。

        移动之后，第二个指针仍然位于后面的递增数组中。

        这样可以缩小寻找的范围。
        （3）按照以上思路，第一个指针left总是指向前面递增数组的元素，第二个指针right总是指向后面递增的数组元素。
        最终第一个指针将指向前面数组的最后一个元素，第二个指针指向后面数组中的第一个元素。
        也就是说他们将指向两个相邻的元素，而第二个指针指向的刚好是最小的元素，这就是循环的结束条件。


        到目前为止以上思路很耗的解决了没有重复数字的情况，这一道题目添加上了这一要求，有了重复数字。
        因此这一道题目比上一道题目多了些特殊情况：
        我们看一组例子：｛1，0，1，1，1｝ 和 ｛1，1，
        1，0，1｝
        都可以看成是递增排序数组｛0，1，1，1，1｝的旋转。
        这种情况下我们无法继续用上一道题目的解法，去解决这道题目。因为在这两个数组中，第一个数字，最后一个数字，中间数字都是1。
        第一种情况下，中间数字位于后面的子数组，第二种情况，中间数字位于前面的子数组。

        因此当两个指针指向的数字和中间数字相同的时候，我们无法确定中间数字1是属于前面的子数组（绿色表示）还是属于后面的子数组（紫色表示）。
        也就无法移动指针来缩小查找的范围。
 */

class Solution06 {
    // {3,4,5,1,2}
    public int minNumberInRotateArray(int [] array) {
        int left = 0, right = array.length - 1;
        int mid;
        while (left < right) {
            if (left + 1 == right) {
                System.out.println(right);
                return array[right];
            }
            mid = (left + right) / 2;
            if (array[mid] == array[left] && array[mid] == array[right]) {
                // 这种情况不能确定Mid是属于前一个有序区，还是后一个有序区
                // 所以只能顺序查找最小值
                int min = array[left];
                for (int i = left + 1; i < right; i++) {
                    if (array[i] < min) {
                        min = array[i];
                    }
                }
                return min;
            } else if (array[mid] >= array[left]) {
                left = mid;
            } else if (array[mid] <= array[right]) {
                right = mid;
            }
        }
        return 0;
    }
}

public class Offer {
    public static void main(String[] args) {
        Solution06 test = new Solution06();
        int [] array = {1,0,1,1,1};  // {1,1,1,0,1}
        // test.minNumberInRotateArray(array);
        System.out.println(test.minNumberInRotateArray(array));

    }

    public static void show04(TreeNode root) {
        if (root != null) {

            show04(root.left);
            System.out.println(root.val);
            show04(root.right);
        }
    }
}