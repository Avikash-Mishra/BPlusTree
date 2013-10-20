package core;

import StringToInt.InfoNode;
import StringToInt.InternalNode;
import StringToInt.LeafNode;
import StringToInt.Node;
/**
  Implements a B+ tree in which the keys  are Strings (with
  maximum length 60 characters) and the values are integers 
*/

public class BPlusTreeString60toInt {
	private Node root;
	public static final int maxDegree = 32;
    /**
     * Returns the integer associated with the given key,
     * or null if the key is not in the B+ tree.
     */
    public Integer find(String key){
    	if (root == null) {
			return null;
		}
		return Find(key,root);
    }


    private Integer Find(String key, Node node) {
    	if(node instanceof LeafNode){
			for(int i = 0; i <= node.getSize() - 1; i++){
				if(key.compareTo(node.getKey(i)) == 0){
					return node.getValue(i);
				}
			} 
			return null;
		}
		if(node instanceof InternalNode){
			for(int i = 1; i <= node.getSize();i++){
				if(key.compareTo(node.getKey(i)) < 0){
					return Find(key,node.getChild(i-1));
				}
			}
			return Find(key,node.getChild(node.getSize()));
		}
		return null;
	}


	/**
     * Stores the value associated with the key in the B+ tree.
     * If the key is already present, replaces the associated value.
     * If the key is not present, adds the key with the associated value
     * @param value
     * @param key 
     * @return whether pair was successfully added.
     */
    public boolean put(String key, Integer value){
    	if(root == null){
			LeafNode leaf = new LeafNode(key,value);
			root = leaf;
			return true;
		}
		else{
			InfoNode infoNode = Add(key, value, this.root);
			if(infoNode != null){
				InternalNode node = new InternalNode();
				node.size = 1;
				node.children[0] = root;
				node.keys[1] = infoNode.newKey;
				node.children[1] = infoNode.rightChild;
				this.root = node;
				return true;
			}
		}
		
		return false;
    }


    public InfoNode Add(String key, Integer value, Node node){
		if(node instanceof LeafNode){
			if(((LeafNode) node).size < BPlusTreeIntToString60.maxDegree + 1){
				node.add(key, value);
				return null;
			}
			else{
				return SplitLeaf(key,value,node);
			}
		}
		if(node instanceof InternalNode){
			for(int i = 1; i <= node.getSize();i++ ){
				if(key.compareTo(((InternalNode)node).keys[i]) < 0){
					InfoNode tempInfo = Add(key, value, ((InternalNode)node).children[i-1]);
					if(tempInfo == null){
						return null;
					}
					else{
						return dealWithPromote(tempInfo,node);
					}
				}
			}
			InfoNode tempInfo = Add(key, value, ((InternalNode)node).children[node.getSize()]);
			if(tempInfo == null){
				return null;
			}
			else{
				return dealWithPromote(tempInfo,node);
			}
		}
		return null;
		
	}


	private InfoNode dealWithPromote(InfoNode infoNode, Node node) {
		if(infoNode == null){return null;}
		node.add(infoNode.newKey, infoNode.rightChild);
		if(node.getSize() <= maxDegree){return null;}
		InternalNode sibling = new InternalNode();
		int mid = (node.getSize()/2)+1;
		int j = 1;
		int loopTo = ((InternalNode)node).size;
		String promoteKey = ((InternalNode)node).keys[mid];
		for(int i = mid+1;i<=loopTo;i++){
			sibling.keys[j] = node.getKey(i);
			sibling.size++;
			j++;
		}
		j=0;
		for(int i = mid; i<=loopTo;i++){
			sibling.children[j] = node.getChild(i);
			j++;
			node.remove(i);
		}
		return new InfoNode(promoteKey, sibling);
	}


	private InfoNode SplitLeaf(String key, Integer value, Node node) {
		node.add(key, value);
		LeafNode sibling = new LeafNode();
		int mid = (node.getSize()+1)/2;
		int loopTo = node.getSize();
		for(int i = mid; i < loopTo;i++){
			sibling.add(node.getKey(i), node.getValue(i));
		}
		for(int i = mid; i < loopTo;i++){
			node.remove(i);
		}
		sibling.nextLeaf = ((LeafNode) node).nextLeaf;
		((LeafNode) node).nextLeaf = sibling;
		return new InfoNode(sibling.keys[0], sibling);
	}

}
