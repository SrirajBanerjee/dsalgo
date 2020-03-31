import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int n = values.length;
        int[] use = new int[n];
        for(int i = 0; i < n; ++i) {
        	use[i] = 0;
        }
        int rest = capacity;
        while(rest > 0) {
        	int maxVpwIdx = -1;
        	float maxVpw = 0;
        	for(int i = 0; i < n; ++i) {
        		if(((float)values[i]/weights[i] > maxVpw) && use[i] == 0) {
        			maxVpwIdx = i;
        			maxVpw = (float)values[i]/weights[i];
        		}
        	}
        	if(maxVpwIdx >= 0) {
        		use[maxVpwIdx] = 1;
        		if(weights[maxVpwIdx] > rest) {
        			value = value + (float)rest*values[maxVpwIdx]/weights[maxVpwIdx];
        			rest = 0;
        		}
            	else {
            		value = value + values[maxVpwIdx];
            		rest = rest - weights[maxVpwIdx];
            	}
        	}
        	else {
        		break;
        	}
        }
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
