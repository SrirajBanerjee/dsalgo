import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
    
    class Node {
    	ArrayList<Node> children;
    	
    	Node() {
    		children = new ArrayList<Node>();
    	}
    	
    	public void AddChild(Node n) {
    		children.add(n);
    	}
    	
    	public ArrayList<Node> getChildren() {
    		return children;
    	}
    }

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
                        // Replace this code with a faster implementation
			Node[] nodeList = new Node[n];
			for(int i=0; i < n; ++i) {
				Node n = new Node();
				nodeList[i] = n;
			}
			Node head = null;
			for(int i=0; i<n; ++i) {
				if(parent[i] == -1) {
					head = nodeList[i];
				}
				else {
					Node parentNode = nodeList[parent[i]];
					parentNode.AddChild(nodeList[i]);					
				}
			}
			LinkedList<Node> q = new LinkedList<Node>();
			q.add(head);
			q.add(null);
			int maxHeight = 0;
			while(!q.isEmpty()) {
				Node top = q.removeFirst();
				if(top==null) {
					maxHeight = maxHeight + 1;
					if(!q.isEmpty()) {
						q.add(null);
					}
				}
				else {
					ArrayList<Node> children = top.getChildren();
					for(int i = 0; i < children.size(); ++i) {
						Node child = children.get(i);
						q.add(child);
					}
				}
			}
			return maxHeight;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
