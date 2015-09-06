import java.util.ArrayList;
import java.util.Scanner;

public class kWayMergeSort {
//    int[] A = new int[1000];
//    int[] C = new int[1000];
//    int[][] B;
    public static ArrayList<Integer> A = new ArrayList<Integer>();
    public static ArrayList<Integer> C = new ArrayList<Integer>();
    public static ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
    public  static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Input n: ");
        int n = in.nextInt();
        System.out.println("Input k: ");
        int k = in.nextInt();
//        B= new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<2; ++i)
            B.add(new ArrayList<Integer>());
        for (int i=0;i<n;i++) {
            System.out.println("Input element: " + i);
            A.add(in.nextInt());
        }
        System.out.println("Initial array: \n"+A);
        KWMS(A, 0, n - 1, k);
        System.out.println("Sorted array: \n" + A);

    }
    public static void KWMS(ArrayList<Integer> A,int i, int j, int k){
        int x = j-i+1;
        int a = (int)Math.ceil(x / k);
        int b = (int)Math.floor(x/k);
        int r = x%k;

        for (int d=0; d<2; ++d)
            temp.add(new ArrayList<Integer>());
        int count =0;
        if(x==1)
            return;
        if (r!=0){
            for(int p=0;p<=r-1;p++){
                if (i+(p+1)*a-1<=j) {
                    temp.get(0).add(i + (p) * a);
                    temp.get(1).add(i + (p + 1) * a - 1);
                    count++;
                    KWMS(A, i + (p) * a, i + (p + 1) * a - 1, k);
                }
            }
            for(int p=0;i+r*a+(p+1)*b<=x;p++){
                if (i+r*a+(p+1)*b-1<=j) {
                    temp.get(0).add(i + r * a + p * b);
                    temp.get(1).add(i + r * a + (p + 1) * b - 1);
                    count++;
                    KWMS(A, i + r * a + p * b, i + r * a + (p + 1) * b - 1, k);
                }
            }
        }else{
            for(int p=0;i+(p+1)*a-1<=j;p++){
                temp.get(0).add(i + (p) * a);
                temp.get(1).add(i + (p + 1) * a - 1);
                count++;
                KWMS(A,i+(p)*a,i+(p+1)*a-1,k);
            }
        }
        System.out.println("1-temp " + temp);
        int size = temp.get(0).size();
        int lowerIndex;
        for (int p=0;p<size;p++){
            lowerIndex=temp.get(0).get(p) ;
            B.get(0).add(p,A.get(lowerIndex));
            B.get(1).add(p,p);
        }
        try {
            buildHeap(B, size);
            while (!B.isEmpty()) {
                int arraySecton = B.get(1).get(0);
                C.add(i, deleteMax(B, B.get(0).size()));
                System.out.println("array section "+arraySecton);
                temp.get(0).set(arraySecton, temp.get(0).get(arraySecton) + 1);
                if (temp.get(0).get(arraySecton) <= temp.get(1).get(arraySecton)) {
                    lowerIndex = temp.get(0).get(arraySecton);
                    insertHeap(B, A.get(lowerIndex), arraySecton, B.get(0).size());
//                temp.get(0).set(arraySecton,lowerIndex+1);
                } else {
                temp.get(0).remove(arraySecton);
                temp.get(1).remove(arraySecton);
                }
//                A.set(temp.get(0).get(arraySecton),(int)Double.NEGATIVE_INFINITY);
            buildHeap(B,B.get(0).size());
                System.out.println("2-temp "+temp);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        temp.removeAll(temp);
    }
    public static int parent(int j){

        if(j>0){
            j=(j-1)/2;
            return (int)Math.floor(j);
        }else
            return 0;
    }
    public static int left (int j){
        return 2*j+1;
    }
    public static int right (int j){
        return 2*j+2;
    }
    public static void fixHeap (ArrayList<ArrayList<Integer>> B,int i,int n){
        int l = left(i);
        int r = right(i);
        int large=i;
        if (l<=n-1){
            if (B.get(0).get(l)>B.get(0).get(i))
                large=l;
        }
        if (r<=n-1){
            if (B.get(0).get(r)>B.get(0).get(large))
                large=r;
        }
        if (large !=i){ //swap
            int[] temp = {B.get(0).get(i),B.get(1).get(i)};
            B.get(0).set(i,B.get(0).get(large)) ;   B.get(1).set(i,B.get(1).get(large)) ;
            B.get(0).set(large,temp[0]);            B.get(1).set(large,temp[1]);
            fixHeap(B,large,n);
        }
    }
    public static void buildHeap (ArrayList<ArrayList<Integer>> B,int n){
        for(int j=parent(n-1);j>=0;j--)
            fixHeap(B,j,n);
    }
    public static int deleteMax ( ArrayList<ArrayList<Integer>> A,int n){
        int max = B.get(0).get(0);
        B.get(0).set(0,B.get(0).get(n-1));  B.get(0).remove(n - 1);
        B.get(1).set(0,B.get(1).get(n-1));  B.get(1).remove(n-1);
        n--;
        fixHeap(B,0,n);
        return max;
    }
    public static void insertHeap (ArrayList<ArrayList<Integer>> B,int x,int section,int n){
        B.get(0).add(n, x);
        B.get(1).add(n,section);
        n=n+1;
        int i = n-1,p;
        while (i>0){
            if(B.get(0).get(i)>B.get(0).get(parent(i))){    //swap
                p=parent(i);
                int[] temp = {B.get(0).get(i),B.get(1).get(i)};
                B.get(0).set(i,B.get(0).get(p)) ;  B.get(1).set(i,B.get(1).get(p)) ;
                B.get(0).set(p,temp[0]);B.get(1).set(p,temp[1]);
            }
            i=parent(i);
        }
    }
}
