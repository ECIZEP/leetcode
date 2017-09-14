
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Main {
    public static double result = 0;
    public static double[] per = null;

    public static void  combine(int[] a, int n) {

        if(null == a || a.length == 0 || n <= 0 || n > a.length)
            return;

        int[] b = new int[n];
        getCombination(a, n , 0, b, 0);
    }

    private static void getCombination(int[] a, int n, int begin, int[] b, int index) {
        if(n == 0){
            Set set = new HashSet();
            for(int i = 0; i < index; i++){
                set.add(b[i]);
                System.out.print(b[i] + " ");
            }
            System.out.println();
            double temp = 1;
            for (int j = 0; j < a.length; j++) {
                if (set.contains(j)) {
                    temp = temp * per[j];
                } else {
                    temp = temp * (1 - per[j]);
                }
            }
            result += temp;
            return;
        }

        for(int i = begin; i < a.length; i++){
            b[index] = a[i];
            getCombination(a, n-1, i+1, b, index+1);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        per = new double[n];
        for (int i = 0; i < n; i++) {
            per[i] = (double) (scanner.nextInt()) / 100;
        }
        int number = (int)Math.ceil(n * 0.6);
        System.out.println(number);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        while (number <= n) {
            combine(a,number);
            number++;
            System.out.printf("%.5f", result);
        }
        // System.out.printf("%.5f", result);
    }


    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int [] price = new int [m];
        int scores = 0;
        int index  = 0;
        for (int i = 0; i < m; i++) {
            price[i] = scanner.nextInt();
        }
        Arrays.sort(price);
        for (int i = 0; i < m; i++) {
            if (m-i > n) {
                continue;
            }
            int score = price[i] * (m-i);
            if (score > scores) {
                scores = score;
                index = i;
            }
        }
        System.out.println(price[index]);
    }*/

   /* public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] cal = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                int temp  = scanner.nextInt();
                if (cal[i][0] == 0 || temp < cal[i][0]) {
                    cal[i][0] = temp;
                    cal[i][1] = 1;
                } else if (temp == cal[i][0]){
                    cal[i][1]++;
                }
                cal[i][2] = cal[i][2] + temp;
            }
        }
        int [] sort = new int[n];
        Set set = new HashSet();
        for (int i = n-1; i >= 0; i--) {
            int min = -1;
            for (int j = 0; j < n; j++) {
                if (set.contains(j)) {
                    continue;
                }
                if (min == -1) {
                    min = j;
                    continue;
                }
                if (cal[j][0] < cal[min][0]) {
                    min = j;
                }
            }
            sort[i] = min;
            set.add(min);
        }

        int start = -1;
        int end = -1;
        for (int i = 0; i < n - 1; i++) {
            if (cal[sort[i]][0] == cal[sort[i+1]][0]) {
                if (start == -1) {
                    start = i;
                } else {
                    end = i + 1;
                }
            } else if (cal[sort[i]][0] > cal[sort[i+1]][0]) {
                if (start != -1) {
                    resort(sort, cal, start, end+1);
                    start = end = -1;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            if (k == n - 1) {
                System.out.println(sort[k]);
            } else {
                System.out.print(sort[k] + " ");
            }

        }

    }

    public static void resort (int [] arr, int [][] cal, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            for (int j = start; j < end + start - i - 1; j++) {
                if (cal[arr[j]][1] > cal[arr[j+1]][1] || (cal[arr[j]][1] == cal[arr[j+1]][1] && cal[arr[j]][2] < cal[arr[j+1]][2])) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
*/
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set set = new HashSet();
        int score = 0;
        for (int i = 0; i < n; i++) {
            int serial = scanner.nextInt();
            int anwser = scanner.nextInt();
            int explain = scanner.nextInt();
            if (anwser == 1 && explain == 1) {
                score += 30;
                set.add(serial);
            } else if (anwser == 1 && explain == 0) {
                if (set.contains(serial)) {
                    score += 5;
                } else {
                    score += 10;
                    set.add(serial);
                }
            }
        }
        System.out.println(score);
    }*/

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }
        int [] temp;
        int [] arr = new int[2];
        int i = 0;

        while (i < num.length - 1) {
            temp = getAnswer(num, i);
            i = temp[1];
            if (temp[0] != -1 && temp[1] - temp[0] > arr[1] - arr[0]) {
                arr[0] = temp[0];
                arr[1] = temp[1];
            }
        }
        if (arr[1] > arr[0]) {
            System.out.println(arr[0] + " " + arr[1]);
        } else {
            System.out.println("-1 -1");
        }
    }

    public static int[] getAnswer (int[] arr, int startIndex) {
        int min = -1;
        int max = -1;
        int i = startIndex;
        while (arr[i] > arr[i+1]) {
            i++;
        }
        min = i;
        while (i < arr.length - 1 && arr[i] < arr[i+1]) {
            i++;
        }
        while (i < arr.length - 1 && arr[i] > arr[i+1]) {
            max = i;
            i++;
        }
        max++;
        int [] answer = new int[2];
        if (max != -1 && max != 0) {
            answer[0] = min;
            answer[1] = max;
        } else {
            answer[0] = -1;
            answer[1] = arr.length - 1;
        }
        return answer;
    }*/
    /*public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int k = scanner.nextInt();
        String[] array = str.split(",");
        int [] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = Integer.parseInt(array[i]);
        }
        Arrays.sort(arr);
        *//*int [] coins = new int[arr.length];
        for (int i = 0,j = arr.length-1;i < arr.length && j >=0;i++,j--) {
            coins[j] = arr[i];
        }*//*
        int road[]=new int[k+1];
        int min=getMinCount(k ,arr ,road );
        System.out.println(min);
    }

    public static int getMinCount (int k,int c[],int r[]){
        int a[]=new int[k+1];
        a [0]=0;
        for(int x=1;x<k+1;x++){
            if(x>=c[0]){
                a[x]=a[x-c[0]]+1;
                r[x]=c[0];
            }else{
                a[x] = -1;

            }
            for(int i=1;i<c.length;i++){
                if(x>=c[i]&&(a[x]>a[x-c[i]]+1)){
                    a[ x]= a[ x- c[ i]]+1;
                    r[ x]= c[ i];
                }
            }
        }
        return a[k];
    }*/
    /*public static void makeChange(int[] values, int valueKinds, int money,
                                  int[] coinsUsed) {
        coinsUsed[0] = 0;
        int cents = 1;
        for (; cents <= money; cents++) {
            int minCoins = cents;
            for (int kind = 0; kind < valueKinds; kind++) {
                if (values[kind] <= cents) {
                    int temp = coinsUsed[cents - values[kind]] + 1;
                    if (temp < minCoins) {
                        minCoins = temp;
                    }
                }
            }
            coinsUsed[cents] = minCoins;
        }
        System.out.println(coinsUsed[--cents]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int amount = scanner.nextInt();
        String[] array = str.split(",");
        int [] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = Integer.parseInt(array[i]);
        }
        Arrays.sort(arr);
        int [] singValue = new int[arr.length];
        for (int i = 0,j = arr.length-1;i < arr.length && j >=0;i++,j--) {
            singValue[j] = arr[i];
        }
        int[] coinsUsed = new int[amount + 1];

        makeChange(singValue, singValue.length, amount, coinsUsed);
    }*/


   /* public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int num = 0;
        for (int i = 1; i < str.length(); i = i + 2) {
            for (int j = 0; j + i < str.length(); j++) {
                if (isSub(str.substring(j,j+i+1))) {
                    num++;
                }
            }
        }
        System.out.println(num);
    }

    public static boolean isSub (String str) {
        if (str.length() == 2) {
            return str.charAt(0) == str.charAt(1);
        }
        Set set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                continue;
            }
            int index = str.indexOf(str.charAt(i));
            int time = 1;
            while ((index = str.indexOf(str.charAt(i), index+1)) != -1) {
                time++;
            }
            if (time % 2 == 0) {
                set.add(str.charAt(i));
                continue;
            } else {
                return false;
            }
        }
        return true;
    }*/

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double sum = 0;
        for (int i = 0;i < n;i++) {
            int x = scanner.nextInt();
            int p = scanner.nextInt();
            float fp = (float)p/100;
            sum = sum + x * fp;
        }
        System.out.printf("%.3f",sum);
    }*/



}


//  分金
/*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int length = scanner.nextInt();
            int[] array = new int[length];
            for (int j = 0; j < length; j++) {
                array[j] = scanner.nextInt();
            }
            System.out.print("Case #" + (i+1) + ": ");
            cardGame(array);
        }
    }

    public static void cardGame(int[] array) {
        int[][] first = new int[array.length][array.length];
        int[][] s = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            first[i][i] = array[i];
            for (int j = i - 1; j >= 0; j--) {
                first[j][i] = Math.max(array[j] + s[j+1][i], array[i] + s[j][i-1]);
                s[j][i] = Math.min(first[j+1][i], first[j][i-1]);
            }
        }
        System.out.println(first[0][array.length-1] + " " + s[0][array.length-1]);
    }
*/
