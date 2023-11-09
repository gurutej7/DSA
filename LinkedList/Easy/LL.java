package LinkedList.Easy;

public class LL {
	//Head Node 
	Node head;

	//Node class
	class Node{
		int data;
		Node next;
		//Constructer
		Node(){
			this.data = 0;
			this.next = null;
		}
		//Constructer with argument
		Node(int data){
			this.data = data;
			this.next = null;
		}
	}

	//Function to addFirst
	private void addFirst(int data){
		//case: If the list is empty, create a node and assign it as head
		if(head == null){
			Node temp = new Node(data);
			head = temp;
			return;
		}
		else{
			Node temp = new Node(data);
			temp.next = head;
			head = temp;
		}
	}
	//Function to add Last
	private void addLast(int data){
		//case: If the list is empty, create a node and assign it as head
		if(head == null){
			Node temp = new Node(data);
			head = temp;
			return;
		}
			// curr = Current Node at which we are
		Node curr = head;
		//Traverse  to the existing last node and mark its next to the new node
		while(curr.next != null) curr = curr.next;

		Node newNode = new Node(data);
		curr.next = newNode;
	}
	//Function to print the List
	private void printList(){
		if(head == null) {
			System.out.println("List is Empty");
			return;
		}

		Node curr = head;
		while (curr!=null) {
			System.out.print(curr.data + "->");
			curr = curr.next;
		}
		System.out.println("null");
	}

	//Function to remove First Element in a Linked list
	private void removeFirst(){
		if(head == null) {
			System.out.println("List is Empty");
			return;
		}
		if(head.next == null){
			head = null;
			return;
		}
		head = head.next;
		return;
	}

	//Function to remove Last Element in a Linked List
	private void removeLast(){
		if(head == null) {
			System.out.println("List is Empty");
			return;
		}
		if(head.next == null){
			head = null;
			return;
		}
		//We have to reach the second last node and mark its next as null
		Node curr = head;
		while(curr.next.next !=null) curr = curr.next;
		
		curr.next = null;
		return;

	}

	//By default remove from last function
	private void remove(){
		if(head == null) {
			System.out.println("List is Empty");
			return;
		}
		if(head.next == null){
			head = null;
			return;
		}
		//We have to reach the second last node and mark its next as null
		Node curr = head;
		while(curr.next.next !=null) curr = curr.next;
		
		curr.next = null;
		return;

	}

	//By default add at last
	private void add(int data){
		//case: If the list is empty, create a node and assign it as head
		if(head == null){
			Node temp = new Node(data);
			head = temp;
			return;
		}
			// curr = Current Node at which we are
		Node curr = head;
		//Traverse  to the existing last node and mark its next to the new node
		while(curr.next != null) curr = curr.next;

		Node newNode = new Node(data);
		curr.next = newNode;
	}


		public static void main(String[] args) {
			LL list = new LL();
			
			//1
			list.addFirst(1);
			list.addLast(4);
			list.printList();
			//2
			list.addFirst(2);
			list.addLast(5);
			list.printList();
			//3
			list.addLast(10);
			list.add(100);
			list.printList();
			//4
			list.removeFirst();
			list.removeLast();
			list.printList();
			//5
			list.removeFirst();
			list.removeLast();
			list.printList();

			//6
			list.removeFirst();
			list.remove();
			list.printList();

		}
  
}
