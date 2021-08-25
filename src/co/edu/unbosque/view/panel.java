package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class panel extends JPanel{
	 private JTextArea area;
	 private JScrollPane scroll;
	 private JButton boton;
	 
	 public panel() {
		 setLayout(new BorderLayout());
		 area=new JTextArea();
		 boton = new JButton();
		 
		 area.setLineWrap(true);
		 scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 boton = new JButton("Ok");
		 boton.setActionCommand("OK");
		 boton.setSize(50, 20);
		 
		 add(scroll,BorderLayout.CENTER);
		 add(boton, BorderLayout.SOUTH);
	 }

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JButton getBoton() {
		return boton;
	}

	public void setBoton(JButton boton) {
		this.boton = boton;
	}
	 
}
