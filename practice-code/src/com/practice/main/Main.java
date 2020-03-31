package com.practice.main;

import java.util.Scanner;

import com.practice.ds.LinkedList;
import com.practice.ds.MinHeap;

public class Main {

	public static void main(String[] args) {
		//testLinkedList();
		testMinHeap();
	}
	
	private static void testMinHeap() {
		MinHeap heap = new MinHeap();
		int[] arr = {4,6,2,7,9};
		heap.MakeMinHeap(arr);
		heap.printHeap();
		heap.Insert(8);
		heap.printHeap();
		heap.Insert(3);
		heap.printHeap();
		int val = heap.DeleteMin();
		System.out.println("val = " + val);
		val = heap.GetMin();
		System.out.println("val = " + val);
		MinHeap sortHeap = new MinHeap();
		int[] sortedArray = sortHeap.HeapSort(arr);
		System.out.print("\n");
		for(int i = 0; i < sortedArray.length; ++i) {
			System.out.print(sortedArray[i]);
			System.out.print(" ");
		}
	}
	
	private static void testLinkedList() {
		LinkedList l = new LinkedList();
		System.out.println("The length of the linked list is = " + l.ListLength());
		System.out.print("The linked list is = ");
		l.printList();
		
		String s = null;
		while(true) {
			System.out.println("Choose an operation");
			System.out.println("1. Insert a Node");
			System.out.println("2. Delete a Node");
			System.out.println("Enter (q/Q) to exit");
			
			Scanner in = new Scanner(System.in);
			s = in.nextLine();
			
			if(s.compareTo("1") == 0) {
				System.out.println("Enter Data");
				int data = in.nextInt();
				System.out.println("Enter Position");
				int pos = in.nextInt();
				l.insertNode(data, pos);
				l.printList();
			}
			else if(s.compareTo("2") == 0) {
				System.out.println("Enter Position");
				int pos = in.nextInt();
				l.deleteNode(pos);
				l.printList();				
			}
			else if(s.compareTo("q") == 0 || s.compareTo("Q") == 0) {
				System.out.println("Thank You !");
				break;
			}
			else {
				System.out.println("Wrong input. Please try again");
			}
		}
	}
}
