package com.practice.ds;

public class LinkedList {
	private LinkedListNode m_head;
	
	public LinkedList() {
		int arr[] = {1,2,3,4,5};
		create(arr);
	}

	public LinkedList(int[] arr) {
		create(arr);
	}
	
	private void create(int[] arr) {
		LinkedListNode next = null;
		for(int i = arr.length-1; i >= 0; --i ) {
			int data = arr[i];
			LinkedListNode node = new LinkedListNode(data);
			node.setNext(next);
			next = node;
			if(i==0) {
				m_head = node;
			}
		}
	}
	
	public int ListLength() {
		LinkedListNode node = m_head;
		int len = 0;
		while(node != null) {
			node = node.getNext();
			len++;
		}
		return len;
	}
	
	public LinkedListNode getNode(int pos) {
		LinkedListNode retNode = m_head;
		if(pos < 1)
			return null;
		else if (pos == 1) {
			return m_head;
		}
		else {
			int count = 1;
			while(count < pos) {
				retNode = retNode.getNext();
				count++;
				if(retNode == null) {
					return null;
				}
			}
		}
		return retNode;
	}
	
	public void printList() {
		LinkedListNode node = m_head;
		while(node != null) {
			System.out.print(node.getData() + " ");
			node = node.getNext();
		}
		System.out.println("");
	}
	
	// position - 
	//		0  	- start
	//		-1 	- end
	//		n 	- anywhere in between
	public void insertNode(int data, int position) {
		if(position==0) {
			insertAtBegin(data);
		}
		else if(position < 0) {
			insertAtEnd(data);
		}
		else {
			insertAtMiddle(data,position);
		}
	}
	
	public void deleteNode(int position) {
		if(position==0) {
			deleteAtBegin();
		}
		else if(position < 0) {
			deleteAtEnd();
		}
		else {
			deleteAtMiddle(position);
		}
		
	}
	
	private void insertAtBegin(int data) {
		LinkedListNode node = new LinkedListNode(data);
		node.setNext(m_head);
		m_head = node;
	}
	
	private void insertAtEnd(int data) {
		LinkedListNode last_node = m_head;
		while(last_node.getNext()!=null) {
			last_node = last_node.getNext();
		}
		LinkedListNode node = new LinkedListNode(data);
		node.setNext(null);
		last_node.setNext(node);
	}
	
	private void insertAtMiddle(int data, int position) {
		LinkedListNode beforeNode = getNode(position);
		if(beforeNode == null) {
			return;
		}
		LinkedListNode node = new LinkedListNode(data);
		node.setNext(beforeNode.getNext());
		beforeNode.setNext(node);
	}

	private void deleteAtBegin() {
		LinkedListNode firstNode = m_head;
		m_head = m_head.getNext();
		firstNode.setNext(null);
	}
	
	private void deleteAtEnd() {
		int n = ListLength();
		LinkedListNode lastButOneNode = getNode(n-1);
		if(lastButOneNode != null) {
			lastButOneNode.setNext(null);
		}
	}
	
	private void deleteAtMiddle(int pos) {
		int len = ListLength();
		if(pos <= 1) {
			deleteAtBegin();
		}
		else if( pos >= len) {
			deleteAtEnd();
		}
		else {
			LinkedListNode prevNode = getNode(pos-1);
			LinkedListNode nextNode = getNode(pos+1);
			if(prevNode != null && nextNode != null) {
				prevNode.setNext(nextNode);
			}
		}
	}


}
