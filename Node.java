
public class Node {
	public char value;
	public boolean isEnd;
	public Node rightSib;
	public Node child;

	public Node(char value) {
		this.value = value;
		this.isEnd = false;
		this.child = null;
		this.rightSib = null;
	}

	public Node(char value, boolean isEnd, Node child, Node rightSib) {
		this.value = value;
		this.isEnd = isEnd;
		this.child = child;
		this.rightSib = rightSib;
	}
}
