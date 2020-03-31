import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        numberOfInversions += getInversionFromMerge(a,b,left,right);
        return numberOfInversions;
    }
    
    private static long getInversionFromMerge(int[] a, int[] b, int left, int right) {
    	int ave = (left + right) / 2;
    	int i = left;
    	int j = ave;
    	int k = left;
    	int numInversion = 0;
    	while((i < ave) && (j<right)) {
    		if(a[i] <= a[j]) {
    			b[k] = a[i];
    			i++;
    			k++;
    		}
    		else {
    			b[k] = a[j];
    			j++;
    			k++;
    			numInversion = numInversion + (ave-i);
    		}
    	}
    	while(i<ave) {
			b[k] = a[i];
			i++;
			k++;    		
    	}
    	while(j<right) {
    		b[k] = a[j];
			j++;
			k++;
    	}
    	for(i=left; i < right; ++i) {
    		a[i] = b[i];
    	}
    	return numInversion;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

