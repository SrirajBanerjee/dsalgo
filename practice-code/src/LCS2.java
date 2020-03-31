import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
    	int slen = a.length + 1;
    	int tlen = b.length + 1;
    	int[][] dist = new int[slen][tlen];
    	for(int i = 0; i < slen; ++i) {
    		dist[i][0] = 0;
    	}
    	for(int i = 0; i < tlen; ++i) {
    		dist[0][i] = 0;
    	}
    	for(int i = 1; i < slen; ++i) {
    		for(int j = 1; j < tlen; ++j) {
    			int d = 0;
    			d = Math.max(d, dist[i-1][j]); // deletion
    			d = Math.max(d, dist[i][j-1]); // insertion
    			if(a[i-1] == b[j-1]) {
    				d = Math.max(d, dist[i-1][j-1]+1); // match
    			}
    			else {
    				d = Math.max(d, dist[i-1][j-1]); // mismatch
    			}
    			dist[i][j] = d;
    		}
    	}
        return dist[slen-1][tlen-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

        System.out.println(lcs2(a, b));
    }
}

