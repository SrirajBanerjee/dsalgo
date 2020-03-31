/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class CardRotation {

	private static int[] data;

	private static ArrayList<Integer> getCardOrder(int n) {
	    Queue<Integer> order = new LinkedList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>(n);
		for(int i = 0; i < n; ++i)
		{
			order.add(i);
			result.add(0);
		}
		int numCards = n;
	    for(int i = 0; i < n; ++i) {
	        int pos = i+1;
			pos = pos % numCards;
			int popNum = pos - 1;
			while(popNum >= 0)
			{
				Integer p = order.poll();
				order.add(p);
				popNum--;
			}
			Integer exPos = order.poll();
			// System.out.print("exPos = " + exPos + " card = " + (i+1));
			result.set(exPos, i+1);
			numCards--;
	    }
		return result;
	}


	private static void readInput()
	{
		Scanner s = new Scanner(System.in);
    	int t = s.nextInt();
    	data = new int[t];
    	for(int i = 0; i < t; ++i) {
    	    data[i] = s.nextInt();
    	}
	}

	private static void printOutput(ArrayList<ArrayList<Integer>> res)
	{
    	for(int i = 0; i < data.length; ++i) {
    	    ArrayList<Integer> order = res.get(i);
    	    if(order == null) {
    	        System.out.println("-1");
    	    }
    	    else {
        	    for(int j = 0; j < order.size(); ++j) {
        	        System.out.print(order.get(j) + " ");
        	    }
    	    }
    	    System.out.println("");
    	}
	}

	private static ArrayList<ArrayList<Integer>> solve()
	{
    	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    	for(int i = 0; i < data.length; ++i) {
    	    ArrayList<Integer> order = getCardOrder(data[i]);
    	    res.add(order);
    	}
		return res;
	}

	public static void main (String[] args) {
    	readInput();
		ArrayList<ArrayList<Integer>> res = solve();
    	printOutput(res);
	}	
}