package core;
import IntToString.*;

import java.rmi.dgc.Lease;

import IntToString.InfoNode;
import IntToString.InternalNode;
import IntToString.LeafNode;


/**
 * Implements a B+ tree in which the keys are integers and the values are
 * Strings (with maximum length 60 characters)
 */

public class BPlusTreeIntToString60 {

	private Node root;
	public static final int maxDegree = 2;

	/**
	 * Returns the String associated with the given key, or null if the key is
	 * not in the B+ tree.
	 */
	public String Find(int key) {
		if (root == null) {
			return null;
		}
		return Find(key,root);
	}

	public String Find(int key, Node node) {
		if(node instanceof LeafNode){
			for(int i = 0; i <= node.getSize() - 1; i++){
				if(key==node.getKey(i)){
					return node.getValue(i);
				}
			} 
			return null;
		}
		if(node instanceof InternalNode){
			for(int i = 1; i <= node.getSize();i++){
				if(key < node.getKey(i)){
					return Find(key,node.getChild(i-1));
				}
			}
			return Find(key,node.getChild(node.getSize()));
		}
		return null;
	}

	/**
	 * Stores the value associated with the key in the B+ tree. If the key is
	 * already present, replaces the associated value. If the key is not
	 * present, adds the key with the associated value
	 * 
	 * @param key
	 * @param value
	 * @return whether pair was successfully added.
	 */
	public boolean put(int key, String value) {
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
	public InfoNode Add(int key, String value, Node node){
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
				if(key < ((InternalNode)node).keys[i]){
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

	public InfoNode dealWithPromote(InfoNode infoNode, Node node) {
		if(infoNode == null){return null;}
		node.add(infoNode.newKey, infoNode.rightChild);
		if(node.getSize() <= maxDegree){return null;}
		InternalNode sibling = new InternalNode();
		int mid = (node.getSize()/2)+1;
		int j = 1;
		int loopTo = ((InternalNode)node).size;
		int promoteKey = ((InternalNode)node).keys[mid];
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

	public InfoNode SplitLeaf(int key, String value, Node node) {
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
	
	@Override
	public String toString(){
		return root.toString();
	}

}
