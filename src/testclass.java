import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;

public class testclass {
//    public static void main (String[] args){
//////        int[] array = new int[5];
////        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
////        for (int i=0; i<2; ++i)
////            arr.add(new ArrayList<Integer>());
////        arr.get(0).add(1);  arr.get(0).add(2);
////        arr.get(1).add(3);  arr.get(1).add(4);
////        System.out.println(arr);
////        System.out.println(arr.get(1).get(1));
////        fillArray(arr, arr.size());
////        System.out.println(arr);
////        System.out.println(arr.get(1).get(0));
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(0); arr.add(1);
//        int n=5;
//        fillArray(arr,n);
//
//    }
//    public static void fillArray(ArrayList<Integer> array,int n) {
////        array.set(0, array.get(n - 1));
////        array.set(1, array.get(n - 2));
////        array.get(0).remove(0);array.get(1).remove(0);
////        array.remove(n-2);
//
//    }

    static int n1=0,n2=1,n3=0;
    static void printFibonacci(ArrayList arr,int count){
        if(count>0){
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
//            System.out.print(" "+n3); 0 1 1 2 3 5 8 13 21 34
            arr.add(n3);
            printFibonacci(arr,count-1);
        }
    }
    public static void main(String args[]){
        int count=10;
        int nan =(int)Double.NEGATIVE_INFINITY;
        ArrayList arr = new ArrayList();
        arr.add(n1);arr.add(n2);
        System.out.println(n1 + " " + n2);//printing 0 and 1
//        printFibonacci(arr, count - 2);//n-2 because 2 numbers are already printed
        double x=2+2+3;
        System.out.println((int)Math.floor(3/2));
    }
}

