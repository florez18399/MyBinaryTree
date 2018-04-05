package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.JOptionPane;

import gui.JFrameMain;
import models.MyBinaryTree;

public class Controller implements ActionListener {
	private static Controller controller;
	private JFrameMain frameMain;
	private MyBinaryTree<Integer> binaryTree;

	private Controller() {

	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public void initComponents() {
		binaryTree = new MyBinaryTree<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}

		});
		binaryTree.addNode(100);
		frameMain = new JFrameMain(binaryTree.getRoot());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "add":
			int number = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero a agregar"));
			binaryTree.addNode(number);
			frameMain.repaintComponents();
			break;

		case "remove":
			int number2 = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero a eliminar"));
			binaryTree.removeNode(number2);
			frameMain.repaintComponents();
			break;

		default:
			break;
		}

	}

}
