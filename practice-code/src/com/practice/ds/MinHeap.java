package com.practice.ds;

public class MinHeap {
	
	private int[] m_Array;
	private int m_Size;
	private int m_Count;
	
	public MinHeap() {
		Initialize();
	}
	
	private void Initialize() {
		m_Array = null;
		m_Size = 0;
		m_Count = 0;
	}
	
	public void MakeMinHeap(int[] iArr) {
		int size = iArr.length;
		m_Array = new int[size];
		for(int i = 0; i < size; ++i) {
			Insert(iArr[i]);
		}
	}

	public int GetMin() {
		if(m_Array != null)
			return m_Array[0];
		return -1;
	}
	
	public int DeleteMin() {
		int retVal = m_Array[0];
		m_Array[0] = m_Array[m_Count-1];
		m_Count--;
		PercolateDown(0);
		return retVal;
	}
	
	public void Insert(int val) {
		if(m_Array==null)
			return;
		m_Count++;
		if(m_Count > m_Size) {
			ResizeHeap();
		}
		m_Array[m_Count-1] = val;
		int i = m_Count-1;
		int p = GetParent(i);
		while(i > 0 && m_Array[p] > m_Array[i]) {
			int temp = m_Array[p];
			m_Array[p] = m_Array[i];
			m_Array[i] = temp;
			i=p;
		}
	}
	
	public int[] HeapSort(int[] arr) {
		m_Array = arr;
		m_Count = 0;
		m_Size = arr.length;
		for(int i = 0; i < m_Size; ++i) {
			Insert(arr[i]);
		}
		// TODO Sort the array
		while(m_Count > 1) {
			int temp = m_Array[m_Count-1];
			m_Array[m_Count-1] = m_Array[0];
			m_Array[0] = temp;
			System.out.print("m_Count = " + m_Count + " ");
			System.out.print("m_Array[m_Count-1] = " + m_Array[m_Count-1] + "\n");
			PercolateDown(0);
			m_Count--;
		}
		int[] retval = m_Array;
		//Initialize();
		return retval;
	}
	
	public void printHeap() {
		for(int i = 0; i < m_Count; ++i) {
			System.out.print(m_Array[i]);
			System.out.print(" ");
		}
		System.out.print("\n");		
	}
	
	private void PercolateDown(int pos) {
		if(pos >= m_Count || pos < 0)
			return;
		int minPos = pos;
		int childPos = GetLeft(pos);
		if(childPos < m_Count && m_Array[childPos] < m_Array[pos])
			minPos = childPos;
		childPos = GetRight(pos);
		if(childPos < m_Count && m_Array[childPos] < m_Array[pos])
			minPos = childPos;
		if(minPos != pos) {
			// swap pos and maxPos
			int temp = m_Array[pos];
			m_Array[pos] = m_Array[minPos];
			m_Array[minPos] = temp;
			PercolateDown(minPos);
		}
	}
	
	private void ResizeHeap() {
		int newSize = 0;
		if(m_Size == 0) {
			newSize = 1;
		}
		else {
			newSize = m_Size*2;
		}
		int[] newArr = new int[newSize];
		for(int i = 0; i < m_Size; ++i) {
			newArr[i] = m_Array[i];
		}
		m_Size = newSize;
		m_Array = newArr;
	}
	
	private int GetParent(int i) {
		if(i==0)
			return 0;
		return (i-1)/2;
	}
	private int GetLeft(int i) {
		return 2*i+1;
	}
	private int GetRight(int i) {
		return 2*i+2;
	}
}
