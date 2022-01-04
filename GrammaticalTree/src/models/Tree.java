package models;

import java.util.ArrayList;

public class Tree {

	private ArrayList<Production> productions;
	private ArrayList<Character> nonTerminals;
	private Node rootNode;
	private int levels = 6;
	
	public Tree(String string) {
		this.rootNode = new Node(string);
	}
	
	public Tree() {
		initProductions();
		rootNode = new Node("aA");
		generateTree(rootNode, levels, 0);
	}

	private void initProductions() {
		ArrayList<String> pr0 = new ArrayList<String>();
		ArrayList<String> pr1 = new ArrayList<String>();

		pr0.add("aA");
		pr1.add("aA");
		pr1.add("bA");
		pr1.add("b");
		productions = new ArrayList<Production>();
		productions.add(new Production('S', pr0));
		productions.add(new Production('A', pr1));
		nonTerminals();
	}

	private void nonTerminals() {
		nonTerminals = new ArrayList<Character>();
		for (Production production : productions) {
			nonTerminals.add(production.getNotTerminal());
		}
	}
	
	public void printHistoryForNode(Node node) {
		if(node == null) {
			System.out.println("jajaja");
		}
		Node auxNode = node;
		while(auxNode != null) {
			System.out.println(auxNode.getValue());
			auxNode = auxNode.getPreviousNode();
		}
	}
	
	public Node generateParticularTree(Node node, String word) {
		Node auxNode = null;
		for(int i = 0; i < node.getChildsCount(); i++) {
			node.getChilds().get(i).setPreviousNode(node);
			if(node.getChilds().get(i).getValue().equals(word)) {
				auxNode = node.getChilds().get(i);
			} else {
				auxNode = (generateParticularTree(node.getChilds().get(i), word) != null) ? generateParticularTree(node.getChilds().get(i), word) : auxNode;
			}
		}
		return auxNode;
	}

	private boolean isNonTerminal(char string) {
		boolean flag = false;
		for (char nonTerminal : nonTerminals) {
			if(nonTerminal == string) {
				flag = true;
			}
		}
		return flag;
	}
	
	private ArrayList<String> getProductions(char nonTerminal) {
		ArrayList<String> auxProductions = new ArrayList<String>();
		for (Production production : productions) {
			if(production.getNotTerminal() == nonTerminal) {
				auxProductions = production.getProducts();
			}
		}
		return auxProductions;
	}
	
	private void generateTree(Node node, int limit, int current) {
		if(current < limit) {
			String word = node.getValue();
			for (int i = 0; i < word.length(); i++) {
				if(isNonTerminal(word.charAt(i))) {
					String auxWord = word;
					ArrayList<String> productions = getProductions(word.charAt(i));
					ArrayList<Node> childs = new ArrayList<Node>();
					for (String string : productions) {
						String rWord = replaceNonTerminal(auxWord, word.charAt(i), string);
						Node auxNode = new Node(rWord);
						childs.add(auxNode);
						generateTree(auxNode, limit, current+1);
					}
					node.setChilds(childs);
				}
			}
		}
	}

	private void generateLevel(String word, int limit, int current) {
		if (current < limit) {
			for (int i = 0; i < word.length(); i++) {
				if(isNonTerminal(word.charAt(i))) {
					String auxWord = word;
					ArrayList<String> productions = getProductions(word.charAt(i));
					for (String string : productions) {
						String rWord = replaceNonTerminal(auxWord, word.charAt(i), string);
						System.out.print(rWord + " ");
						generateLevel(rWord, limit, current + 1);
					}
					
					System.out.println();
					System.out.println("============");
				}
			}
		}
	}
	
	private String replaceNonTerminal(String string, char element, String replacer) {
	    return string.replace(Character.toString(element), replacer);
	}
	
	public int highestNodesAmount() {
		int counter = 0;
		int highestCounter = 0;
		for (Production production : productions) {
			for(int i = 0; i < production.getProducts().size(); i++) {
				counter++;
			}
			highestCounter = (counter > highestCounter) ? counter : highestCounter;
			counter = 0;
		}
		return highestCounter;
	}
	
	public void generateTest() {
		initProductions();
		generateLevel("aA", levels, 0);
		generateTree(new Node("aA"), levels, 0);
	}
	
	public Node getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	public void setLevels(int levels) {
		this.levels = levels;
	}
	
	public int getLevels() {
		return levels;
	}
	
	public ArrayList<Character> getNonTerminals() {
		return nonTerminals;
	}
}
