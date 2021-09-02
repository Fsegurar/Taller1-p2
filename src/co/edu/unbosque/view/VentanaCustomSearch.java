package co.edu.unbosque.view;

import java.awt.Color;

import javax.swing.JFrame;

import co.edu.unbosque.controller.Controller;

public class VentanaCustomSearch extends JFrame{

	private panelCustomSearch pCustom;
	
	public VentanaCustomSearch(Controller control) {
		setTitle("Custom Search");
		setSize(400,550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		getContentPane().setLayout(null);
		 
		inicializarComponentes();
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		pCustom.getBoton_search().addActionListener(control);
	}

	private void inicializarComponentes() {
		pCustom=new panelCustomSearch();
		pCustom.setBounds(3, 4, 378, 500);
		getContentPane().add(pCustom);		
	}

	public panelCustomSearch getpCustom() {
		return pCustom;
	}

	public void setpCustom(panelCustomSearch pCustom) {
		this.pCustom = pCustom;
	}
	
}
















