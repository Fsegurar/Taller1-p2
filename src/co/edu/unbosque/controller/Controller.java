package co.edu.unbosque.controller;

import co.edu.unbosque.model.Model;
import co.edu.unbosque.view.View;

public class Controller {

	private Model model;
	private View view;
	
	public Controller() {
		model = new Model();
		view = new View();
		funcionar();
	}
	
	public void funcionar() {
		System.out.println(model.getManager().uploadData(view.chooserFile()));
		System.out.println(model.getManager().getPet());
		System.out.println("generando ids---------------------------------------------------------------");
		System.out.println(model.getManager().assignID());
		System.out.println(model.getManager().getPet());
	}
	
}
