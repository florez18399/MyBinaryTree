package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import models.Node;

public class JPanelDrawTree<T> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node<T> root;

	public JPanelDrawTree(Node<T> rootTree, Dimension dimension) {
		this.root = rootTree;
		init(dimension);
	}

	private void init(Dimension dimension) {
		setSize(dimension);
		setPreferredSize(dimension);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintTree(g, root, getSize().width/2 , 40, getSize().width/2);
	}
	
	public void paintTree(Graphics g, Node<T> actual, int x, int y, int total) {
		total /= 2;
		if(actual != null) {
			g.setColor(Color.RED);
			g.fillOval(x, y, 70, 40);
			g.setColor(Color.WHITE);
			g.drawString(actual.toString(), x + 20, y + 20); 
			g.setColor(Color.BLACK);
			g.drawLine(x + 35, y + 40, x - total + 35, y + 100);
			paintTree(g, actual.getLeft(), x - total, y + 100, total);
			g.drawLine(x + 35, y + 40, x + total + 35, y + 100);
			paintTree(g, actual.getRight(), x + total, y + 100, total);
		}
	}

}
