import java.io.PrintStream;
import java.util.*;

import javax.xml.soap.Node;
/**
 * Binary Search Tree implementation of a multiset.  Implements Multiset  abstract class.
 * 
 * @param T Type of elements that the multiset can hold.
 * 
 */
public class BstMultiset<T> extends Multiset<T>
{
	/* root node */
	private TreeNode<T> root;
	
	/* size of the tree */
	private int size;
	
	public BstMultiset() {
		this.root = null;
		this.size = 0;
	} // end of BstMultiset()

	public void add(T item) {
		if(this.root == null){
			this.root = new TreeNode(item);;
		}
		else{
			insert(item, this.root);			
		}
	} // end of add()
	
	private void insert(T item, TreeNode node){
		if(node == null){
			return;
		}
		else if(((Comparable)node.getValue()).compareTo((Comparable)item)>0){
			
			if(node.getLeftChild() == null){
				/* set new node as left child of node */
				TreeNode newNode = new TreeNode(item);
				node.setLeftChild(newNode);
				newNode.setParent(node);
			}
			else{
				insert(item, node.getLeftChild());				
			}// end of if(node.getLeftChild() == null)

		}// end of else if(((Comparable)node.getValue()).compareTo((Comparable)item)>0)
		
		else if(((Comparable)node.getValue()).compareTo((Comparable)item)<0){
			if(node.getRightChild() == null){
				/* set new node as right child of node */
				TreeNode newNode = new TreeNode(item);
				node.setRightChild(newNode);
				newNode.setParent(node);
			}
			else{
				insert(item, node.getRightChild());				
			}// end of if(node.getRightChild() == null)
		}// end of else if(((Comparable)node.getValue()).compareTo((Comparable)item)<0)
		else{
			/* value of node is equals to item */
			node.addElementCount();
		}// end of if(node == null)		
		size++;
	}

	public int search(T item) {
		// search the item in recursive way
		return search(item, this.root);
	} // end of add()
	
	public int search(T item, TreeNode node){		
		// search the item in recursive way
		if(node == null){
			/* no result */
			return 0;
		}
		else if(((Comparable)node.getValue()).compareTo((Comparable)item)>0){
			return search(item, node.getLeftChild());
		}
		else if(((Comparable)node.getValue()).compareTo((Comparable)item)<0){
			return search(item, node.getRightChild());
		}
		else{
			return node.getCount();
		}
	}


	public void removeOne(T item) {
		removeOne(item, root);
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		removeAll(item, root);
	} // end of removeAll()


	public void print(PrintStream out) {
		// Implement me!
		print(out, this.root);
	} // end of print()
	
	public void print(PrintStream out,TreeNode n){
        if(n != null){
            if(n.getLeftChild() != null){
                print(out, n.getLeftChild());;
            }
            out.println(n.getValue() + " | " + n.getCount());
            if(n.getRightChild() != null){
                print(out, n.getRightChild());;
            }
        }
    }
	
    private void removeOne(T item,TreeNode node){
        int compare = ((Comparable)node.getValue()).compareTo((Comparable)item);
        if(compare == 0){
            if(node.getCount()>1){
                node.reduceElementCount();
            }else{
            	deleteNode(node);
            }
        }else if(compare > 0){
            if(node.getLeftChild() != null){
                removeOne(item,node.getLeftChild());
            }else{
                return;
            }
        }else{
            if(node.getRightChild() != null){
                removeOne(item,node.getRightChild());
            }else{
                return;
            }
        }// end of if(compare == 0)
    }
    
    private void removeAll(T item,TreeNode node){
        int compare = ((Comparable)node.getValue()).compareTo((Comparable)item);
        if(compare == 0){
            deleteNode(node);
        }else if(compare > 0){
            if(node.getLeftChild() != null){
                removeAll(item, node.getLeftChild());
            }else{
                return;
            }
        }else{
            if(node.getRightChild() != null){
                removeAll(item, node.getRightChild());
            }else{
                return;
            }
        }// end of if(compare == 0)
    }
    
    public void deleteNode(TreeNode node){
        /**
        ** node is a Leaf node, delete directly
        ** node has got an child(left or right). 
        ** node has two children nodes
        **/
        if(node.getLeftChild() == null && node.getRightChild() == null){
        	
        	/* node is a Leaf node, delete directly */
        	TreeNode parent = node.getParent();
        	if(parent == null){
        		/* node is root node */
        		this.root = null;
        	}
        	else{
            	if(parent.getLeftChild() == node){
            		parent.setLeftChild(null);
            	}
            	else{
            		parent.setRightChild(null);
            	}
            	node.setParent(null);
                node = null;	
                
        	}// end of if(parent == null)
    		this.size--;

        }//end of if(node.getLeftChild() == null && node.getRightChild() == null)
        
        else if(node.getLeftChild()!=null && node.getRightChild() == null){
        	 
        	/* node has left child, exchange node and its left child */
        	TreeNode parent = node.getParent();
        	TreeNode leftChild = node.getLeftChild();

        	leftChild.setParent(parent);
        	
        	if(parent!= null){
        	    if(parent.getLeftChild() == node){
        		    parent.setLeftChild(leftChild);
        	    }
        	    else{
        		    parent.setRightChild(leftChild);
        	    }

        	    leftChild.setParent(parent);
        	}
        	else{
        		/* node is root node */
        		this.root = leftChild;
        	}// end of if(parent!= null)
        	
        	node.setParent(null);
        	node.setLeftChild(null);
        	node = null;
        	this.size--;
            
        }// end of  else if(node.getLeftChild()!=null && node.getRightChild() == null)
        else if(node.getLeftChild() == null && node.getRightChild() != null){
        	
        	/* node has left child, exchange node and its right child */
        	TreeNode parent = node.getParent();
        	TreeNode rightChild = node.getRightChild();
        	
        	rightChild.setParent(parent);
        	
        	if(parent!=null){
        	     if(parent.getLeftChild() == node){
        		     parent.setLeftChild(rightChild);
        	    }
        	    else{
        		    parent.setRightChild(rightChild);
        	    }

        	    rightChild.setParent(parent);
        	}else{
        		this.root = rightChild;
        	}
        	
        	node.setParent(null);
        	node.setRightChild(null);
        	node = null;
        	this.size--;

        }//end of else if(node.getLeftChild() == null && node.getRightChild() != null)
        else{
            //node has two children nodes
            
        	TreeNode newRoot = node.getRightChild();
            
            while (newRoot.getLeftChild()!= null){
            	newRoot = newRoot.getLeftChild();//find the minimum of right subtree
            }
            node.setValue(newRoot.value);
            node.setCount(newRoot.getCount());
            
            if(newRoot == node.getRightChild()){
            	node.setRightChild(newRoot.getRightChild());
            	newRoot.setRightChild(null);
            }
            else{
            	if(newRoot.getRightChild()!=null){
            		newRoot.getRightChild().setParent(newRoot.getParent());
            	}
                newRoot.getParent().setLeftChild(newRoot.getRightChild());
            }


            newRoot.setParent(null);
            newRoot = null;
            
            this.size--;
        }
    }

    /*Nodes of BST store value and occurence of elements*/
	private class TreeNode<T> {
		private int count;
		private T value;
		
		private TreeNode<T> parent;
		private TreeNode<T> leftChild;
		private TreeNode<T> rightChild;

		
		public T getValue(){
			return this.value;
		}
		
		public TreeNode getParent() {
			return this.parent;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public TreeNode(T value){
			this.count = 1;
			this.value = value;
			this.parent = null;
			this.leftChild = null;
			this.rightChild = null;
		}
		
		public TreeNode getRightChild(){
			return this.rightChild;
		}
		
		public TreeNode getLeftChild(){
			return this.leftChild;
		}
		
		public void setLeftChild(TreeNode left){
			this.leftChild = left;
		}
		
		public void setRightChild(TreeNode right){
			this.rightChild = right;
		}
		
		public void setValue(T value){
			this.value = value;
		}
		
		public int getCount(){
			return this.count;
		}
		
		public void setCount(int count){
			this.count = count;
		}
		public void addElementCount(){
			this.count++;
		}
		public void reduceElementCount(){
			this.count--;
		}
	}
} // end of class BstMultiset
