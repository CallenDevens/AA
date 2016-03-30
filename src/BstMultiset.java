import java.io.PrintStream;
import java.util.*;

import javax.xml.soap.Node;

public class BstMultiset<T> extends Multiset<T>
{
	private TreeNode<T> root;
	private int size;
	public BstMultiset() {
		// Implement me!
		this.root = null;
		this.size = 0;
	} // end of BstMultiset()

	public void add(T item) {
		// Implement me!
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
				TreeNode newNode = new TreeNode(item);
				node.setLeftChild(newNode);
				newNode.setParent(node);
			}
			else{
				insert(item, node.getLeftChild());				
			}

		}
		else if(((Comparable)node.getValue()).compareTo((Comparable)item)<0){
			if(node.getRightChild() == null){
				TreeNode newNode = new TreeNode(item);
				node.setRightChild(newNode);
				newNode.setParent(node);
			}
			else{
				insert(item, node.getRightChild());				
			}
		}
		else{
			node.addElementCount();
		}
		
		size++;
	}

	public int search(T item) {
		// Implement me!

		// default return, please override when you implement this method
		return search(item, this.root);
	} // end of add()
	
	public int search(T item, TreeNode node){
		if(node == null){
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
        }
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
        }
    }
    
    public void deleteNode(TreeNode node){
    	System.out.println("delete Node");
        /**
        **①Leaf node，delete directly
        **②have got an child. 
        **③two children nodes
        **/
        if(node.getLeftChild() == null && node.getRightChild() == null){
        	
        	System.out.println("directly!");
        	TreeNode parent = node.getParent();
        	if(parent.getLeftChild() == node){
        		parent.setLeftChild(null);
        	}
        	else{
        		parent.setRightChild(null);
        	}
        	node.setParent(null);
            node = null;
            
        }else if(node.getLeftChild()!=null && node.getRightChild() == null){
        	
        	System.out.println("Left child not null!");
        	
        	TreeNode parent = node.getParent();
        	TreeNode leftChild = node.getLeftChild();
        	
        	leftChild.setParent(parent);
        	
        	if(parent.getLeftChild() == node){
        		parent.setLeftChild(leftChild);
        	}
        	else{
        		parent.setRightChild(leftChild);
        	}
        	
        	node.setParent(null);
        	node.setLeftChild(null);
        	node = null;
            
        }else if(node.getLeftChild() == null && node.getRightChild() != null){
        	System.out.println("Right child not null!");
        	
        	TreeNode parent = node.getParent();
        	TreeNode rightChild = node.getRightChild();
        	
        	rightChild.setParent(parent);
        	
        	if(parent.getLeftChild() == node){
        		parent.setLeftChild(rightChild);
        	}
        	else{
        		parent.setRightChild(rightChild);
        	}
        	
        	node.setParent(null);
        	node.setRightChild(null);
        	node = null;

        }else{
        	System.out.println("two children nodes");
            //two children nodes
            
        	TreeNode newRoot = node.getRightChild();
            
            while (newRoot.getLeftChild()!= null){
            	newRoot = newRoot.getLeftChild();//find the minimum of right sub tree
            }
                
            System.out.println("new root:" + newRoot.getValue());
            
            node.setValue(newRoot.value);
            node.setCount(newRoot.getCount());

            newRoot.getParent().setLeftChild(null);
            newRoot.setParent(null);
            newRoot = null;
        }
    }

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
			// TODO Auto-generated method stub
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
