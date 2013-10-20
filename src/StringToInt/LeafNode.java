package StringToInt;

import core.BPlusTreeString60toInt;

public class LeafNode implements Node {
	public String[] keys = new String[BPlusTreeString60toInt.maxDegree+3];
	public Integer values[] = new Integer[BPlusTreeString60toInt.maxDegree+3];
	public int size;
	public LeafNode nextLeaf;

	public LeafNode() {
		this.size = 0;
	}

	/**
	 * @param keys
	 * @param values
	 */
	public LeafNode(String key, int value) {
		super();
		this.size = 0;
		this.add(key, value);
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
	public Integer getValue(int i) {
		return values[i];
	}

	@Override
	public void add(String key, Integer value) {
		if (this.size == 0) {
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key.compareTo(this.keys[size-1]) > 0){
			this.keys[size] = key;
			this.values[size] = value;
			this.size++;
			return;
		}
		else if(key.compareTo(this.keys[0]) < 0){
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
			if(key.compareTo(this.keys[i]) > 0 && key.compareTo(this.keys[i+1]) < 0){
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
	public void add(String key, Node child) {
		// should never happen
	}

	@Override
	public Node getChild(int i) {
		// should never happen
		return null;
	}
	
	@Override
	public String toString(){
		String toReturn = "";
		for(int i = 0; i<this.size;i++){
			toReturn += this.keys[i];
			toReturn+= "->";
			toReturn += this.values[i];
			toReturn+= "\n";
			
		}
		if(this.nextLeaf != null){
			toReturn+= this.nextLeaf.toString();
		}
		return toReturn;
	}



}
