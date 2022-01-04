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

public class GetSigmaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel terminalsPanel;
	private ArrayList<SigmaCapturePanel> terminalFields;
	private JButton submitDerivationsButton;
	private JComboBox<String> initialSymbolOptions;
	private JLabel iconLabel;
	private GrammarValidator grammarValidator;
	private int preferredWidth;
	private int preferredHeight;
	
	public GetSigmaPanel(GrammarValidator grammarValidator) {
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
		terminalFields = new ArrayList<SigmaCapturePanel>();
		terminalFields.add(new SigmaCapturePanel(terminalFields.size(), new SigmaPanelObserver(this), grammarValidator));
	}
	
	private ImageIcon resize(ImageIcon image,Dimension dimension) {
        java.awt.Image auxImage = image.getImage();
        ImageIcon newImage = new ImageIcon(auxImage.getScaledInstance((int) dimension.width, (int) dimension.height,java.awt.Image.SCALE_REPLICATE));
        return newImage;
    }

	private void initComponents(GrammarValidator grammarValidator) {
		iconLabel = new JLabel(resize(new ImageIcon(getClass().getResource("/img/ni.png")), new Dimension(preferredWidth/50, preferredHeight/50)));
		initDerivationsList(grammarValidator);

		initialSymbolOptions = new JComboBox<String>();

		terminalsPanel = new JPanel();
		terminalsPanel.setLayout(new BoxLayout(terminalsPanel, BoxLayout.X_AXIS));
		terminalsPanel.add(iconLabel);
		terminalsPanel.add(initialSymbolOptions);

		JScrollPane noTerminalsScroller = new JScrollPane();
		noTerminalsScroller.setViewportView(terminalsPanel);

		submitDerivationsButton = new JButton("Submit");

		renderDerivationsColumns();
		
		add(noTerminalsScroller);
		add(submitDerivationsButton, BorderLayout.SOUTH);
	}

	private void renderDerivationsColumns() {
		for (SigmaCapturePanel noTerminalsCapturePanel : terminalFields) {
			terminalsPanel.add(noTerminalsCapturePanel);
		}
	}

	public void addNewNoTerminalColumn() {
		terminalFields
				.add(new SigmaCapturePanel(terminalFields.size(), new SigmaPanelObserver(this), grammarValidator));
		renderDerivationsColumns();
		updateInitialSymbolBox();
		validate();
	}
	
	private void flushSymbolsBox() {
		initialSymbolOptions.removeAllItems();
	}
	
	public void updateInitialSymbolBox() {
		flushSymbolsBox();
		for (SigmaCapturePanel noTerminalCapturePanel : terminalFields) {
			String string = noTerminalCapturePanel.getTextFieldValue();
			if (string.length() > 0) {
				initialSymbolOptions.addItem(string);
			}
		}
	}

	public void removeCapturePanel(SigmaCapturePanel myInstance) {
		if(terminalFields.size() > 1) {
			terminalFields.remove(myInstance);
			terminalsPanel.remove(myInstance);
		}
		updateInitialSymbolBox();
		validate();
	}

	public int getFieldsListSize() {
		return terminalFields.size();
	}
}
