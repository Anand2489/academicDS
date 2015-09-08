
import java.util.Arrays;
import java.util.Scanner;

public class kWayMerge2 {
    public static int[] A ={14,78,25,17,19,21,30,90,23,22,33,44,55,66,77,88};
//    public static int[] A ={22,12,14,15,78,16};

    public static  int[] C ;
//    public static  int[][] B;

    public static int Nan = (int)Double.NEGATIVE_INFINITY;

    public  static void main(String[] args){
        Scanner in = new Scanner(System.in);
//        System.out.println("Input n: ");
        int n = A.length;//in.nextInt();
        System.out.println("A length: "+n);
//        A =new int[n];
        C =new int[n];
        int[] D = new int[n];
        System.out.println("Input k: ");
        int k = in.nextInt();
//        B = new int[2][k];
//        for (int d=0;d<k;d++){
//            B[0][d]=Nan;    B[1][d]=Nan;
//        }

//        for (int i=0;i<n;i++) {
//            System.out.println("Input element: " + i);
//            A[i]=in.nextInt();
//        }
        for (int i=0;i<n;i++)
            D[i]=A[i];
        Arrays.sort(D);
        System.out.println("Initial array: \n" + Arrays.toString(A));
        KWMS(A, 0, n - 1, k);
        System.out.println("Sorted array: \n" + Arrays.toString(A));
        System.out.println("Correct Sorted array: \n" + Arrays.toString(D));
        boolean flag=true;
        for (int i=0,j=n-1;flag==true && i<n && j>=0;i++,j--){
            if (A[i]!=D[j])
                flag=false;
        }
        System.out.println("Result: "+flag);
    }

    public static void KWMS(int[] A,int i, int j, int k){
        double x = j-i+1;
        if(x<=1)
            return;
        int a = (int)Math.ceil(x / k);
        int b = (int)Math.floor(x / k);
        int r = (int)x%k; int p1,p2,count=0;
        System.out.println("r value: "+r);
        int[][] temp = new int[2][k];
        for (int d=0;d<k;d++){
            temp[0][d]=Nan; temp[1][d]=Nan;
        }
        if (r!=0){
            for(int p=0;p<=r-1 && (i + (p) * a)<=j && (i + (p + 1) * a - 1)<=j;p++){
                p1=i + (p) * a;
                p2=i + (p + 1) * a - 1;
                temp[0][count]=p1;
                temp[1][count]=p2;
                KWMS(A, p1,p2, k);
                count++;
            }
            for(int p=0;i+r*a+(p+1)*b-1<=j && (i + r * a + p * b)<=j;p++){
                p1=i + r * a + p * b;
                p2=i + r * a + (p + 1) * b - 1;
                temp[0][count]=p1;
                temp[1][count]=p2;
                KWMS(A, p1,p2, k);
                count++;
            }
        }else{
            for(int p=0;i+(p+1)*a-1<=j && (i + (p) * a)<=j && (i + (p + 1) * a - 1)<=j;p++){
                p1=i + (p) * a;
                p2=i + (p + 1) * a - 1;
                temp[0][count]=p1;
                temp[1][count]=p2;
                KWMS(A,p1,p2,k);
                count++;
            }
        }
        k=count;
        Merge(A,temp,i,j,k);
    }
    public static void Merge(int[] A,int[][] temp,int i, int j,int k){
        int x=j-i+1;
        int[][] B = new int[2][k];
        for (int d=0;d<k;d++){
            B[0][d]=Nan; B[1][d]=Nan;
        }
        int lowerIndex;
        for (int p=0;p<k;p++){
            if (temp[0][p]!=Nan) {
                B[0][p] = A[temp[0][p]];
                B[1][p] = p;
            }
        }
        try {
            buildHeap(B, k);
            int p=i;
            for (int d=0; d<x ;d++,p++){
                int arraySecton = B[1][0];
                if(arraySecton!=Nan) {
                    System.out.println("B: "+Arrays.toString(B[0]));
                    C[p] = deleteMax(B, k);
                    temp[0][arraySecton] += 1;

                    if (temp[0][arraySecton] <= temp[1][arraySecton]) {
                        lowerIndex = temp[0][arraySecton];
                        insertHeap(B, A[lowerIndex], arraySecton, k);
                    }
                    System.out.println("C: "+ Arrays.toString(C));
                    System.out.println();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int d=i;d<=j;d++)
            A[d] =C[d];

    }
    public static int parent(int j){

        if(j>0){
            return (j-1)/2;
        }else
            return 0;
    }
    public static int left (int j){
        return 2*j+1;
    }
    public static int right (int j){
        return 2*j+2;
    }

    public static void fixHeap (int[][] B,int i,int n){
        int l = left(i);
        int r = right(i);
        int large=i;
        if (l<=n-1){
            if (B[0][l]>=B[0][i])
                large=l;
        }
        if (r<=n-1){
            if (B[0][r]>=B[0][large])
                large=r;
        }
        if (large !=i){ //swap
            int[] temp = {B[0][i],B[1][i]};
            B[0][i]=B[0][large] ;   B[1][i]=B[1][large] ;
            B[0][large]=temp[0];            B[1][large]=temp[1];
            fixHeap(B,large,n);
        }
    }

    public static void buildHeap (int[][] B,int n){
        for(int j=parent(n-1);j>=0;j--)
            fixHeap(B,j,n);
    }

    public static int deleteMax ( int[][] B,int n){
        int max = B[0][0];
        B[0][0]=Nan;    B[1][0]=Nan;
        fixHeap(B,0,n);
        return max;
    }

    public static void insertHeap (int[][] B,int x,int section,int n){
        B[0][n-1]=x;
        B[1][n-1]=section;
        int i = n-1,p;
        while (i>0){
            if(B[0][i]>=B[0][parent(i)]){    //swap
                p=parent(i);
                int[] temp = {B[0][i],B[1][i]};
                B[0][i]=B[0][p] ;   B[1][i]=B[1][p] ;
                B[0][p]=temp[0];   B[1][p]=temp[1];
            }
            i=parent(i);
        }
        fixHeap(B,0,n);
    }
}
