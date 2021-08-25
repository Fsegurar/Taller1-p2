 package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.Model;
import co.edu.unbosque.view.View;

public class Controller implements ActionListener {

	private Model model;
	private View view;
	Byte opcion = 0;
	public Controller() {
		model = new Model();
		view = new View(this);
		funcionar();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void funcionar() {
		view.escribirDato("Bienvenido al programa ciudadano de 4 patas");
		view.escribirDato("Por favor cargue el archivo CSV");
		if(model.getManager().uploadData(view.chooserFile())) {
			view.mostrarInfo("Carga exitosa", "info");
		}else {
			do {
				view.mostrarInfo("Carga no exitosa", "error");
				view.escribirDato("Por favor cargue el archivo CSV");
			}while(!model.getManager().uploadData(view.chooserFile()));
			view.mostrarInfo("Carga exitosa", "info");
		}
		if(view.yesOrno("¿Desea ver las cargas erradas?")==0) {
			view.getP().getArea().setText(model.getManager().getOmit().toString());
			view.bigData();
			
		}else {
			int opcion;
			do {
				opcion=view.pedirDatoEnteroInt(
						"¿QUÉ DESEA HACER?\n"+
						"1.Asignar Id a todas las mascotas\n"+
						"2.Ver todos los registros\n"+
						"3.Buscar un animal por su microchip\n"+
						"4.Contar la cantidad de animales por especie\n"+
						"5.Contar la cantidad de animales por localidad\n"+
						"6.Busqueda personalizada\n"+
						"7.Salir\n"+
						"ELIJA SU POCIÓN");	
				switch(opcion) {
				case 1:	
					view.escribirDato(model.getManager().assignID());
					break;
				case 2:
					view.getP().getBoton().setActionCommand("OK1");
					view.getP().getArea().setText(model.getManager().getPet().toString());
					view.bigData();
					opcion=7;
					break;
				case 3:
					long number = view.pedirDatoEntero("Escriba el número del microchip");
					view.escribirDato(model.getManager().findByMicrochip(number));
					break;
				case 4:
					view.escribirDato(model.getManager().countBySpecies());
					break;
				case 5:
					view.escribirDato(model.getManager().countByNeighborhood());
					break;
				case 6:
					
					break;
				case 7: 
					view.mostrarInfo("Hasta Pronto", "info");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida");
					break;
				}
			}while (opcion!=7 );
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("OK")) {
			view.setVisible(false);
			view.getP().getBoton().setActionCommand("OK1");
			int opcion;
			do {
				opcion=view.pedirDatoEnteroInt(
						"¿QUÉ DESEA HACER?\n"+
						"1.Asignar Id a todas las mascotas\n"+
						"2.Ver todos los registros\n"+
						"3.Buscar un animal por su microchip\n"+
						"4.Contar la cantidad de animales por especie\n"+
						"5.Contar la cantidad de animales por localidad\n"+
						"6.Busqueda personalizada\n"+
						"7.Salir\n"+
						"ELIJA SU POCIÓN");	
				switch(opcion) {
				case 1:	
					view.escribirDato(model.getManager().assignID());
					break;
				case 2:
					view.getP().getBoton().setActionCommand("OK1");
					view.getP().getArea().setText(model.getManager().getPet().toString());
					view.bigData();
					opcion=7;
					break;
				case 3:
					long number = view.pedirDatoEntero("Escriba el número del microchip");
					view.escribirDato(model.getManager().findByMicrochip(number));
					break;
				case 4:
					view.escribirDato(model.getManager().countBySpecies());
					break;
				case 5:
					view.escribirDato(model.getManager().countByNeighborhood());
					break;
				case 6:
					break;
				case 7: 
					view.mostrarInfo("Hasta Pronto", "info");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida");
					break;
				}
			}while (opcion!=7);
		
		}else if(e.getActionCommand().equals("OK1")) {
			view.setVisible(false);
			int opcion;
			do {
				opcion=view.pedirDatoEnteroInt(
						"¿QUÉ DESEA HACER?\n"+
						"1.Asignar Id a todas las mascotas\n"+
						"2.Ver todos los registros\n"+
						"3.Buscar un animal por su microchip\n"+
						"4.Contar la cantidad de animales por especie\n"+
						"5.Contar la cantidad de animales por localidad\n"+
						"6.Busqueda personalizada\n"+
						"7.Salir\n"+
						"ELIJA SU POCIÓN");	
				switch(opcion) {
				case 1:	
					view.escribirDato(model.getManager().assignID());
					break;
				case 2:
					view.getP().getBoton().setActionCommand("OK1");
					view.getP().getArea().setText(model.getManager().getPet().toString());
					view.bigData();
					opcion=7;
					break;
				case 3:
					long number = view.pedirDatoEntero("Escriba el número del microchip");
					view.escribirDato(model.getManager().findByMicrochip(number));
					break;
				case 4:
					view.escribirDato(model.getManager().countBySpecies());
					break;
				case 5:
					view.escribirDato(model.getManager().countByNeighborhood());
					break;
				case 6:
					break;
				case 7: 
					view.mostrarInfo("Hasta Pronto", "info");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida");
					break;
				}
			}while (opcion!=7);
		}
	}
}


















