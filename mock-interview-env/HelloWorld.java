import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

public class HelloWorld
{
    private FastScanner in = null;
    private int[] data = null;

    public static void main(String[] args)
    {
        System.out.println("Hello World !");

        // Scanner s = new Scanner(System.in);
        // while(true)
        // {
        //     int num = s.nextInt();
        //     System.out.println("bitwise and with 1 = " + (num&1));
        //     System.out.println("right shift = " + (num >> 1));
        //     System.out.println("left shift = " + (num << 1));
        // }
        
        Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		int[] data = new int[t];
		for(int i = 0; i < t; ++i) {
		    data[i] = s.nextInt();
		}
		int[] res = new int[t];
        for(int i = 0; i < t; ++i) {
            int num = data[i];
            int one = 1;
            int sum = 0;
            while(num > 0) {
                sum = sum + (num&one);
                //System.out.println(sum);
                num = num >> 1;
            }
            res[i] = sum;
        }
        for(int i = 0; i < t; ++i) {
            System.out.println(res[i]);
        }

        // // Testing line input
        // System.out.println("Testing line input");
        // HelloWorld hw = new HelloWorld();
        // hw.lineInput();

        // // Testing Single input
        // System.out.println("Testing single input");
        // hw.singleInput();
    }

    // Read a single input
    public void singleInput()
    {
        Scanner s = new Scanner(System.in);
        String token = " ";
        while(token != null && !token.equals("exit"))
        {
            token = s.next();
            System.out.println("token = " + token);
        }
    }

    // Read a lines of input
    public void lineInput()
    {
        try
        {
            FastScanner in = new FastScanner();
            ArrayList<String> line = in.nextLine();
            while(line != null)
            {
                line = in.nextLine();
            }
            //readData();
        }
        catch(IOException e)
        {
            System.out.println("Exception occurred");
        }
    }

    private void readData() throws IOException 
    {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    static class FastScanner 
    {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public ArrayList<String> nextLine() throws IOException
        {
            String line = reader.readLine();
            if(line == null || line.length() == 0)
                return null;
            System.out.println("line = " + line);
            tokenizer = new StringTokenizer(line);
            ArrayList<String> retval = new ArrayList<String>();
            while(tokenizer.hasMoreTokens())
            {
                retval.add(tokenizer.nextToken());
            }
            System.out.println("Printing individual tokens");
            for(int i = 0; i < retval.size(); ++i)
            {
                System.out.println(retval.get(i));
            }
            return retval;
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

