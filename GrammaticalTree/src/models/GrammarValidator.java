package models;

import java.util.ArrayList;

import views.MainFrame;

public class GrammarValidator {
	private ArrayList<Production> productions;
	private ArrayList<String> nonTerminals;
	private ArrayList<String> terminals;
	private MainFrame mainFrame;
	
	public GrammarValidator(MainFrame mainFrame) {
		initComponents(mainFrame);
	}

	public boolean addingNonTerminalValidation(String symbol) {
		boolean flag = false;
		for (String nonTerminal: nonTerminals) {
			if(nonTerminal.equals(symbol)) {
				flag = true;
				break;
			}
		}
		if(flag == false) {
			nonTerminals.add(symbol);
		}
		return !flag;
	}
	
	public boolean addingTerminalValidation(String symbol) {
		boolean flag = false;
		for (String nonTerminal: terminals) {
			if(nonTerminal.equals(symbol)) {
				flag = true;
				break;
			}
		}
		if(flag == false ) {
			terminals.add(symbol);
		}
		return !flag;
	}
	
	private void initComponents(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		productions = new ArrayList<Production>();
		nonTerminals = new ArrayList<String>();
		terminals = new ArrayList<String>();
	}
	
	public ArrayList<Production> getProductions() {
		return productions;
	}
	
	public ArrayList<String> getNonTerminals() {
		return nonTerminals;
	}
	
	public ArrayList<String> getTerminals() {
		return terminals;
	}

	public void removeSigmaSymbol(String text) {
		for(int i = 0; i < terminals.size(); i++) {
			if(text.equals(terminals.get(i))) {
				terminals.remove(i);
				break;
			}
		}
		System.out.println("sigma size: " + terminals.size());
	}

	public void removeVariableSymbol(String symbolValue) {
		for(int i = 0; i < nonTerminals.size(); i++) {
			if(symbolValue.equals(nonTerminals.get(i))) {
				nonTerminals.remove(i);
				break;
			}
		}
		System.out.println("sigma size: " + nonTerminals.size());
	}
}
