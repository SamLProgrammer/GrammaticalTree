package views;

public class NodeObject {
	
	float x;
	float y;
	float size;
	
	
	public NodeObject(float x, float y, float size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
