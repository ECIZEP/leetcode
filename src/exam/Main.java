package exam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by ECIZEP on 2017/4/22.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int [][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            array[i][0] = scanner.nextInt();
            array[i][1] = scanner.nextInt();
        }
        System.out.println(maxBaby(array));
    }

    public static int maxBaby(int[][] array) {
        Arrays.sort(array, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        int length = 0;
        int dp[] = new int[array.length];
        for(int[] baby : array){
            int index = Arrays.binarySearch(dp, 0, length, baby[1]);
            if(index < 0) {
                index = -index - 1;
            }
            dp[index] = baby[1];
            if (index == length) {
                length++;
            }
        }
        return length;
    }
}


/*public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ' ') {
                continue;
            } else {
                int start = i;
                while (i < stringBuffer.length() && stringBuffer.charAt(i) != ' ') {
                    i++;
                }
                int end = --i;
                while (end > start) {
                    char temp = stringBuffer.charAt(start);
                    stringBuffer.setCharAt(start, stringBuffer.charAt(end));
                    stringBuffer.setCharAt(end, temp);
                    start++;
                    end--;
                }
            }
        }
        System.out.println(stringBuffer);
    }
}*/
