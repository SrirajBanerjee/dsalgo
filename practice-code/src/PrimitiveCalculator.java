import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence_err(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }
    
    private static List<Integer> optimal_sequence(int n) {
    	List<Integer> seq = new ArrayList<Integer>();
    	int[] minOp = new int[n+1];
    	minOp[0] = 0;
    	minOp[1] = 0;
    	for(int i = 2; i <= n; ++i) {
    		// Addition of 1 case
    		int min = minOp[i-1] + 1;
    		if(i%2==0) {
    			min = Math.min(min, minOp[i/2] + 1);
    		}
    		if(i%3==0) {
    			min = Math.min(min, minOp[i/3] + 1);
    		}
    		minOp[i] = min;
    	}
    	int num = n;
    	seq.add(n);
    	while (num > 1) {
    		if(minOp[num-1] == (minOp[num]-1)) {
    			seq.add(num-1);
    			num = num-1;
    		}
    		else if((num%2==0) && minOp[num/2] == (minOp[num]-1)) {
    			seq.add(num/2);
    			num = num/2;
    		}
    		else if((num%3==0) && minOp[num/3] == (minOp[num]-1)) {
    			seq.add(num/3);
    			num = num/3;
    		}
    		else {
    			System.out.print("Error in reconstruction");
    			break;
    		}
    	}
    	Collections.reverse(seq);
    	return seq;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

