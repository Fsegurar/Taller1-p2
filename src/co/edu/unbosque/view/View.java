package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import co.edu.unbosque.controller.Controller;

public class View extends JFrame{
	private panel p;
	private VentanaCustomSearch custom;
	
	public View() {
		
		custom = new VentanaCustomSearch();	
	}
	 
	
	public View(Controller control) {
		setSize(700,500);
		setResizable(false);
		setVisible(false);
		setTitle("Ciudadano de 4patas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		p = new panel();
		add(p,BorderLayout.CENTER);
		
		p.getBoton().addActionListener(control);
	
	}
	
	public void bigData() {
		setVisible(true);
	}

	public String chooserFile() {
			
			JFileChooser filechooser =new JFileChooser();
			filechooser.showSaveDialog(filechooser);
			File ubicacion = filechooser.getSelectedFile();
			String u = ""+ubicacion;	
			return u; 
			
		}
	
	
	public void escribirDato(String string) {
		JOptionPane.showMessageDialog(null, string, "Ciudadano de 4 patas", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	public int yesOrno(String tex) {
		int dato = 0; 
		dato= JOptionPane.showConfirmDialog(null, tex, "Ciudadano de 4 patas", JOptionPane.YES_NO_OPTION);;
		return dato;
	}
	
	public int pedirDatoEnteroInt(String mensaje) {
		boolean error = false;
		int numero = 0;
		do {
			try {
				numero = 0;
				numero = Integer.parseInt(JOptionPane.showInputDialog(null,mensaje , "Ciudadano de 4 patas", JOptionPane.QUESTION_MESSAGE));
				error = false;
			} catch ( NumberFormatException e) {
				mostrarInfo("Digite número válido", "error");
				error = true;
			} 
		}while (error);	
		
		return numero;
	}
	
	public long pedirDatoEntero(String mensaje) {
		boolean error = false;
		long numero = 0;
		do {
			try {
				numero = 0;
				numero = Long.parseLong(JOptionPane.showInputDialog(null,mensaje , "Ciudadano de 4patas", JOptionPane.QUESTION_MESSAGE));
				error = false;
			} catch ( NumberFormatException e) {
				mostrarInfo("Digite numero valido", "error");
				error = true;
			} 
		}while (error);	
		
		return numero;
	}
	
	public void mostrarInfo(String dato, String tipo) {
		if(tipo.equals("info")) {
			JOptionPane.showMessageDialog(null, dato,"informacion",JOptionPane.INFORMATION_MESSAGE);
		}else if(tipo.equals("error")) {
			JOptionPane.showMessageDialog(null, dato, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public panel getP() {
		return p;
	}

	public void setP(panel p) {
		this.p = p;
	}

	public VentanaCustomSearch getCustom() {
		return custom;
	}

	public void setCustom(VentanaCustomSearch custom) {
		this.custom = custom;
	}
	
	
}









