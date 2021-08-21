package co.edu.unbosque.view;

import java.io.File;

import javax.swing.JFileChooser;

public class View {

public String chooserFile() {
		
		JFileChooser filechooser =new JFileChooser();
		filechooser.showSaveDialog(filechooser);
		File ubicacion = filechooser.getSelectedFile();
		String u = ""+ubicacion;	
		return u;
		
	}
}
