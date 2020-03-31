package com.practice.ds;

import java.util.HashMap;

public class DisjointSet {
	
	private class DisjointSetNode {
		public int arrPos;
		public int element;
		public int setName;
	}
	
	private DisjointSetNode[] m_Array;
	private HashMap m_ElementPosMap;
	
	// Fast Union
	//	1. Slow Find
	//	2. Fast Find - Union by size
	//	3. Fast Find - Union by height
	//		For all the 3 above
	//			1. MakeSet
	//			2. Find
	//			3. Union
	
	public void MakeSet(int[] arr, int size) {
		
	}
	
	public int Find(int element) {
		return 0;
	}
	
	public void Union(int a, int b) {
		
	}

}
