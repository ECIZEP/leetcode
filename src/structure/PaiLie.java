package structure;

/**
 * Created by ECIZEP on 2017/4/7.
 */
public class PaiLie {

    public void combine(int[] a, int n) {

        if(null == a || a.length == 0 || n <= 0 || n > a.length)
            return;

        int[] b = new int[n];
        getCombination(a, n , 0, b, 0);
    }

    private void getCombination(int[] a, int n, int begin, int[] b, int index) {

        if(n == 0){
            for(int i = 0; i < index; i++){
                System.out.print(b[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = begin; i < a.length; i++){

            b[index] = a[i];
            getCombination(a, n-1, i+1, b, index+1);
        }

    }

    public static void main(String[] args){

        PaiLie robot = new PaiLie();

        int[] a = {1,2,3,4};
        int n = 2;
        robot.combine(a,n);

    }

}