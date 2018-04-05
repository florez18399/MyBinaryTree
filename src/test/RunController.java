package test;

import controller.Controller;

public class RunController {
	public static void main(String[] args) {
		Controller controller = Controller.getInstance();
		controller.initComponents();
	}
}
