import java.io.PrintStream;
import java.util.*;

//TODO compare String with equal while int with "=="


public class LinkedListMultiset<T> extends Multiset<T>
{
	private LinkedListNode<T> head;
	private int size = 0;
	
	public LinkedListMultiset() {
		// Implement me!
		head = null;
		size = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		
		LinkedListNode<T> node = new LinkedListNode<T>(item);
		
		if(head == null){
			head = node;
		}
		else{
			LinkedListNode<T> p = head;
			while(p.getNext() !=null ){
				p = p.getNext();
			}
			p.setNext(node);
		}
		this.size++;
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!
		int count = 0;
		LinkedListNode<T> node = head;
		
		//TODO fix the error
		while(node!=null){
			if(node.getValue().equals(item)){
				count++;
			}
			node = node.getNext();
		}
		
		// default return, please override when you implement this method
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		
		LinkedListNode<T> node= head;
		if(head.getValue().equals(item)){
			head = head.getNext();
			node.setNext(null);
			node = null;
			this.size--;
		}
		else{
			while(node.getNext() != null){
				if(node.getNext().getValue().equals(item)){
					break;
				}
				node = node.getNext();
			}
			if(node.getNext() != null){
				LinkedListNode<T> tobeDeleted = node.getNext();
			    node.setNext(tobeDeleted.getNext());
			    tobeDeleted.setNext(null);
				this.size--;
			}
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		
		LinkedListNode<T> p= head;
		
		while(head!=null&&head.getValue().equals(item) ){
			p = head;
			head = head.getNext();
			p.setNext(null);
			p = null;	
			this.size--;
		}
		
		p = head;
		
		while(p!=null && p.getNext() != null){
			if(p.getNext().getValue().equals(item)){
				LinkedListNode<T> q = p.getNext();
				p.setNext(q.getNext());
				q.setNext(null);
				q = null;
				
				this.size --;
			}
			else{
			    p = p.getNext();
			}
		}
	} // end of removeAll()
	public void printTest(){
		System.out.println(size);
		LinkedListNode<T> p = head;

		while(p!=null){
			System.out.print(p.getValue()+ "  ");
			p = p.getNext();
		}
	}
	
	public void print(PrintStream out) {
		// Implement me!
		LinkedListNode<T> node= head;
		
		Set<T> valueSet = new HashSet<T>();
		while(node!=null){
			if(valueSet.contains(node.getValue())){
				;
			}
			else{
				T value = node.getValue();
				valueSet.add(value);
				out.println(value + "  |  " + this.search(value) );
			}
			node = node.getNext();
		}
		
		//printTest();
	} // end of print()
	
	private class LinkedListNode<T> {
		private T value;
		private LinkedListNode<T> next;
		
		public T getValue(){
			return this.value;
		}
		
		public LinkedListNode(T value){
			this.value = value;
			next = null;
		}
		
		public LinkedListNode getNext(){
			return this.next;
		}
		
		public void setNext(LinkedListNode next){
			this.next = next;
		}
		
		public void setValue(T value){
			this.value = value;
		}
	}
	
} // end of class LinkedListMultiset