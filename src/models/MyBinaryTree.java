package models;

import java.util.Comparator;

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
		else
			throw new NumberFormatException("El número ya está agregado al árbol");
	}

	/**
	 * Elimina un nodo del árbol
	 * 
	 * @param info
	 */
	public void removeNode(T info) {
		Node<T> nodeFather = null;
		if (comparator.compare(info, root.getInfo()) != 0) {
			nodeFather = searchFather(info);
		}
		Node<T> nodeToRemove = searchNode(info);
		if (!isFather(nodeToRemove)) {
			changeSon(nodeFather, info, null);
		} else if (nodeToRemove.getLeft() != null && nodeToRemove.getRight() == null) {
			changeSon(nodeFather, info, nodeToRemove.getLeft());
		} else if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() != null) {
			changeSon(nodeFather, info, nodeToRemove.getRight());
		} else {
			Node<T> nodeLower = searchLowerNode(nodeToRemove.getRight());
			Node<T> nodeMajor = searchMajorNode(nodeToRemove.getLeft());
			if (Math.abs(comparator.compare(nodeLower.getInfo(), nodeToRemove.getInfo())) < Math
					.abs(comparator.compare(nodeMajor.getInfo(), nodeToRemove.getInfo()))) {
				removeNode(nodeLower.getInfo());
				nodeToRemove.setInfo(nodeLower.getInfo());
			} else {
				removeNode(nodeMajor.getInfo());
				nodeToRemove.setInfo(nodeMajor.getInfo());
			}
		}
	}

	private void changeSon(Node<T> nodeFather, T info, Node<T> nodeNewSon) {
		if (nodeFather == null) {
			if (root.getLeft() != null) {
				if (comparator.compare(root.getLeft().getInfo(), info) == 0)
					if (comparator.compare(nodeNewSon.getInfo(), root.getLeft().getInfo()) != 0) {
						nodeNewSon.setLeft(root.getLeft());
					} else if (comparator.compare(nodeNewSon.getInfo(), root.getRight().getInfo()) != 0)
						nodeNewSon.setRight(root.getRight());
			} else {
				if (comparator.compare(nodeNewSon.getInfo(), root.getRight().getInfo()) != 0)
					nodeNewSon.setRight(root.getRight());
			}
			root = nodeNewSon;
		} else if (nodeFather.getLeft() != null) {
			if (comparator.compare(nodeFather.getLeft().getInfo(), info) == 0)
				nodeFather.setLeft(nodeNewSon);
			else
				nodeFather.setRight(nodeNewSon);
		} else {
			nodeFather.setRight(nodeNewSon);
		}
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
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}

	public Node<T> searchMajorNode(Node<T> node) {
		while (node.getRight() != null) {
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