package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import models.GrammarValidator;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GetDerivationsPanel derivationsPanel;
	private GetVariablesPanel noTerminalsPanel;
	private GetSigmaPanel terminalsPanel;

	public MenuPanel(GrammarValidator grammarValidator) {
		initComponents(grammarValidator);
		initProperties();
	}

	private void initProperties() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		int preferredWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int preferredHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setPreferredSize(new Dimension(preferredWidth / 8, preferredHeight));
	}

	private void initComponents(GrammarValidator grammarValidator) {
		noTerminalsPanel = new GetVariablesPanel(grammarValidator);
		terminalsPanel = new GetSigmaPanel(grammarValidator);
		derivationsPanel = new GetDerivationsPanel();

		JPanel splitContainer1 = new JPanel();
		splitContainer1.setLayout(new BorderLayout());
		
		JPanel splitContainer0 = new JPanel();
		splitContainer0.setLayout(new BorderLayout());
		
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, terminalsPanel, derivationsPanel);

		splitContainer1.add(splitPane1);
		
		JSplitPane splitPane0 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, noTerminalsPanel, splitPane1);

		splitContainer0.add(splitPane0);
		add(splitContainer0);
	}
}
