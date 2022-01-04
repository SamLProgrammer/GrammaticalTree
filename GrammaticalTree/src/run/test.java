package run;


import models.Tree;

public class test {

	//aaaB
	//Baaa
	//aaBaa
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.printHistoryForNode(tree.generateParticularTree(tree.getRootNode(), "aaaab"));
//		System.out.println(replaceNonTerminal("Aaa", 'A', "aA"));
	}
	
//	public static String replaceNonTerminal(String string, char element, String replacer) {
//		String word = "";
//		for (int i = 0; i < string.length(); i++) {
//			if(string.charAt(i) == element) {
//				word = string.substring(0,i) + replacer + string.substring(i + replacer.length(), string.length());;
//			}
//		}
//		return word;
//	}

}
