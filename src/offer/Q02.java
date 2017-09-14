package offer;

/**
 * Created by ECIZEP on 2017/4/21.
 */

/*
问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
      从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
      从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
*/
public class Q02 {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("We Are Happy ");
        System.out.println(replaceSpace(str));
    }

    public static String replaceSpace(StringBuffer str) {
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        int oldIndex = str.length()-1;
        int newLength = str.length() + spaceNum*2;
        str.setLength(newLength);
        for (int i = newLength - 1; i >= 0 && oldIndex >= 0; i--) {
            if (str.charAt(oldIndex) == ' ') {
                str.setCharAt(i--, '0');
                str.setCharAt(i--, '2');
                str.setCharAt(i, '%');
            } else {
                str.setCharAt(i, str.charAt(oldIndex));
            }
            oldIndex--;
        }
        return str.toString();
    }
}
