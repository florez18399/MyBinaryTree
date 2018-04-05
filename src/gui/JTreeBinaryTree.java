package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import models.Node;

public class JTreeBinaryTree<T> extends JTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode mutableTreeNode;
	private DefaultTreeModel defaultTreeModel;
	private Node<T> root;

	public JTreeBinaryTree(Node<T> root) {
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
	}
	
	public void setIconNode(String pathIcon) {
		DefaultTreeCellRenderer render = (DefaultTreeCellRenderer)
		getCellRenderer();
		ImageIcon imageIcon = getScaleIconImage(pathIcon);
		render.setLeafIcon(imageIcon);
		render.setOpenIcon(imageIcon);
		render.setClosedIcon(imageIcon);
		paintTree();
	}

	private void paintTree(Node<T> root, DefaultMutableTreeNode jroot) {
		if (root != null) {
			DefaultMutableTreeNode jNode = createNodeJtree(root);
			jroot.add(jNode);
			paintTree(root.getLeft(), jNode);
			paintTree(root.getRight(), jNode);
		}
	}

	public DefaultMutableTreeNode createNodeJtree(Node<T> node) {
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
