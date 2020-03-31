import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
    	Integer[] dist = new Integer[adj.length];
    	LinkedList<Integer> q = new LinkedList<Integer>();
    	q.add(s);
    	dist[s] = 0;
    	while(!q.isEmpty()) {
    		Integer curNode = q.removeFirst();
    		Integer curDist = dist[curNode];
    		if(curNode == t) {
    			return dist[curNode];
    		}
        	ArrayList<Integer> nList = adj[curNode];
        	for(Integer n : nList) {
    			if(dist[n] == null) {
        			q.add(n);
        			dist[n] = curDist + 1;        				
    			}
        	}
    	}
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

