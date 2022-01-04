package views;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GetDerivationsPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPanel derivationsPanel;
	private ArrayList<DerivationCapturePanel> derivationsRows;
	private JButton submitDerivationsButton;
	
	public GetDerivationsPanel() {
		initProperties();
		initComponents();
	}

	private void initProperties() {
		setLayout(new BorderLayout());
	}
	
	private void initDerivationsList() {
		derivationsRows = new ArrayList<DerivationCapturePanel>();
		derivationsRows.add(new DerivationCapturePanel(derivationsRows.size(), new GetDerivationsPanelObserver(this)));
	}

	private void initComponents() {
		initDerivationsList();
		
		derivationsPanel = new JPanel();
		derivationsPanel.setLayout(new BoxLayout(derivationsPanel, BoxLayout.Y_AXIS));
		
		JScrollPane derivationsScroller = new JScrollPane();
		derivationsScroller.setViewportView(derivationsPanel);
		
		submitDerivationsButton = new JButton("Submit");
		
		renderDerivationsRows();
		
		add(derivationsScroller);
		add(submitDerivationsButton, BorderLayout.SOUTH);
	}
	
	private void renderDerivationsRows() {
		for (DerivationCapturePanel derivationCapturePanel : derivationsRows) {
			derivationsPanel.add(derivationCapturePanel);
		}
	}
	
	public void addNewDerivationRow() {
		derivationsRows.add(new DerivationCapturePanel(derivationsRows.size(), new GetDerivationsPanelObserver(this)));
		renderDerivationsRows();
		validate();
	}
}
