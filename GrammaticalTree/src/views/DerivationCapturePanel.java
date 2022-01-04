package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DerivationCapturePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> leftSideProductionTextBox;
	private JTextField arrowArea;
	private JTextField rightSideProductionTextField;
	private GetDerivationsPanelObserver observer;
	private ArrayList<String> nonTerminalsList;
	private int preferredWidth;
	private int preferredHeight;

	public DerivationCapturePanel(int index, GetDerivationsPanelObserver observer) {
		this.observer = observer;
		initProperties();
		initComponents();
	}

	private void initProperties() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		preferredWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		preferredHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setPreferredSize(new Dimension(preferredWidth/40, preferredHeight/20));
	}

	private void initComponents() {
		leftSideProductionTextBox = new JComboBox<String>();
		
		rightSideProductionTextField = new JTextField("B");
		leftSideProductionTextBox.setFont(new Font("Arial", Font.BOLD, (preferredWidth + preferredHeight)/50));
		rightSideProductionTextField.setFont(new Font("Arial", Font.BOLD, (preferredWidth + preferredHeight)/50));
		arrowArea = new JTextField("=>");
		arrowArea.setHorizontalAlignment(SwingConstants.CENTER);
		rightSideProductionTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (getNonTerminalSelected().length() > 0
							&& rightSideProductionTextField.getText().length() > 0) {
						observer.addDerivationRow();
					}
				}
			}
		});

		add(leftSideProductionTextBox);
		add(arrowArea);
		add(rightSideProductionTextField);
	}
	
	private String getNonTerminalSelected() {
		return leftSideProductionTextBox.getSelectedItem().toString();
	}
	
	private void flushNonTerminalsBox() {
		leftSideProductionTextBox.removeAllItems();
	}
	
	public void updateNonTermianalsBox() {
		flushNonTerminalsBox();
		for (String value : nonTerminalsList) {
			if (value.length() > 0) {
				leftSideProductionTextBox.addItem(value);
			}
		}
		validate();
	}
}
