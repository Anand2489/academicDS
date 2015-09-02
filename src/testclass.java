import java.util.Arrays;

public class testclass {
    public static void main (String[] args){
        int[] array = new int[5];
        System.out.println(Arrays.toString(array)); // 0, 0, 0, 0, 0
        fillArray(array,1);
        System.out.println(Arrays.toString(array)); // 0, 1, 2, 3, 4
        fillArray(array,2);
        System.out.println(Arrays.toString(array)); // 0, 1, 2, 3, 4
    }
    public static void fillArray(int[] array,int t) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i*t;
        }
    }
}
