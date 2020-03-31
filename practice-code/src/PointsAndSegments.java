import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        if(Math.log(starts.length) < points.length) {
            int[] cnt = new int[points.length];
	        Arrays.sort(starts);
	        Arrays.sort(ends);
	        for(int i = 0; i < points.length; ++i) {
	        	int num = starts.length;
	        	int s = starts.length - searchFirstGreater(starts, 0, starts.length - 1, points[i]);
	        	num = num - s;
	        	int e = searchFirstLower(ends, 0, ends.length - 1, points[i]) + 1;
	        	num = num - e;
	        	cnt[i] = num;
	        }
	        return cnt;
        }
        else {
        	return naiveCountSegments(starts, ends, points);
        }
    }
    
    private static int searchFirstGreater(int[] a, int l, int r, int x) {
    	if(l > r) {
    		return l;
    	}
    	int mid = (int)Math.floor((l+r)/2.0);
    	if(a[mid]==x) {
    		if( (mid+1>r) || (a[mid+1] > x) )
    			return mid+1;
    		else {
    			return searchFirstGreater(a,mid+1, r, x);
    		}
    	}
    	else if(a[mid] < x) {
    		return searchFirstGreater(a,mid+1, r, x);
    	}
    	else {
    		return searchFirstGreater(a,l, mid-1, x);
    	}
    }
    
    private static int searchFirstLower(int[] a, int l, int r, int x) {
    	if(l > r) {
    		return r;
    	}
    	int mid = (int)Math.floor((l+r)/2.0);
    	if(a[mid]==x) {
    		if( (mid-1 < l) || (a[mid-1] < x) )
    			return mid-1;
    		else {
    			return searchFirstLower(a,l, mid-1, x);
    		}
    	}
    	else if(a[mid] < x) {
    		return searchFirstLower(a,mid+1, r, x);
    	}
    	else {
    		return searchFirstLower(a,l, mid-1, x);
    	}    	
    }
    
    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
//        Scanner scanner = new Scanner(System.in);
//        int n, m;
//        m = scanner.nextInt();
//        for (int i = 0; i < m; i++) {
//            points[i] = scanner.nextInt();
//        }
//        n = scanner.nextInt();
//        System.out.print(searchFirstGreater(points, 0, points.length-1, n));

        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

