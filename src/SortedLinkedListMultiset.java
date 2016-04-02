import java.io.PrintStream;
import java.util.*;
/**
 * Sorted linked list implementation of a multiset.  Implements Multiset  abstract class.
 * 
 * @param T Type of elements that the multiset can hold.
 * 
 */
public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	/* head node */
	private LinkedListNode<T> headNode;
	
	/* size of the list */
	private int size = 0;
	
	public SortedLinkedListMultiset() {
		/* head node does not hold any real value */
		headNode = new LinkedListNode<T>(null);
		size = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		
		LinkedListNode<T> p = headNode;
		LinkedListNode<T> node = new LinkedListNode<T>(item);
		
		/* insert element before p */
		while(p.getNext()!=null){
			/* if value of p.next is less than item, go to next node */
			if(((Comparable)p.getNext().getValue()).compareTo((Comparable)item)<0){
				p = p.getNext();
			}
			else{
				break;
			}// end of if(((Comparable)p.getNext().getValue()).compareTo((Comparable)item)<0)

		}// end of while(p.getNext()!=null)
		
		/* if value of p.next ids equal to item, add 1 to count of p.next*/
		if(p.getNext()!=null && ((Comparable)p.getNext().getValue()).compareTo((Comparable)item) == 0){
			p.getNext().addElementCount();
		}
		else{
			/* add a new node after p before p.next */
			if(p.getNext() != null){
			    node.setNext(p.getNext());
			}
			p.setNext(node);
		}//end of (p.getNext()!=null && ((Comparable)p.getNext().getValue()).compareTo((Comparable)item) == 0)

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
		
		// return count of element if it is contained in the list, otherwise return 0;
		return count;
	}
	
	
	public void removeOne(T item) {
		LinkedListNode<T> p = headNode;
		
		/* start from head.next(head node, a node added for easy operation, does not hold value)*/
		while(p.getNext() != null){
			
			/* delete p.next */
			if(p.getNext().getValue().equals(item)){
				
				/* if count of p.next is 1, remove the node from list */
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
				}// end ofif(p.getNext().getCount() == 1)
				
			}// end of (p.getNext().getValue().equals(item))
			p = p.getNext();
			
		}// end of (p.getNext() != null)
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		LinkedListNode<T> p = headNode;

		/* start from head.next(head node, a node added for easy operation, does not hold value)*/
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
			}//end of if(p.getNext().getValue().equals(item))
		}//end of while (p.getNext() != null)
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
		
		/* occurence of the value */
		private int count;
		
		/* value of the node */
		private T value;
		
		/* next node */
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