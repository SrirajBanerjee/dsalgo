import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] A) {
        //write your code here
    	int sum = 0;
    	for(int i = 0; i < A.length; ++i) {
    		sum += A[i];
    	}
    	if(sum%3 != 0) {
    		return 0;
    	}
    	int val = sum/3;
    	int dp[][][] = new int[A.length+1][val+1][val+1];
    	for(int k = 0; k < A.length+1; ++k) {
	    	for(int i = 0; i < val+1; ++i) {
	    		for(int j = 0; j < val+1; ++j) {
	    			dp[k][i][j] = 0;
	    		}
	    	}
    	}
    	for(int k = 1; k < A.length+1; ++k) {
    		dp[k][0][0] = 1;
    	}
    	dp[1][A[0]][0] = 1;
    	dp[1][0][A[0]] = 1;
    	for(int k = 2; k < A.length+1; ++k) {
    		for(int i = 0; i < val+1; ++i) {
    			for(int j = 0; j < val+1; ++j) {
    				if(i==0 && j==0) {
    					continue;
    				}
    				int e = dp[k-1][i][j];
    				if(A[k-1] <= i) {
    					e = e | dp[k-1][i-A[k-1]][j];
    				}
    				if(A[k-1] <= j) {
    					e = e | dp[k-1][i][j-A[k-1]];
    				}
    				dp[k][i][j] = e;
    			}
    		}
    	}    	
        return dp[A.length][val][val];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

