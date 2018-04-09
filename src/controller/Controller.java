package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import javax.swing.JOptionPane;
import gui.JFrameDrawTree;
import models.MyBinaryTree;

public class Controller implements ActionListener {
	private static Controller controller;
	private JFrameDrawTree<Integer> frameMain;
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
		frameMain = new JFrameDrawTree<Integer>(binaryTree.getRoot());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "add":
			try {
			int number = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero a agregar"));
				binaryTree.addNode(number);
				frameMain.setRoot(binaryTree.getRoot());
				frameMain.repaintComponents();
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(frameMain, "Número errado");
			}
			break;

		case "remove":
			try {
				int number2 = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero a eliminar"));
				binaryTree.removeNode(number2);
				frameMain.setRoot(binaryTree.getRoot());
				frameMain.repaintComponents();
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(frameMain, "Número errado");
			}
			break;

		default:
			break;
		}

	}

}
