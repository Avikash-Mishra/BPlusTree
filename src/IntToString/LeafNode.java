package IntToString;

import core.Node;

public class LeafNode implements Node {
	public int keys[] = new int[10];
	public String values[] = new String[10];
	public int size;
	public LeafNode nextLeaf;

	public LeafNode() {
		this.size = 0;
	}

	/**
	 * @param keys
	 * @param values
	 */
	public LeafNode(int key, String value) {
		super();
		this.size = 0;
		this.add(key, value);
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public int getKey(int index) {
		return keys[index];
	}

	@Override
	public String getValue(int i) {
		return values[i];
	}

	@Override
	public void add(int key, String value) {
		if (this.size == 0) {
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key > this.keys[size - 1]){
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key < this.keys[0]){
			for(int i = this.size-1;i>=0;i--){
				this.keys[i+1] = this.keys[i];
				this.values[i+1] = this.values[i];
			}
			this.keys[0] = key;
			this.values[0] = value;
			this.size++;
			return;
		}
		for(int i = 0; i <= this.size-1;i++){
			if(key > this.keys[i] && key < this.keys[i+1]){
				for(int j = size; j >i+1;j--){
					this.keys[j] = this.keys[j-1];
					this.values[j] = this.values[j-1];
				}
				this.keys[i+1] = key;
				this.values[i+1] = value;
				this.size++;
				break;
			}
		}
		
			
		
		
	}

	@Override
	public void remove(int i) {
		for(int j = i; j<this.size-1;j++){
			this.keys[j] = this.keys[j+1];
			this.values[j] = this.values[j+1];
		}
		this.size--;
		
	}
	@Override
	public void add(int key, Node child) {
		// should never happen
	}

	@Override
	public Node getChild(int i) {
		// should never happen
		return null;
	}


}
