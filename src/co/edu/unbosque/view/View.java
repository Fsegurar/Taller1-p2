package co.edu.unbosque.view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class View {

	public String chooserFile() {
			
			JFileChooser filechooser =new JFileChooser();
			filechooser.showSaveDialog(filechooser);
			File ubicacion = filechooser.getSelectedFile();
			String u = ""+ubicacion;	
			return u; 
			
		}
	
	public void seeInfo(String mesage) {
		System.out.println("cargue el archivo CSV");
	}
	
	public void escribirDato(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	
	
	public int pedirErradas() {
		int dato = 0; 
		dato= JOptionPane.showConfirmDialog(null, "¿Desea ver las cargas erradas?", "", JOptionPane.YES_NO_OPTION);;
		return dato;
	}
}









