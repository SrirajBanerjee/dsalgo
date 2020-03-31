import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    // Operations - add, delete, replace, same
	int slen = s.length() + 1;
	int tlen = t.length() + 1;
	int max = Math.max(slen, tlen);
//	int dim = Math.max(slen, tlen);
	int[][] dist = new int[slen][tlen];
	for(int i = 0; i < slen; ++i) {
		dist[i][0] = i;
	}
	for(int i = 0; i < tlen; ++i) {
		dist[0][i] = i;
	}
	for(int i = 1; i < Math.min(slen, tlen); ++i) {
		int col = i;
		for(; col < tlen; ++col) {
			int d = max;
			d = Math.min(d, dist[i-1][col]+1);
			d = Math.min(d, dist[i][col-1]+1);
			if(s.charAt(i-1) == t.charAt(col-1)) {
				d = Math.min(d, dist[i-1][col-1]);
			}
			else {
				d = Math.min(d, dist[i-1][col-1]+1);
			}
			dist[i][col] = d;
		}
		int row = i+1;
		for(;row < slen; ++row) {
			int d = max;
			d = Math.min(d, dist[row-1][i]+1);
			d = Math.min(d, dist[row][i-1]+1);
			if(s.charAt(row-1) == t.charAt(i-1)) {
				d = Math.min(d, dist[row-1][i-1]);
			}
			else {
				d = Math.min(d, dist[row-1][i-1]+1);
			}
			dist[row][i] = d;	
		}
	}
    return dist[slen-1][tlen-1];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
