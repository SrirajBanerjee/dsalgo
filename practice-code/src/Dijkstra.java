import java.util.*;

public class Dijkstra {
	
	private static class Node
	{
		public int n;
		public long v;
		
		Node(int num, long val)
		{
			this.n = num;
			this.v = val;
		}
	}
	
	private static class PQueue
	{
		Node[] q;
		int l;
		Map<Integer, Integer> distQPosMap;
		
		PQueue()
		{
			q = new Node[1];
			l = 0;
			distQPosMap = new HashMap<Integer, Integer>();
		}
		
		private void printDS(String title)
		{
			System.out.println(title);
			System.out.print("q = ");
			for(int i = 0; i < q.length; ++i)
			{
				if(q[i] == null)
				{
					System.out.print("null,null" + " ");
				}
				else
				{
					System.out.print(q[i].n + "," + q[i].v + " ");
				}
			}
			System.out.println("");
			System.out.print("Map = ");
			for(Integer key : distQPosMap.keySet())
			{
				System.out.print(key + ":" + distQPosMap.get(key) + " ");
			}
			System.out.println("");
		}
		
	    public void addToQueue(Node node)
	    {
	    	if(l >= q.length) {
	    		resizeQ();
	    	}
	    	q[l] = node;
	    	distQPosMap.put(node.n, l);
	    	l++;
	    	SiftUp(l-1);
	    	//printDS("addToQueue n,v = " + node.n + "," + node.v);
	    }
	    
	    public Node extractMin()
	    {
	    	Node retVal = q[0];
	    	q[0] = q[l-1];
	    	q[l-1] = null;
	    	l--;
	    	distQPosMap.remove(retVal.n);
	    	if(q[0] != null)
	    	{
		    	distQPosMap.put(q[0].n, 0);
		    	SiftDown(0);
	    	}
	    	//printDS("extractMin");
	    	return retVal;
	    }
	    
	    public void changePriority(int i, long newVal)
	    {
	    	int qPos = distQPosMap.get(i);
	    	Node node = q[qPos];
	    	if(newVal > node.v)
	    	{
	    		node.v = newVal;
	    		SiftDown(qPos);
	    	}
	    	else if(newVal < node.v)
	    	{
	    		node.v = newVal;
	    		SiftUp(qPos);
	    	}
	    	//printDS("changePriority n,v = " + i + "," + newVal);
	    }
	    
	    public boolean isEmpty()
	    {
	    	return l==0;
	    }
	    
	    private int parent(int i) {
	    	return (int)((i-1)/2);
	    }
		
	    private int LeftChild(int i) {
	    	return 2*i+1;
	    }
	    
	    private int RightChild(int i) {
	    	return 2*i+2;
	    }
	    	    
	    private void resizeQ() {
	    	Node[] newQ = new Node[2*l];
	    	for(int i = 0; i < l; ++i)
	    	{
	    		Node newNode = new Node(q[i].n, q[i].v);
	    		newQ[i] = newNode;
	    	}
	    	q = newQ;
	    }
	    
	    private void SiftUp(int i)
	    {
	    	if(i==0 || i >= l)
	    		return;
	    	if(q[i].v < q[parent(i)].v)
	    	{
	    		Node tmp = q[i];
	    		q[i] = q[parent(i)];
	    		q[parent(i)] = tmp;
	    		distQPosMap.put(q[i].n, i);
	    		distQPosMap.put(q[parent(i)].n, parent(i));
	    		SiftUp(parent(i));
	    	}
	    }
		
	    private void SiftDown(int i) {
	    	int minIndex = i;
	    	if(LeftChild(i) < l) {
	    		if(q[minIndex].v > q[LeftChild(i)].v) {
	        		minIndex = LeftChild(i);
	    		}
	    	}
	    	if(RightChild(i) < l) {
	    		if(q[minIndex].v > q[RightChild(i)].v) {
	    			minIndex = RightChild(i);
	    		}
	    	}
	    	if(minIndex!=i) {
	    		Node tmp = q[i];
	    		q[i] = q[minIndex];
	    		q[minIndex] = tmp;
	    		distQPosMap.put(q[i].n, i);
	    		distQPosMap.put(q[minIndex].n, minIndex);	    		
	    		SiftDown(minIndex);
	    	}
	    }
	}
		
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	Long[] distArr = new Long[adj.length];
    	PQueue pQueue = new PQueue();
    	Node node = new Node(s,0);
    	pQueue.addToQueue(node);
    	distArr[s] = 0L;
    	while(!pQueue.isEmpty()) {
    		Node curNode = pQueue.extractMin();
    		Long curDist = curNode.v;
    		if(curNode.n == t) {
    			return curDist;
    		}
        	ArrayList<Integer> nList = adj[curNode.n];
        	ArrayList<Integer> nCost = cost[curNode.n];
        	for(int i = 0; i < nList.size(); ++i) {
        		Integer n = nList.get(i);
        		Integer c = nCost.get(i);
    			if(distArr[n] == null) {
    				node = new Node(n,curDist+c);
    				pQueue.addToQueue(node);
        			distArr[n] = curDist+c;        				
    			}
    			else {
    				if((curDist + c) < distArr[n]) {
    					long newDist = curDist + c;
    					pQueue.changePriority(n, newDist);
    					distArr[n] = newDist;
    				}
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

