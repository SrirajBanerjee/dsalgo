import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
    	Long[] distArr = new Long[adj.length];
    	boolean changeDetected = false;
    	LinkedList<Integer> order = toposort(adj);
    	LinkedList<Integer> nodeList = new LinkedList<Integer>();
    	for(int i = 0; i < distArr.length-1; ++i)
    	{
    		// do bfs
    		changeDetected = bfs(adj, cost, distArr, order, nodeList);
        	if(!changeDetected)
        		return 0;
    	}
    	// Perform another round and check if there is a change detected
    	// do bfs
    	changeDetected = bfs(adj, cost, distArr, order, nodeList);
    	if(!changeDetected)
    		return 0;
    	
        return 1;
    }
    
    private static boolean bfs(	ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, Long[] distArr, 
    							LinkedList<Integer> order, LinkedList<Integer> nodeList)
    {
    	boolean changeDetected = false;
    	Integer[] visited = new Integer[adj.length];
    	if(nodeList.size() <= 0)
    	{
	        for (int k : order) {
	    		if(visited[k] == null)
	    		{
	    			if(distArr[k] == null)
	    	    	{
	    	    		distArr[k] = 0L;
	    	    	}
	    			nodeList.add(k);
	    			boolean haschanged = bfsAtS(adj, cost, distArr, visited, k);
	    			if(haschanged)
	    				changeDetected = true;
	    		}
	    	}
    	}
    	else
    	{
	        for (int k : nodeList) {
    			boolean haschanged = bfsAtS(adj, cost, distArr, visited, k);
    			if(haschanged)
    				changeDetected = true;
	    	}    		
    	}
    	return changeDetected;
    }
    
    private static boolean bfsAtS(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, Long[] distArr, Integer[] visited, int s)
    {
    	boolean changed = false;
    	LinkedList<Integer> q = new LinkedList<Integer>();
    	q.add(s);
    	while(!q.isEmpty())
    	{
    		Integer n = q.poll();
    		visited[n] = 1;
    		ArrayList<Integer> nAdjArr = adj[n];
    		ArrayList<Integer> nCostArr = cost[n];
    		for(int i = 0; i < nAdjArr.size(); ++i)
    		{
    			Integer nAdj = nAdjArr.get(i);
    			Integer nCost = nCostArr.get(i);
    			if(visited[nAdj] == null || visited[nAdj] == 0)
    			{
    				q.add(nAdj);
    			}
    			if(distArr[nAdj] == null)
    			{
    				distArr[nAdj] = distArr[n] + nCost;
    				changed = true;
    			}
    			else if(distArr[n] + nCost < distArr[nAdj])
    			{
    				distArr[nAdj] = distArr[n] + nCost;
    				changed = true;
    			}
    		}
    	}
    	return changed;
    }
    
    private static LinkedList<Integer> toposort(ArrayList<Integer>[] adj) {
        boolean used[] = new boolean[adj.length];
        LinkedList<Integer> order = new LinkedList<Integer>();
    	for(int i = 0; i < used.length; ++i) {
			if(used[i] == false) {
				used[i] = true;
				dfs(adj, used, order, i);
		    	order.add(0,i);
		    	
			}
    	}
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] used, LinkedList<Integer> order, int s) {
    	for(int i = 0; i < adj[s].size(); ++i) {
    		Integer n = adj[s].get(i);
    		if(used[n] == false) {
    	    	used[n] = true;
    			dfs(adj, used, order, n);
    	    	order.add(0,n);
    		}
    	}
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

