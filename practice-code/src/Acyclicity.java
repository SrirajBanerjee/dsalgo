import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        Integer[] visited = new Integer[adj.length];
        Integer[] present = new Integer[adj.length];
        for(int i = 0; i < present.length; ++i) {
        	present[i] = 1;
        }
        for(int i = 0; i < present.length; ++i) {
        	if(present[i] == 1) {
                boolean val = explore(adj, i, visited, present);
                if(!val)
                	return 1;        		
        	}
        }
        return 0;
    }
    
    private static boolean explore(ArrayList<Integer>[] adj, Integer v, Integer[] visited, Integer[] present) {
		visited[v] = 1;
    	for(int i = 0; i < adj[v].size(); ++i) {
    		Integer n = adj[v].get(i);
    		if(present[n] == 1) {
        		if( visited[n] == null ) {
        			boolean val = explore(adj, n, visited, present);
        			if(!val)
        				return false;
        		}
        		else {
        			// cycle present
        			return false;
        		}
    		}
    	}
    	present[v] = 0;
    	return true;
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
        System.out.println(acyclic(adj));
    }
}

