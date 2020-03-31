import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
    	int numCoins = 0;
        int rest = m;
        while(rest > 0) {
        	if(rest >= 10) {
        		int n = rest/10;
        		numCoins = numCoins + n;
        		rest = rest%10; 
        	}
        	else if(rest >= 5) {
        		int n = rest/5;
        		numCoins = numCoins + n;
        		rest = rest%5;
        	}
        	else {
        		numCoins = numCoins + rest;
        		rest = 0;
        	}
        }
        return numCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
//    	int m = 2;
        System.out.println(getChange(m));

    }
}

