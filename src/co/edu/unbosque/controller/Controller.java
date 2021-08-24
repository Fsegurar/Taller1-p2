 package co.edu.unbosque.controller;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.Model;
import co.edu.unbosque.view.View;

public class Controller {

	private Model model;
	private View view;
	Byte opcion = 0;
	public Controller() {
		model = new Model();
		view = new View();
		funcionar();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void funcionar() {
		view.escribirDato("Por favor cargue el archivo CSV");
		System.out.println(model.getManager().uploadData(view.chooserFile()));
		
		if(view.pedirErradas()==0) {
			System.out.println(model.getManager().getOmit());
			
		}view.escribirDato("¿Desea asignar un ID a todas las mascotas?");
		System.out.println("generando ids---------------------------------------------------------------");
		System.out.println(model.getManager().assignID());
		System.out.println(model.getManager().getPet());
		
		do {
			opcion=Byte.parseByte(JOptionPane.showInputDialog(
					"¿QUÉ DESEA HACER?\n"+
					"1. Buscar un animal por su microchip\n"+
					"2. Contar la cantidad de animales por especie\n"+
					"3. Contar la cantidad de animales por localidad\n"+
					"ELIJA SU POCIÓN"));	
			switch(opcion) {
			case 1:	
					String x= JOptionPane.showInputDialog(null, "Escriba el número del microchip");
					for(int i=0;i<model.getManager().getPet().size();i++) {	
					if((model.getManager().getPet().get(i).getMicrochip()+"").equals(x)) {
						JOptionPane.showMessageDialog(null, model.getManager().getPet().get(i));
				}
			}
				
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opción inválida");
				break;
			}
		}while (opcion!=3);
		
		
	}
}


















