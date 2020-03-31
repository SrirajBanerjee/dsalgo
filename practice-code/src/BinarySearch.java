import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearchImpl(int[] a, int left, int right, int x) {
        //write your code here
    	if(left > right) {
    		return -1;
    	}
        int mid = (int)Math.floor((left+right)/2.0);
        if(a[mid] == x) {
        	return mid;
        }
        else if(a[mid] > x) {
        	return binarySearchImpl(a, left,mid-1, x);
        }
        else if(a[mid] < x) {
        	return binarySearchImpl(a, mid+1,right, x);
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
        	System.out.print(binarySearchImpl(a, 0, a.length-1, b[i]) + " ");
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
