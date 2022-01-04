package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.GrammarValidator;

public class VariablesCapturePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField noTerminalField;
	private VariablesPanelObserver observer;
	private String temp_symbol;
	private int preferredWidth;
	private int preferredHeight;
	private Font textFont;

	public VariablesCapturePanel(int index, VariablesPanelObserver observer, GrammarValidator grammarValidator) {
		this.observer = observer;
		initProperties();
		initComponents(grammarValidator);
	}

	private void initProperties() {
		setLayout(new BorderLayout());
		preferredWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		preferredHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setPreferredSize(new Dimension(preferredWidth / 40, preferredHeight / 50));
		textFont = new Font("Arial", Font.BOLD, (preferredWidth + preferredHeight) / 50);
	}

	private void initComponents(GrammarValidator grammarValidator) {
		temp_symbol = "";
		noTerminalField = new JTextField();
		noTerminalField.setHorizontalAlignment(SwingConstants.CENTER);
		noTerminalField.setPreferredSize(new Dimension(getWidth(), getHeight()));
		noTerminalField.setFont(textFont);
		noTerminalField.requestFocus();
		noTerminalField.addKeyListener(new KeyListener() {

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
					if (noTerminalField.getText().length() > 0) {
						myInstance().setPreferredSize(new Dimension((int) calculateStringWidth(), getHeight()));
						if (grammarValidator.addingNonTerminalValidation(noTerminalField.getText())) {
							if (!noTerminalField.getText().equals(temp_symbol)) {
								grammarValidator.removeVariableSymbol(temp_symbol);
								temp_symbol = noTerminalField.getText();
								observer.addDerivationColumn();
								noTerminalField.setBackground(Color.decode("#99e36c"));
							}
						} else {
							noTerminalField.setBackground(Color.decode("#e67979"));
						}
					} else {
						if (temp_symbol != null) {
							grammarValidator.removeVariableSymbol(temp_symbol);
							temp_symbol = "";
						}
						if(observer.fieldsSize() > 2) {
						observer.removeCapturePanel(myInstance());
						} else {
							noTerminalField.setBackground(Color.decode("#ffffff"));
						}
					}
				}
			}
		});
		add(noTerminalField);
	}

	private float calculateStringWidth() {
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setFont(textFont);
		return g2d.getFontMetrics().stringWidth(noTerminalField.getText()) + g2d.getFontMetrics().stringWidth("a");
	}

	private VariablesCapturePanel myInstance() {
		return this;
	}

	public String getTextFieldValue() {
		return noTerminalField.getText();
	}
}
