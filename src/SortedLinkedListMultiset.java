import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	private LinkedListNode<T> headNode;
	private int size = 0;
	
	public SortedLinkedListMultiset() {
		// Implement me!
		headNode = new LinkedListNode<T>(null);
		size = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		
		LinkedListNode<T> p = headNode;
		LinkedListNode<T> node = new LinkedListNode<T>(item);
		
		while(p.getNext()!=null){
			if(((Comparable)p.getNext().getValue()).compareTo((Comparable)item)<0){
				p = p.getNext();
			}
			else{
				break;
			}
		}
		
		if(p.getNext()!=null && ((Comparable)p.getNext().getValue()).compareTo((Comparable)item) == 0){
			p.getNext().addElementCount();
		}
		else{
			if(p.getNext() != null){
			    node.setNext(p.getNext());
			}
			p.setNext(node);
		}

		
		size++;
		// Implement me!
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!
		int count = 0;
		LinkedListNode<T> node = headNode.getNext();
		
		while(node!=null){
			if(node.getValue().equals(item)){
				count = node.getCount();
				break;
			}
			node = node.getNext();
		}
		
		// default return, please override when you implement this method
		return count;
	}
	
	
	public void removeOne(T item) {
		LinkedListNode<T> p = headNode;
		while(p.getNext() != null){
			if(p.getNext().getValue().equals(item)){
				if(p.getNext().getCount() == 1){
					LinkedListNode<T> q = p.getNext();
					p.setNext(q.getNext());
					q.setNext(null);
					q = null;
					this.size--;
					break;
				}
				else{
					p.getNext().reduceElementCount();
					this.size--;
				}
			}
			p = p.getNext();
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		LinkedListNode<T> p = headNode;
		while(p.getNext() != null){
			if(p.getNext().getValue().equals(item)){
				LinkedListNode<T> q = p.getNext();
				p.setNext(q.getNext());
				q.setNext(null);
				q = null;
				this.size--;
			}
			else{
			    p = p.getNext();
			}
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
		LinkedListNode<T> node= headNode.getNext();
		while(node!=null){
			System.out.println(node.getValue() + "  |  " + node.getCount() );
			node = node.getNext();
		}
		
	} // end of print()
	
	private class LinkedListNode<T> {
		private int count;
		private T value;
		private LinkedListNode<T> next;
		
		public T getValue(){
			return this.value;
		}
		
		public LinkedListNode(T value){
			this.value = value;
			this.count = 1;
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
		
		public int getCount(){
			return this.count;
		}
		
		public void addElementCount(){
			this.count++;
		}
		
		public void reduceElementCount(){
			this.count--;
		}
	}
	
} // end of class SortedLinkedListMultiset