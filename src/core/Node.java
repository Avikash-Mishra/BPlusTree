package core;

public interface Node {
	public int getSize();
	public int getKey(int index);
	public String getValue(int i);
	public Node getChild(int i);
	public void add(int key, String value);
	public void add(int key, Node child);
	public void remove(int i);
}
