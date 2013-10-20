package StringToInt;

import core.BPlusTreeString60toInt;


public class InternalNode implements Node {
	public String keys[] = new String[BPlusTreeString60toInt.maxDegree+3];
	public int size;
	public Node children[] = new Node[BPlusTreeString60toInt.maxDegree+3];

	public InternalNode(String key, Node child) {
		this.size = 0;
		this.add(key, child);
	}

	public InternalNode() {
		this.size = 0;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public String getKey(int index) {
		return keys[index];
	}

	@Override
	public Node getChild(int i) {
		return children[i];
	}

	@Override
	public void add(String key, Node child) {
		if (size == 0) {
			this.keys[size + 1] = key;
			this.children[size+1] = child;
			this.size++;
			return;
		}
		else if(key.compareTo(this.keys[size]) > 0){
			this.keys[size+1] = key;
			this.children[size+1] = child;
			this.size++;
			return;
		}
		else{
			for(int i = 1; i <=this.size;i++){
				if(key.compareTo(this.keys[size]) < 0){
					for(int j = size+1; j>i;j--){
						this.keys[j] = this.keys[j-1];
						this.children[j] = this.children[j-1];
					}
					this.keys[i] = key;
					this.children[i] = child;
					this.size++;
					break;
				}
			}
		}

	}

	@Override
	public void add(String key, Integer value) {
		// should never happen
	}

	@Override
	public Integer getValue(int i) {
		// should never happen
		return (Integer) null;
	}

	@Override
	public void remove(int i) {
		for(int j = i; j<=this.size;j++){
			this.keys[j] = this.keys[j+1];
		}
		this.size--;

	}
	
	@Override
	public String toString(){
		String toReturn = "INTERNAL:";
		for(int i = 0; i<=this.size;i++){
			toReturn += this.keys[i];
			toReturn+= "->";
			toReturn += this.children[i];
			toReturn+= ",";
			
		}
		return toReturn;
	}

}
