package StringToInt;


public class InfoNode {
	public String newKey;
	public Node rightChild;
	
	
	public InfoNode() {}
	/**
	 * @param newKey
	 * @param rightChild
	 */
	public InfoNode(String newKey, Node rightChild) {
		super();
		this.newKey = newKey;
		this.rightChild = rightChild;
	}
	/**
	 * @return the newKey
	 */
	public String getNewKey() {
		return newKey;
	}
	/**
	 * @param newKey the newKey to set
	 */
	public void setNewKey(String newKey) {
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
