package Tree.Structs;

import java.util.ArrayList;

public class TreeNode<E> {

	E data;
	ArrayList<TreeNode<E>> children;
	
	public TreeNode(E theItem) {

		this.data = theItem;
		this.children = new ArrayList<TreeNode<E>>();

	}

	// Accessors/Mutators 

	public E getData() {

		return this.data;

	}

	public ArrayList<TreeNode<E>> getChildren() {

		return this.children;

	}

	// Methods

	public void addChild(TreeNode<E> theItem) {

		this.children.add(theItem);

	}
	
	public void removeChild(E theItem) {

		// Must find which child to remove since we are given an item and not an inst of TreeNode. streams useful here, again

		TreeNode<E> childToRemove = this.children.stream().filter(child -> child.getData().equals(theItem)).findFirst().orElse(null);

		if (childToRemove == null) {

			return; // Silently fail, but shouldn't happen

		}

		// Now we can get the child's children and add them to the parent

		ArrayList<TreeNode<E>> childChildren = childToRemove.getChildren();

		childChildren.forEach(foresakenChild -> this.addChild(foresakenChild));

		// finally, we will remove the child from this node 

		this.children.remove(childToRemove);

	}
	
	// Misc

	@Override
	public String toString() {

		return String.format("| %s |", this.data.toString());

	}
		
}