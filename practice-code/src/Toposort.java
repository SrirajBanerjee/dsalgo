import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Toposort {
    private static LinkedList<Integer> toposort(ArrayList<Integer>[] adj) {
        boolean used[] = new boolean[adj.length];
        LinkedList<Integer> order = new LinkedList<Integer>();
    	for(int i = 0; i < used.length; ++i) {
			if(used[i] == false) {
				dfs(adj, used, order, i);
				used[i] = true;
		    	order.add(0,i);
			}
    	}
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] used, LinkedList<Integer> order, int s) {
    	for(int i = 0; i < adj[s].size(); ++i) {
    		Integer n = adj[s].get(i);
    		if(used[n] == false) {
    			dfs(adj, used, order, n);
    	    	used[n] = true;
    	    	order.add(0,n);
    		}
    	}
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
        }
        LinkedList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

