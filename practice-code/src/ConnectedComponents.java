import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        Integer[] visited = new Integer[adj.length];
        for(int i = 0; i < visited.length; ++i) {
        	visited[i] = null;
        }
        Integer cc = 0;
        for(int i = 0; i < visited.length; ++i) {
        	if(visited[i] == null) {
        		cc = cc + 1;
                explore(adj, i, visited, cc);
        	}
        } 
        return cc;
    }
    
    private static void explore(ArrayList<Integer>[] adj, Integer v, Integer[] visited, Integer cc) {
		visited[v] = cc;
    	for(int i = 0; i < adj[v].size(); ++i) {
    		Integer n = adj[v].get(i);
    		if( visited[n] == null ) {
    			explore(adj, n, visited, cc);
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

