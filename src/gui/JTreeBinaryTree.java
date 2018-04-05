package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import models.Node;

public class JTreeBinaryTree extends JTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode mutableTreeNode;
	private DefaultTreeModel defaultTreeModel;
	private Node<Integer> root;

	public JTreeBinaryTree(Node<Integer> root) {
		mutableTreeNode = new DefaultMutableTreeNode();
		defaultTreeModel = new DefaultTreeModel(mutableTreeNode);
		setModel(defaultTreeModel);
		this.root = root;
		init();
	}

	public void init() {
		setSize(500, 500);
		setLayout(new BorderLayout());
		paintTree();
		repaint();
	}

	public void paintTree() {
		mutableTreeNode = createNodeJtree(root);
		defaultTreeModel = new DefaultTreeModel(mutableTreeNode);
		setModel(defaultTreeModel);
		paintTree(root.getLeft(), mutableTreeNode);
		paintTree(root.getRight(), mutableTreeNode);
		// jTree.removeAll();
		// DefaultTreeCellRenderer render = (DefaultTreeCellRenderer)
		// jTree.getCellRenderer();

		// ImageIcon imageIcon = getScaleIconImage(ConstantsGUI.APP_ICON_PATH);
		// render.setLeafIcon(imageIcon);
		// render.setOpenIcon(imageIcon);
		// render.setClosedIcon(imageIcon);
		// render.setBackground(ConstantsGUI.COLOR_BACK);
		// render.setForeground(Color.WHITE);
		// add(jTree, BorderLayout.CENTER);
	}

	private void paintTree(Node<Integer> root, DefaultMutableTreeNode jroot) {
		if (root != null) {
			DefaultMutableTreeNode jNode = createNodeJtree(root);
			jroot.add(jNode);
			paintTree(root.getLeft(), jNode);
			paintTree(root.getRight(), jNode);
		}
	}

	public DefaultMutableTreeNode createNodeJtree(Node<Integer> node) {
		return new DefaultMutableTreeNode(node.toString());
	}

	public ImageIcon getScaleIconImage(String imageURL) {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(imageURL));
		Image image = imageIcon.getImage();
		imageIcon = new ImageIcon(image.getScaledInstance(20, 20, java.awt.Image.SCALE_REPLICATE));
		return imageIcon;
	}

	public void repaintTree() {
		removeAll();
		paintTree();
		revalidate();
		repaint();
	}

}
