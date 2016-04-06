import java.io.PrintStream;
import java.util.*;

/**
 * Linked list implementation of a multiset.  Implements Multiset  abstract class.
 * 
 * @param T Type of elements that the multiset can hold.
 * 
 */

public class LinkedListMultiset<T> extends Multiset<T>
{
	/* head pointer */
	private LinkedListNode<T> head;
	private int size = 0;
	
	public LinkedListMultiset() {
		head = null;
		size = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {	
		
		LinkedListNode<T> node = new LinkedListNode<T>(item);
		
		/* if the list is null set new node as head */
		if(head == null){
			head = node;
		}
		else{
			/* if head node contains the value add 1 to its count*/
			if(head.getValue().equals(item)){
				head.addElementCount();
			}else{
				/* add new node into the list*/
				LinkedListNode<T> p = head;
				while(p.getNext() !=null){				
					/* if list contains the value add 1 to count of this element */
					if(p.getNext().getValue().equals(item)){
						p.getNext().addElementCount();
						this.size++;
						return;
					}
					p = p.getNext();
				}// end of while(p.getNext() !=null)
				p.setNext(node);
			}// end of if(head.getValue().equals(item))
		}// end of if(head == null)
		this.size++;
	} // end of add()
	
	
	public int search(T item) {
		int count = 0;
		LinkedListNode<T> node = head;
		
		while(node!=null){
			if(node.getValue().equals(item)){
				count = node.getCount();
				break;
			}
			
			node = node.getNext();
		}//end of while(node!=null)
		// return count of element if it is contained in the list, otherwise return 0;
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		LinkedListNode<T> node= head;
		
		//remove one element from head
		if(head.getValue().equals(item)){
			if(head.getCount() == 1){
				/* remove head */
				head = head.getNext();
				node.setNext(null);
				node = null;
			}
			else{
				head.reduceElement();
			}// end of (head.getCount() == 1)
			this.size--;
		}
		//remove one element from other nodes
		else{
			while(node.getNext() != null){
				if(node.getNext().getValue().equals(item)){
					break;
				}
				node = node.getNext();
			}// end of while(node.getNext() != null)

			if(node.getNext() != null){
				if(node.getNext().getCount() == 1){
					/* remove the node */
					LinkedListNode<T> tobeDeleted = node.getNext();
				    node.setNext(tobeDeleted.getNext());
				    tobeDeleted.setNext(null);
				}
				else{
					node.getNext().reduceElement();
				}
				this.size--;
			}// end of if(node.getNext() != null)
		}// end of if(head.getValue().equals(item))
	} // end of removeOne()
	
	
	public void removeAll(T item) {
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
			}// end of if(p.getNext().getValue().equals(item))
		}// end of while(p!=null && p.getNext() != null)
	} // end of removeAll()

	public void print(PrintStream out) {

		LinkedListNode<T> node= head;
		while(node!=null){
            out.println(node.getValue() + "  |  " + node.getCount());
			node = node.getNext();
		}//while(node!=null)
		
		//printTest();
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