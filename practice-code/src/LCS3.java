import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
    	int alen = a.length + 1;
    	int blen = b.length + 1;
    	int clen = c.length + 1;
    	int[][][] dist = new int[alen][blen][clen];
    	for(int i = 0; i < alen; ++i) {
    		for(int j = 0; j < blen; ++j) {
    			dist[i][j][0] = 0;
    		}
    	}
    	for(int i = 0; i < alen; ++i) {
    		for(int k = 0; k < clen; ++k) {
    			dist[i][0][k] = 0;
    		}
    	}
    	for(int j = 0; j < blen; ++j) {
    		for(int k = 0; k < clen; ++k) {
    			dist[0][j][k] = 0;
    		}
    	}
    	for(int i = 1; i < alen; ++i) {
    		for(int j = 1; j < blen; ++j) {
    			for(int k = 1; k < clen; ++k) {
        			int d = 0;
        			d = Math.max(d, dist[i-1][j][k]);
        			d = Math.max(d, dist[i][j-1][k]);
        			d = Math.max(d, dist[i-1][j-1][k]);
        			d = Math.max(d, dist[i-1][j][k-1]);
        			d = Math.max(d, dist[i][j-1][k-1]);
        			d = Math.max(d, dist[i][j][k-1]);
        			if((a[i-1] == b[j-1]) && (b[j-1] == c[k-1])) {
        				d = Math.max(d, dist[i-1][j-1][k-1]+1); // match
        			}
        			else {
        				d = Math.max(d, dist[i-1][j-1][k-1]); // mismatch
        			}
        			dist[i][j][k] = d;    				
    			}
    		}
    	}
        return dist[alen-1][blen-1][clen-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

