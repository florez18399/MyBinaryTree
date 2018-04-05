package models;

import java.util.Comparator;

import org.w3c.dom.NamedNodeMap;

public class MyBinaryTree<T> {
	private Comparator<T> comparator;
	private Node<T> root;

	public MyBinaryTree(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Método de agregación de nodo al árbol binario
	 */
	public void addNode(T info) {
		addNode(new Node<T>(info));
	}

	public void addNode(Node<T> nodeToAdd) {
		if (root != null)
			addNode(nodeToAdd, root);
		else
			root = nodeToAdd;
	}

	private void addNode(Node<T> nodeToAdd, Node<T> actual) {
		if (comparator.compare(actual.getInfo(), nodeToAdd.getInfo()) > 0)
			if (actual.getLeft() != null)
				addNode(nodeToAdd, actual.getLeft());
			else
				actual.setLeft(nodeToAdd);
		else if (comparator.compare(actual.getInfo(), nodeToAdd.getInfo()) < 0)
			if (actual.getRight() != null)
				addNode(nodeToAdd, actual.getRight());
			else
				actual.setRight(nodeToAdd);
	}

	/**
	 * Elimina un nodo del árbol
	 * 
	 * @param info
	 */
	public void removeNode(T info) {
		Node<T> nodeFather = searchFather(info);
		Node<T> nodeToRemove = searchNode(info);
		if (!isFather(nodeToRemove)) {
			changeSon(nodeFather, nodeToRemove, null);
		} else if (nodeToRemove.getLeft() != null && nodeToRemove.getRight() == null) {
			changeSon(nodeFather, nodeToRemove, nodeToRemove.getLeft());
		} else if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() != null) {
			changeSon(nodeFather, nodeToRemove, nodeToRemove.getRight());
		} else {
			Node<T> newSon = searchLowerToRemove(nodeToRemove.getRight());
			newSon.setLeft(nodeToRemove.getLeft());
			newSon.setRight(nodeToRemove.getRight());
			changeSon(nodeFather, nodeToRemove, newSon);
		}
	}

	private void changeSon(Node<T> nodeFather, Node<T> nodeSonActual, Node<T> nodeNewSon) {
		if (nodeFather.getLeft() != null) {
			if (nodeFather.getLeft().equals(nodeSonActual))
				nodeFather.setLeft(nodeNewSon);
			else
				nodeFather.setRight(nodeNewSon);
		} else {
			nodeFather.setRight(nodeNewSon);
		}
	}

	private Node<T> searchLowerToRemove(Node<T> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		Node<T> nodeToReturn = new Node<T>(node.getInfo());
		searchFather(node.getInfo()).setLeft(node.getRight());
		return nodeToReturn;
	}

	private boolean isFather(Node<T> node) {
		return (node.getLeft() != null || node.getRight() != null);
	}

	/**
	 * Busca un nodo en el árbol binario
	 * 
	 * @param info
	 *            : Contenido del nodo a buscar
	 * @return
	 */
	public Node<T> searchNode(T info) {
		return searchNode(root, info);
	}

	private Node<T> searchNode(Node<T> actual, T info) {
		if (actual == null)
			throw new NullPointerException("El nódo buscado no se encuentra en el árbol");
		else if (comparator.compare(actual.getInfo(), info) == 0)
			return actual;
		else if (comparator.compare(actual.getInfo(), info) > 0)
			return searchNode(actual.getLeft(), info);
		else
			return searchNode(actual.getRight(), info);
	}

	/**
	 * Busca el nodo padre de un nodo.
	 * 
	 * @param info:
	 *            Contenido del nodo hijo
	 * @return
	 */
	public Node<T> searchFather(T info) {
		if (comparator.compare(root.getInfo(), info) != 0)
			return searchFather(root, info);
		else
			throw new NullPointerException("El nodo que busca es la raíz");
	}

	private Node<T> searchFather(Node<T> actual, T info) {
		if (actual == null)
			throw new NullPointerException("No se encuntra el nodo con la información buscada: " + info);
		else if (isFather(actual, info))
			return actual;
		else if (comparator.compare(actual.getInfo(), info) > 0)
			return searchFather(actual.getLeft(), info);
		else
			return searchFather(actual.getRight(), info);
	}

	private boolean isFather(Node<T> father, T infoSon) {
		if (father.getLeft() != null) {
			if (comparator.compare(father.getLeft().getInfo(), infoSon) == 0)
				return true;
		}
		if (father.getRight() != null) {
			if (comparator.compare(father.getRight().getInfo(), infoSon) == 0)
				return true;
		}
		return false;
	}

	/**
	 * @return the root
	 */
	public Node<T> getRoot() {
		return root;
	}
	
	public Node<T> searchLowerNode(Node<T> node) {
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
	
	public Node<T> searchMajorNode(Node<T> node) {
		while(node.getRight() != null) {
			node = node.getRight();
		}
		return node;
	}
	

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}
}