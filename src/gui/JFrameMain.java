package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import models.MyBinaryTree;
import models.Node;

public class JFrameMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTreeBinaryTree jTreeBinaryTree;
	private JPanelDrawTree<Integer> drawTree;

	public JFrameMain(Node<Integer> rootTree) {
		init(rootTree);
	}

	private void init(Node<Integer> rootTree) {
		setTitle("TreeBinary");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		jTreeBinaryTree = new JTreeBinaryTree(rootTree);
		JPanel panel = new JPanel();
		panel.add(jTreeBinaryTree);
		add(panel, BorderLayout.WEST);

		drawTree = new JPanelDrawTree<>(rootTree, new Dimension(1500, 1500));
		JScrollPane pane = new JScrollPane(drawTree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(pane, BorderLayout.CENTER);

		JButton button = new JButton("Agregar");
		button.setActionCommand("add");
		button.addActionListener(Controller.getInstance());
		add(button, BorderLayout.SOUTH);

		JButton button2 = new JButton("Eliminar");
		button2.setActionCommand("remove");
		button2.addActionListener(Controller.getInstance());
		add(button2, BorderLayout.EAST);
		setVisible(true);
	}

	public void repaintComponents() {
		jTreeBinaryTree.repaintTree();
		drawTree.repaint();
		drawTree.revalidate();
	}
}
