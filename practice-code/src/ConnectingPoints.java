import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConnectingPoints {
	
	private static class Node
	{
		public int n;
		public double v;
		
		Node(int num, double val)
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
	    
	    public void changePriority(int i, double newVal)
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

	
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        if(x.length <= 1)
        	return result;
        //write your code here
        Double[] distArr = new Double[x.length];
        Boolean[] added = new Boolean[x.length];
        PQueue pq = new PQueue();
        pq.addToQueue(new Node(0,0.));
        distArr[0] = 0.;
//        for(int i = 1; i < x.length; ++i)
//        {
//        	Double dist = Math.pow((Math.pow((x[0] - x[i]),2) + Math.pow((y[0] - y[i]), 2)),0.5);
//        	pq.addToQueue(new Node(i, dist));
//        	distArr[i] = dist;
//        }
        while(!pq.isEmpty())
        {
        	Node node = pq.extractMin();
        	result = result+node.v;
        	added[node.n] = true;
            for(int i = 0; i < x.length; ++i)
            {
            	if(added[i] == null)
            	{
	            	Double dist = Math.pow((Math.pow((x[node.n] - x[i]),2) + Math.pow((y[node.n] - y[i]), 2)),0.5);
	            	if(distArr[i] == null)
	            	{
	                	pq.addToQueue(new Node(i, dist));
	                	distArr[i] = dist;
	            	}
	            	else if(dist < distArr[i])
	            	{
	            		pq.changePriority(i, dist);
	            		distArr[i] = dist;
	            	}
            	}
            }            
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

