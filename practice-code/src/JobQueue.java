import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    
    private int LeftChild(int i) {
    	return 2*i+1;
    }
    
    private int RightChild(int i) {
    	return 2*i+2;
    }
    
    private void SiftDown(int i, int[] pQueue, long[] nextFreeTime) {
    	int minIndex = i;
    	if(LeftChild(i) < pQueue.length) {
    		if(nextFreeTime[pQueue[minIndex]] > nextFreeTime[pQueue[LeftChild(i)]]) {
        		minIndex = LeftChild(i);
    		}
    		else if ((nextFreeTime[pQueue[minIndex]] == nextFreeTime[pQueue[LeftChild(i)]]) && (pQueue[minIndex]>pQueue[LeftChild(i)])) {
    			minIndex = LeftChild(i);
    		}
    	}
    	if(RightChild(i) < pQueue.length) {
    		if(nextFreeTime[pQueue[minIndex]] > nextFreeTime[pQueue[RightChild(i)]]) {
    			minIndex = RightChild(i);
    		}
    		else if((nextFreeTime[pQueue[minIndex]] == nextFreeTime[pQueue[RightChild(i)]]) && (pQueue[minIndex]>pQueue[RightChild(i)])) {
    			minIndex = RightChild(i);
    		}
    	}
    	if(minIndex!=i) {
    		int tmp = pQueue[i];
    		pQueue[i] = pQueue[minIndex];
    		pQueue[minIndex] = tmp;
    		SiftDown(minIndex, pQueue, nextFreeTime);
    	}
    }


    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
    	long[] nextFreeTime = new long[numWorkers];
    	int[] pQueue = new int[numWorkers];
    	for(int i = 0; i < numWorkers; ++i) {
    		nextFreeTime[i] = 0;
    		pQueue[i] = i;
    	}
    	for(int i = 0; i < jobs.length; ++i) {
    		assignedWorker[i] = pQueue[0];
    		startTime[i] = nextFreeTime[pQueue[0]];
    		nextFreeTime[pQueue[0]] = nextFreeTime[pQueue[0]] + jobs[i];
    		SiftDown(0, pQueue, nextFreeTime);
    	}
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
