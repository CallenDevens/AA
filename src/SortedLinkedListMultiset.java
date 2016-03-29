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
		while(p.getNext()!=null && ((Comparable)p.getNext().getValue()).compareTo((Comparable)item)<0){
			p = p.getNext();
		}
		if(p.getNext() != null){
		    node.setNext(p.getNext());
		}
		p.setNext(node);
		
		size++;
		// Implement me!
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!
		int count = 0;
		LinkedListNode<T> node = headNode.getNext();
		
		//TODO fix the error
		while(node!=null){
			if(node.getValue().equals(item)){
				count++;
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
				LinkedListNode<T> q = p.getNext();
				p.setNext(q.getNext());
				q.setNext(null);
				q = null;
				this.size--;
				break;
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
		
		Set<T> valueSet = new HashSet<T>();
		while(node!=null){
			if(valueSet.contains(node.getValue())){
				;
			}
			else{
				T value = node.getValue();
				valueSet.add(value);
				System.out.println(value + "  |  " + this.search(value) );
			}
			node = node.getNext();
		}
		
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
	
} // end of class SortedLinkedListMultiset