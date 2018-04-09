package models;

import java.math.RoundingMode;

public class Node <T>{
	private T info;
	private Node<T> left;
	private Node<T> right;
	
	public Node(T info) {
		super();
		this.info = info;
	}
	public Node(T info, Node<T> left, Node<T> right) {
		this.info = info;
		this.left = left;
		this.right = right;
	}
	/**
	 * @return the info
	 */
	public T getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(T info) {
		this.info = info;
	}
	/**
	 * @return the left
	 */
	public Node<T> getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public Node<T> getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		String string = info.toString();
		if(left!= null)
			string += "i: " + left.getInfo();
		if(right != null)
			string += "d: " + right.getInfo();
		return string;
	}
	
}
