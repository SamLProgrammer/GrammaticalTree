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

public class SigmaCapturePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField terminalField;
	private SigmaPanelObserver observer;
	private int preferredWidth;
	private String temp_symbol;
	private int preferredHeight;
	private Font textFont;

	public SigmaCapturePanel(int index, SigmaPanelObserver observer, GrammarValidator grammarValidator) {
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
		terminalField = new JTextField();
		terminalField.setHorizontalAlignment(SwingConstants.CENTER);
		terminalField.setPreferredSize(new Dimension(getWidth(), getHeight()));
		terminalField.setFont(textFont);
		terminalField.requestFocus();
		terminalField.addKeyListener(new KeyListener() {

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
					if (terminalField.getText().length() > 0) {
						myInstance().setPreferredSize(new Dimension((int) calculateStringWidth(), getHeight()));
						if (grammarValidator.addingTerminalValidation(terminalField.getText())) {
							if (!terminalField.getText().equals(temp_symbol)) {
								grammarValidator.removeVariableSymbol(temp_symbol);
								temp_symbol = terminalField.getText();
								observer.addDerivationColumn();
								terminalField.setBackground(Color.decode("#99e36c"));
							}
						} else {
							terminalField.setBackground(Color.decode("#e67979"));
						}
					} else {
						if (temp_symbol != null) {
							grammarValidator.removeSigmaSymbol(temp_symbol);
							temp_symbol = "";
						}
						if(observer.fieldsSize() > 2) {
						observer.removeCapturePanel(myInstance());
						} else {
							terminalField.setBackground(Color.decode("#ffffff"));
						}
					}
				}
			}
		});
		add(terminalField);
	}

	private float calculateStringWidth() {
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setFont(textFont);
		return g2d.getFontMetrics().stringWidth(terminalField.getText()) + g2d.getFontMetrics().stringWidth("a");
	}

	private SigmaCapturePanel myInstance() {
		return this;
	}

	public String getTextFieldValue() {
		return terminalField.getText();
	}
}
