import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
    	ArrayList<Integer> points = new ArrayList<Integer>();
    	int[] used = new int[segments.length];
    	int numUsed = 0;
    	while(numUsed < segments.length) {
    		Segment minSeg = null;
    		// find the seg with lowest end
    		for(int i=0; i < segments.length; ++i) {
    			if(used[i]==0 && ((minSeg==null) || segments[i].end < minSeg.end )) {
    				minSeg = segments[i];
    			}
    		}
    		// mark all the seg covered by the point
    		if(minSeg!=null) {
				points.add(minSeg.end);
    			for(int i=0; i < segments.length; ++i) {
        			if((segments[i].start <= minSeg.end) && (used[i]==0)) {
        				used[i] = 1;
        				numUsed++;
        			}
        		}
    		}
    	}
    	int[] array = points.stream().mapToInt(i->i).toArray();
        return array;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
