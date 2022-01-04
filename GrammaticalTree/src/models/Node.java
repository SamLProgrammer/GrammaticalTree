package models;

import java.util.ArrayList;

public class Node {

	private String value;
	private ArrayList<Node> childs;
	private Node previousNode;
	
	public Node(String value) {
		this.value = value;
		childs = new ArrayList<Node>();
	}
	
	public void addChild(Node node) {
		childs.add(node);
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setChilds(ArrayList<Node> childs) {
		this.childs = childs;
	}
	
	public int getChildsCount() {
		return childs.size();
	}

	
	public ArrayList<Node> getChilds() {
		return childs;
	}

	public void setPreviousNode(Node node) {
		previousNode = node;
	}

	public Node getPreviousNode() {
		return previousNode;
	}
}
