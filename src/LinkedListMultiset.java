import java.io.PrintStream;
import java.util.*;

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
			
			if(head.getValue().equals(item)){
				head.addElementCount();
			}else{
				
				LinkedListNode<T> p = head;
				while(p.getNext() !=null){
					if(p.getValue().equals(item)){
						p.addElementCount();
						this.size++;
						return;
					}
					p = p.getNext();
				}
				p.setNext(node);
			}

		}
		this.size++;
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!
		int count = 0;
		LinkedListNode<T> node = head;
		
		while(node!=null){
			if(node.getValue().equals(item)){
				count = node.getCount();
				break;
			}
		}
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		
		LinkedListNode<T> node= head;
		if(head.getValue().equals(item)){
			if(head.getCount() == 1){
				head = head.getNext();
				node.setNext(null);
				node = null;
			}
			else{
				head.reduceElement();
			}
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
				if(node.getNext().getCount() == 1){
					LinkedListNode<T> tobeDeleted = node.getNext();
				    node.setNext(tobeDeleted.getNext());
				    tobeDeleted.setNext(null);
				}
				else{
					node.getNext().reduceElement();
				}
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
		while(node!=null){
            out.println(node.getValue() + "  |  " + node.getCount());
			node = node.getNext();
		}
		
		//printTest();
	} // end of print()
	
	private class LinkedListNode<T> {
		private int count;
		private T value;
		private LinkedListNode<T> next;
		
		public T getValue(){
			return this.value;
		}
		
		public void addElementCount(){
			this.count++;
		}
		
		public int getCount(){
			return this.count;
		}
		
		public void reduceElement(){
			this.count--;
		}
		public LinkedListNode(T value){
			this.value = value;
			next = null;
			this.count = 1;
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