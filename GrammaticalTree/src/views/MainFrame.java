package views;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import models.GrammarValidator;
import models.Tree;


public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private MenuPanel menuPanel;
	private TreePanel treePanel;
	private JScrollPane treePaneScroller;
	

	public MainFrame(Tree tree, GrammarValidator grammarValidator) {
		initComponents(tree, grammarValidator);
		initProperties();
	}

	private void initComponents(Tree tree, GrammarValidator grammarValidator) {
		menuPanel = new MenuPanel(grammarValidator);
		treePaneScroller = new JScrollPane();
		treePanel = new TreePanel(tree, new ScrollTreePaneObserver(treePaneScroller));
		
		JPanel treeContainerPanel = new JPanel();
		
		treePaneScroller.setViewportView(treePanel);
		treePaneScroller.setPreferredSize(treePanel.getPreferredSize());
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                menuPanel, treePaneScroller);
		add(splitPane);
	}

	private void initProperties() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(width/4, height/2);
		setLocation(width /2 - getWidth()/2, height/6);
		
		setVisible(true);
	}
}
