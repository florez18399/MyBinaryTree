package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import controller.Controller;
import models.Node;

public class JFrameDrawTree<T> extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTreeBinaryTree<T> jTreeBinaryTree;
	private JPanelDrawTree<T> drawTree;

	public JFrameDrawTree(Node<T> rootTree) {
		init(rootTree);
	}

	private void init(Node<T> rootTree) {
		setTitle("TreeBinary");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		fillJMenu();
		jTreeBinaryTree = new JTreeBinaryTree<T>(rootTree);
		jTreeBinaryTree.setIconNode("/images/vector.png");
		JPanel panel = new JPanel();
		panel.add(jTreeBinaryTree);
		add(panel, BorderLayout.WEST);
		drawTree = new JPanelDrawTree<T>(rootTree, new Dimension(1500, 1500));
		JScrollPane pane = new JScrollPane(drawTree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(pane, BorderLayout.CENTER);
		JButton button = new JButton("Agregar");
		button.setActionCommand("add");
		button.addActionListener(Controller.getInstance());
		add(button, BorderLayout.SOUTH);
		JButton button2 = new JButton("Eliminar");
		button2.addActionListener(Controller.getInstance());
		add(button2, BorderLayout.EAST);
		setVisible(true);
	}

	private void fillJMenu() {
		JMenuBar barMenu = new JMenuBar();
		JMenu menu = new JMenu("Árbol");
		menu.add(createJMenuItem("add", "Agregar", KeyEvent.VK_A));
		menu.add(createJMenuItem("remove", "Eliminar", KeyEvent.VK_R));
		barMenu.add(menu);
		setJMenuBar(barMenu);
	}
	
	private JMenuItem createJMenuItem(String command, String title, int event) {
		JMenuItem menuItem = new JMenuItem(title);
		menuItem.setActionCommand(command);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(event, ActionEvent.ALT_MASK));
		menuItem.addActionListener(Controller.getInstance());
		return menuItem;
	}

	public void repaintComponents() {
		jTreeBinaryTree.repaintTree();
		drawTree.repaint();
		drawTree.revalidate();
	}
}
