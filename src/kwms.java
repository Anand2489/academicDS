
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Scanner;

    class KWMS {
    public static int Nan = (int)Double.NEGATIVE_INFINITY;
    public static int[] A ;
//        public static int[] A ={22,12,14,15,78,16,25,11,15,17,19,21,30,15,16,90};
    public static  int[] C ;

    public  static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
		if (n>0){
			A =new int[n];
			for (int i=0;i<n;i++) {
				A[i]=in.nextInt();
			}
			C =new int[n];
			int k =in.nextInt();
			if (k>1){
				KWMS(A, 0, n - 1, k);
			}
		}
    }

    public static void KWMS(int[] A,int i, int j, int k){
        double x = j-i+1;
        if(x ==1){
			System.out.println("The sorted list in the range "+i+" : "+j+" is "+A[i]);
			 return;
		}
           
        int a = (int)Math.ceil(x / k);
        int b = (int)Math.floor(x / k);
        int r = (int)x%k; int p1,p2,count=0;
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
        Merge(A, temp, i, j, k);
        System.out.print("The sorted list in the range "+i+" : "+j+" is ");
        for (int t=i;t<=j;t++)
            System.out.print(A[t]+" ");
        System.out.println();
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
            for (int d=0; d<x ;d++){
                int arraySecton = B[1][0];
                if(arraySecton!=Nan) {
                    C[p] = deleteMax(B, k);
                    temp[0][arraySecton] += 1;
                    if (temp[0][arraySecton] <= temp[1][arraySecton]) {
                        lowerIndex = temp[0][arraySecton];
                        insertHeap(B, A[lowerIndex], arraySecton, k);
                    }
                    System.out.print("Content of the heap is ");
                    for (int t=0;t<k;t++){
                        if (B[0][t]!=Nan)
                            System.out.print(B[0][t]+" ");
                    }
                    System.out.println();
                    p++;
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
        int pos=n-1;
        for (int j=n-1;j>=0;j--){
            if (B[0][j]==Nan){
                pos=j;
                break;
            }
        }
        B[0][pos]=x;
        B[1][pos]=section;
        int i = pos,p;
        while (i>0){
            if(B[0][i]>=B[0][parent(i)]){    //swap
                p=parent(i);
                int[] temp = {B[0][i],B[1][i]};
                B[0][i]=B[0][p] ;   B[1][i]=B[1][p] ;
                B[0][p]=temp[0];   B[1][p]=temp[1];
            }
            i=parent(i);
        }
    }
}
