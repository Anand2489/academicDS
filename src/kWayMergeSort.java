import java.util.Scanner;

public class kWayMergeSort {
    int[] A = new int[1000];
    int[] C = new int[1000];
    int[][] B;
    public  void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        B = new int[2][k+1];
        for (int i=0;i<n;i++)
            A[i] = in.nextInt();
        KWMS(A,0,n,k);

    }
    public void KWMS(int[] A,int i, int j, int k){
        int x = j-i+1;
        int a = (int)Math.ceil(x/k);
        int b = (int)Math.floor(x/k);
        int r = x%k;

    }
}
