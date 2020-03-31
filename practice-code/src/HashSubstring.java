import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    
    private static Integer x,p;
    private static Long[] precomputed;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }
    
    private static void initializeHashParams(Data d) {
        x = 31;
        BigInteger bi = BigInteger.valueOf(2*d.text.length());
        p = Integer.parseInt(bi.nextProbablePrime().toString());
        precomputed = new Long[d.text.length()-d.pattern.length()+1];
    }
    
    private static Long hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * x + s.charAt(i)) % p;
        return hash;
    }
    
    private static Long pow(int x, int n) {
    	Long y = 1L;
    	for(int i = 1; i <=n; ++i) {
    		y = (y*x)%p;
    	}
    	return y;
    }
    
    private static void precompute(Data input) {
    	String s = input.pattern, t = input.text;
        int sl = s.length(), tl = t.length();
        Long hash = hashFunc(t.substring(tl-sl));
        precomputed[tl-sl] = hash;
        Long y = pow(x,sl);
    	for(int i = tl-sl-1; i >=0; --i) {
    		hash = t.charAt(i)%p + ((hash*x)%p) - (((t.charAt(i+sl)%p)*y)%p);
    		while(hash < 0) {
    			hash = hash+p;
    		}
    		hash = hash % p;
    		precomputed[i] = hash;
    	}
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }
    
    private static List<Integer> getOccurrences(Data input) {
    	initializeHashParams(input);
    	precompute(input);
        String s = input.pattern, t = input.text;
        int sl = s.length(), tl = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        Long h = hashFunc(s);
        for (int i = 0; i + sl <= tl; ++i) {
        	boolean equal = false;
        	if(h == precomputed[i]) {
        		equal = true;
			    for (int j = 0; j < sl; ++j) {
			    	if (s.charAt(j) != t.charAt(i + j)) {
			    		equal = false;
			    		break;
			    	}
			    }
        	}
            if (equal)
                occurrences.add(i);
	    }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
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

