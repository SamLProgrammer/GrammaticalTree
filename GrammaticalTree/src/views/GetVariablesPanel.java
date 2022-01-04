package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.GrammarValidator;

public class GetVariablesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel noTerminalsPanel;
	private ArrayList<VariablesCapturePanel> noTerminalFields;
	private JButton submitDerivationsButton;
	private JComboBox<String> initialSymbolOptions;
	private JLabel iconLabel;
	private GrammarValidator grammarValidator;
	private int preferredWidth;
	private int preferredHeight;
	
	public GetVariablesPanel(GrammarValidator grammarValidator) {
		this.grammarValidator = grammarValidator;
		initProperties();
		initComponents(grammarValidator);
	}

	private void initProperties() {
		setLayout(new BorderLayout());
		preferredWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		preferredHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setPreferredSize(new Dimension(getWidth(), preferredHeight / 7));
	}

	private void initDerivationsList(GrammarValidator grammarValidator) {
		noTerminalFields = new ArrayList<VariablesCapturePanel>();
		noTerminalFields.add(new VariablesCapturePanel(noTerminalFields.size(), new VariablesPanelObserver(this), grammarValidator));
	}
	
	private ImageIcon resize(ImageIcon image,Dimension dimension) {
        java.awt.Image auxImage = image.getImage();
        ImageIcon newImage = new ImageIcon(auxImage.getScaledInstance((int) dimension.width, (int) dimension.height,java.awt.Image.SCALE_REPLICATE));
        return newImage;
    }

	private void initComponents(GrammarValidator grammarValidator) {
		
		iconLabel = new JLabel(resize(new ImageIcon(getClass().getResource("/img/sigmaIcon.png")), new Dimension(preferredWidth/50, preferredHeight/50)));
		initDerivationsList(grammarValidator);

		initialSymbolOptions = new JComboBox<String>();

		noTerminalsPanel = new JPanel();
		noTerminalsPanel.setLayout(new BoxLayout(noTerminalsPanel, BoxLayout.X_AXIS));
		noTerminalsPanel.add(iconLabel);
		noTerminalsPanel.add(initialSymbolOptions);

		JScrollPane noTerminalsScroller = new JScrollPane();
		noTerminalsScroller.setViewportView(noTerminalsPanel);

		submitDerivationsButton = new JButton("Submit");

		renderDerivationsColumns();
		
		add(noTerminalsScroller);
		add(submitDerivationsButton, BorderLayout.SOUTH);
	}

	private void renderDerivationsColumns() {
		for (VariablesCapturePanel noTerminalsCapturePanel : noTerminalFields) {
			noTerminalsPanel.add(noTerminalsCapturePanel);
		}
	}

	public void addNewNoTerminalColumn() {
		noTerminalFields
				.add(new VariablesCapturePanel(noTerminalFields.size(), new VariablesPanelObserver(this), grammarValidator));
		renderDerivationsColumns();
		updateInitialSymbolBox();
		validate();
	}
	
	private void flushSymbolsBox() {
		initialSymbolOptions.removeAllItems();
	}
	
	public void updateInitialSymbolBox() {
		flushSymbolsBox();
		for (VariablesCapturePanel noTerminalCapturePanel : noTerminalFields) {
			String string = noTerminalCapturePanel.getTextFieldValue();
			if (string.length() > 0) {
				initialSymbolOptions.addItem(string);
			}
		}
	}

	public void removeCapturePanel(VariablesCapturePanel myInstance) {
		if(noTerminalFields.size() > 1) {
			noTerminalFields.remove(myInstance);
			noTerminalsPanel.remove(myInstance);
		}
		updateInitialSymbolBox();
		validate();
	}

	public int getFieldsListSize() {
		return noTerminalFields.size();
	}
}
