import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
    	Integer[] dist = new Integer[adj.length];
    	LinkedList<Integer> q = new LinkedList<Integer>();
    	Integer s = 0;
    	q.add(s);
    	dist[s] = 0;
    	while(!q.isEmpty()) {
    		Integer curNode = q.removeFirst();
    		Integer curDist = dist[curNode];
    		boolean curEven = curDist%2 == 0 ? true:false;
        	ArrayList<Integer> nList = adj[curNode];
        	for(Integer n : nList) {
    			if(dist[n] == null) {
        			q.add(n);
        			dist[n] = curDist + 1;        				
    			}
    			boolean nEven = dist[n]%2 == 0 ? true:false;
    			if((nEven && curEven) || (!nEven && !curEven))
    				return 0;
        	}
    	}
        return 1;
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
        System.out.println(bipartite(adj));
    }
}

