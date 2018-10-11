package ai.graph;

public class NumberNode implements Node{
	private int number;
	private int x;
	private int y;
	
	public NumberNode(int number, int x, int y) {
		this.number = number;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return this.number;
	}
}
