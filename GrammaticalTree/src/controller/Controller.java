package controller;

import models.GrammarValidator;
import models.Tree;
import views.MainFrame;

public class Controller {

	private MainFrame mainFrame;
	private Tree tree;
	private GrammarValidator grammarValidator;
	
	public Controller() {
		initComponents();
	}

	private void initComponents() {
		tree = new Tree();
		grammarValidator = new GrammarValidator(mainFrame);
		mainFrame = new MainFrame(tree, grammarValidator);
	}
}
