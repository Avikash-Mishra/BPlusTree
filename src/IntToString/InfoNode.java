package IntToString;


public class InfoNode {
	public int newKey;
	public Node rightChild;
	
	
	public InfoNode() {}
	/**
	 * @param newKey
	 * @param rightChild
	 */
	public InfoNode(int newKey, Node rightChild) {
		super();
		this.newKey = newKey;
		this.rightChild = rightChild;
	}
	/**
	 * @return the newKey
	 */
	public int getNewKey() {
		return newKey;
	}
	/**
	 * @param newKey the newKey to set
	 */
	public void setNewKey(int newKey) {
		this.newKey = newKey;
	}
	/**
	 * @return the rightChild
	 */
	public Node getRightChild() {
		return rightChild;
	}
	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	
}
