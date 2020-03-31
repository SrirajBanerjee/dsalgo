import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
    	int numCC = 0;
    	ArrayList<Integer>[] rev = reverse(adj);
    	int[] post = new int[adj.length];
    	dfs(rev,post);

    	boolean[] visited = new boolean[adj.length];
    	for(int i = 0; i < visited.length; ++i) {
    		visited[i] = false;
    	}
    	
    	// bucket sort
    	int[] indexOrder = new int[adj.length];
    	for(int i = 0; i < post.length; ++i) {
    		int o = post[i];
    		int index = post.length - o;
    		indexOrder[index] = i;
    	}
    	
    	for(int i = 0; i < indexOrder.length; ++i) {
    		if(visited[indexOrder[i]] == false) {
    			numCC++;
    			explore(adj, indexOrder[i], null, 0, visited);
    		}
    	}
        return numCC;
    }
    
    private static ArrayList<Integer>[] reverse(ArrayList<Integer>[] adj) {
    	ArrayList<Integer>[] rev = (ArrayList<Integer>[])new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            rev[i] = new ArrayList<Integer>();
        }
        for (int u = 0; u < adj.length; u++) {
            ArrayList<Integer> l = adj[u];
            for(int j = 0; j < l.size(); ++j) {
                Integer v = l.get(j);
                rev[v].add(u);            	
            }
        }
        return rev;
    }
    
    private static void dfs(ArrayList<Integer>[] adj, int[] post) {
    	int curPost = 0;
    	boolean[] visited = new boolean[adj.length];
    	for(int i = 0; i < visited.length; ++i) {
    		visited[i] = false;
    	}
    	for(int i = 0; i < post.length; ++i) {
			if(visited[i] == false) {
				curPost = explore(adj, i, post, curPost, visited);
    			curPost++;
    			post[i] = curPost;
			}
    	}
    }
    
    private static int explore(ArrayList<Integer>[] adj, int s, int[] post, int curPost, boolean[] visited) {
    	if(visited != null) {
    		visited[s] = true;
    	}
    	for(int i = 0; i < adj[s].size(); ++i) {
    		Integer n = adj[s].get(i);
    		if(post != null) {
    			if(visited[n] == false) {
	    			curPost = explore(adj, n, post, curPost, visited);
	    			curPost++;
	    			post[n] = curPost;
    			}
    		}
    		else
    		{
    			if(visited[n] == false) {
    				explore(adj, n, null, 0, visited);
    			}
    		}
    	}
    	return curPost;
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

