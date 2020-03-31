import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        //write your code here
        int mid = (int)Math.floor((left + right)/2.0);
        int lMajor = getMajorityElement(a, left, mid);
        int rMajor = getMajorityElement(a, mid, right);
        int lMajorCount = 0;
        int rMajorCount = 0;
        for(int i=left; i < right; ++i) {
        	if((lMajor!=-1) && (a[i]==lMajor)) {
        		lMajorCount++;
        	}
        	if((rMajor!=-1) && (a[i]==rMajor)) {
        		rMajorCount++;
        	}
        }
        if(lMajorCount > ((right-left)/2.0)) {
        	return lMajor;
        }
        if(rMajorCount > ((right-left)/2.0)) {
        	return rMajor;
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
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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

