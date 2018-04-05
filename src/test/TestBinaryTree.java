package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import org.junit.jupiter.api.Test;
import models.MyBinaryTree;

public class TestBinaryTree {
	@Test
	public void testSearchFather() {
		MyBinaryTree<Integer> tree = new MyBinaryTree<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		tree.addNode(35);
		tree.addNode(50);
		tree.addNode(8);
		tree.addNode(12);
		tree.addNode(69);
		tree.addNode(84);
		tree.addNode(25);
		tree.addNode(75);
		tree.addNode(93);
		tree.addNode(26);
		tree.addNode(16);
		tree.addNode(14);
		tree.addNode(19);
		tree.addNode(17);
		tree.addNode(21);
		tree.addNode(18);
		tree.addNode(-94);
		assertEquals(35, (int)tree.searchFather(50).getInfo());
		assertEquals(35, (int)tree.searchFather(8).getInfo());
		assertEquals(25, (int)tree.searchFather(16).getInfo());
		assertEquals(84, (int)tree.searchFather(75).getInfo());
		assertEquals(69, (int)tree.searchFather(84).getInfo());
		tree.removeNode(16);
		assertEquals(-94, (int)tree.searchLowerNode(tree.getRoot()).getInfo());
		assertEquals(93, (int)tree.searchMajorNode(tree.getRoot()).getInfo());
	}
}
