package views;

import java.awt.Dimension;

import javax.swing.JScrollPane;

public class ScrollTreePaneObserver {

	private JScrollPane treeScrollPane;
	
	public ScrollTreePaneObserver(JScrollPane treeScrollPane) {
		this.treeScrollPane = treeScrollPane;
	}

	public void updateScrollPane(int xMaxScaled, int yMaxScaled) {
		treeScrollPane.setPreferredSize(new Dimension(xMaxScaled, yMaxScaled));
		treeScrollPane.revalidate();
	}
}
