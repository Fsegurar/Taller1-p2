package co.edu.unbosque.view;

import java.awt.Color;

import javax.swing.JFrame;

public class VentanaCustomSearch extends JFrame{

	private panelCustomSearch pCustom;
	
	public VentanaCustomSearch() {
		setTitle("Custom Search");
		setSize(400,550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		getContentPane().setLayout(null);
		 
		inicializarComponentes();
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
	
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
















