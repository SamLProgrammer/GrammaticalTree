package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import models.Node;
import models.Tree;

public class TreePanel extends JPanel implements MouseWheelListener{

	private static final long serialVersionUID = 1L;
	private Tree tree;
	private int maxTreeProductionsNumber;
	private int screenWidth;
	private int screenHeight;
	private float NODE_SIZE;
	private float zoom = 1;
	private int VERTICAL_SPACE;
	private double translateX;
	private double translateY;
	private double wheelX;
	private double wheelY;
	int xMax = 0;
	int yMax = 0;
	int xMaxScaled = 0;
	int yMaxScaled = 0;
	private CopyOnWriteArrayList<NodeObject> nodeImagesList;
	private ScrollTreePaneObserver scrollerObserver;

	public TreePanel(Tree tree, ScrollTreePaneObserver scrollerObserver) {
		this.scrollerObserver = scrollerObserver;
		initBasics(tree);
		setFocusable(true);
		addKeyListener(buildKeyListener());
		addMouseWheelListener(this);
		this.tree = tree;
	}

	private void initBasics(Tree tree) {
		nodeImagesList = new CopyOnWriteArrayList<NodeObject>();
		maxTreeProductionsNumber = tree.highestNodesAmount();
		screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		NODE_SIZE = (screenWidth + screenHeight)/50;
		wheelX = getWidth()/2;
		wheelY = getHeight()/2;
		VERTICAL_SPACE = (int)NODE_SIZE;
	}
	
	private void flushImageNodeList() {
		nodeImagesList.clear();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		flushImageNodeList();
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(zoom,zoom);
		paintNodes(g2, tree.getRootNode(), getWidth() / 2, getHeight() / 70, 0, getWidth(), 1, (int)NODE_SIZE);
		for (NodeObject nodeObject : nodeImagesList) {
			if(nodeObject.x > xMax) {
				xMax = (int)nodeObject.x;
			}
			if(nodeObject.y > yMax) {
				yMax = (int)nodeObject.y;
			}
			g2.drawOval((int)nodeObject.x, (int)nodeObject.y, (int)nodeObject.size, (int)nodeObject.size);
		}
	}

	private void paintNodes(Graphics2D g2, Node node, int initX2, int initY2, int leftLimit, int rightLimit, int j, int nodeSize) {
		g2.setColor(Color.blue);
		nodeImagesList.add(new NodeObject(initX2 - nodeSize/2, initY2, nodeSize));
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.BOLD, 2*nodeSize/3));
		g2.drawString(node.getValue(),
				initX2 - g2.getFontMetrics().charWidth('a')*(node.getValue().length()/2), 
				initY2 + g2.getFontMetrics().getHeight());
		int xDelta = 2;
		int xPositionIndex = 1;
		int xChild = 0;
		int yChild = 0;
		int childSize = 0;
		float horDelta = (rightLimit - leftLimit) / maxTreeProductionsNumber;
		for (int k = 0; k < node.getChildsCount(); k++) {
			xChild = (int)(leftLimit + ((horDelta/2) * xPositionIndex));
			yChild = (int)(NODE_SIZE/2 + initY2 + VERTICAL_SPACE);
			childSize = ((maxTreeProductionsNumber-1)*nodeSize)/ maxTreeProductionsNumber;
			Node child = node.getChilds().get(k);
			paintNodes(g2, child, xChild, yChild, (int) (horDelta * k)+ leftLimit, (int) (horDelta * (k + 1) + leftLimit), j + 1, childSize);
			g2.setColor(Color.blue);
			g2.drawLine(initX2, initY2 + nodeSize, xChild, yChild);
			xPositionIndex += xDelta;
		}	
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
				zoom -= e.getUnitsToScroll()*0.05;
				wheelX = e.getPoint().getX();
		} else {
			zoom -= e.getUnitsToScroll()*0.05;
				wheelY = e.getPoint().getY();
		}
		xMaxScaled = (int)(zoom*xMax);
		yMaxScaled = (int)(zoom*yMax);
		System.out.println(xMaxScaled +" " + yMaxScaled);
		setPreferredSize(new Dimension(xMaxScaled, yMaxScaled));
		scrollerObserver.updateScrollPane(xMaxScaled, yMaxScaled);
		repaint();
	}
	
	public KeyListener buildKeyListener() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {			
					translateY += (double)(((zoom*NODE_SIZE)/(double)(getHeight()))*wheelY);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {	
					translateY -= (double)(((zoom*NODE_SIZE)/(double)(getHeight()))*wheelY);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					translateX -= (double)(((zoom*NODE_SIZE)/(double)(getWidth()))*wheelX);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					translateX += (double)(((zoom*NODE_SIZE)/(double)(getWidth()))*wheelX);
				}
				repaint();
			}
		};
	}
}
