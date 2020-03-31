import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
    	int[] minCoins = new int[m+1];
    	minCoins[0] = 0;
    	minCoins[1] = 1;
    	for(int i=2; i <= m; ++i) {
    		int min = i;
    		if((i-1) >= 0) {
    			min = Math.min(min, minCoins[i-1]+1);
    		}
    		if((i-3) >= 0) {
    			min = Math.min(min, minCoins[i-3]+1);
    		}
    		if((i-4) >= 0) {
    			min = Math.min(min, minCoins[i-4]+1);
    		}
    		minCoins[i] = min;
    	}
        //write your code here
        return minCoins[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

