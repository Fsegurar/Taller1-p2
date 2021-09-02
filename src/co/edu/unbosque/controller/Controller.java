 package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.Model;
import co.edu.unbosque.view.VentanaCustomSearch;
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
	
	
 //metodo para iniciar el programa.
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
						"6.Búsqueda personalizada\n"+
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
					view.getCustom().setVisible(true);	
					opcion=7;
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
//metodo de asicnacion de acciones.
	@Override
	public void actionPerformed(ActionEvent e) {
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
						"6.Búsqueda personalizada\n"+
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
					view.getCustom().setVisible(true);					
					opcion=7;
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
						"6.Búsqueda personalizada\n"+
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
					view.getCustom().setVisible(true);				
					opcion=7;
					break;
				case 7: 
					view.mostrarInfo("Hasta Pronto", "info");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opción inválida");
					break;
				}
			}while (opcion!=7);
			
		}else if(e.getActionCommand().equals("BUSCAR")) {
			try {
				int n= Integer.parseInt(view.getCustom().getpCustom().getTxtNumero().getText());
				String p= view.getCustom().getpCustom().getTxtPosicion().getText();		
				String s= view.getCustom().getpCustom().getTxtEspecie().getText();
				String se= view.getCustom().getpCustom().getTxtSex().getText();
				String si= view.getCustom().getpCustom().getTxtSize().getText();
				String da= view.getCustom().getpCustom().getTxtDangerous().getText();
				String ne= view.getCustom().getpCustom().getTxtNeighborhood().getText();
				if(p.equals("")&&(!p.equalsIgnoreCase("top")&&!p.equalsIgnoreCase("last"))){
					view.mostrarInfo("Por favor ingrese Top o Last  en el campo de posición (TOP/LAST)", "error");
					view.getCustom().getpCustom().getTxtPosicion().setText("");
				}else if(!s.equals("")&&!s.equalsIgnoreCase("Canino")&&!s.equalsIgnoreCase("felino")&&!s.equalsIgnoreCase("no identificado")) {
					view.mostrarInfo("Por favor ingrese Valores validos en el campo de Especie (Canino/Felino/No Identificado)", "error");
					view.getCustom().getpCustom().getTxtEspecie().setText("");
				}else if(!se.equals("")&&!se.equalsIgnoreCase("Hembra")&&!se.equalsIgnoreCase("macho")) {
					view.mostrarInfo("Por favor ingrese Valores validos en el campo de Sexo (macho/hembra)", "error");
					view.getCustom().getpCustom().getTxtSex().setText("");
				}else if(!si.equals("")&&!si.equalsIgnoreCase("Gigante")&&!si.equalsIgnoreCase("Grande")&&!si.equalsIgnoreCase("Mediano")&&!si.equalsIgnoreCase("Miniatura")&&!si.equalsIgnoreCase("Muy Grande")&&!si.equalsIgnoreCase("Pequeño")) {
					view.mostrarInfo("Por favor ingrese Valores validos en el campo deTamaño (Miniatura/Pequeño/Mediano/Grande/Muy Grande/Gigamte)", "error");
					view.getCustom().getpCustom().getTxtSize().setText("");
				}else if(!da.equals("")&&!da.equals("si")&&!da.equalsIgnoreCase("no")) {
					view.mostrarInfo("Por favor ingrese Valores validos en el campo de ¿Es peligroso? (si/no)", "error");
					view.getCustom().getpCustom().getTxtDangerous().setText("");
				}else if(!ne.equals("")&&!ne.equalsIgnoreCase("A. NARINO")&&!ne.equalsIgnoreCase("B. UNIDOS")&&!ne.equalsIgnoreCase("BOSA")&&!ne.equalsIgnoreCase("C. BOLIVAR")&&!ne.equalsIgnoreCase("CHAPINERO")&&!ne.equalsIgnoreCase("ENGATIVA")&&!ne.equalsIgnoreCase("FONTIBON")&&!ne.equalsIgnoreCase("KENNEDY")&&!ne.equalsIgnoreCase("LA CANDELARIA")&&!ne.equalsIgnoreCase("LOS MARTIRES")&&!ne.equalsIgnoreCase("MUNICIPIOS ALEDAÑOS BOGOTA D.C.")&&!ne.equalsIgnoreCase("P. ARANDA")&&!ne.equalsIgnoreCase("R. URIBE")&&!ne.equalsIgnoreCase("SAN CRISTOBAL")&&!ne.equalsIgnoreCase("SANTA FE")&&!ne.equalsIgnoreCase("SIN IDENTIFICAR")&&!ne.equalsIgnoreCase("SUBA")&&!ne.equalsIgnoreCase("SUMAPAZ")&&!ne.equalsIgnoreCase("TEUSAQUILLO")&&!ne.equalsIgnoreCase("TUNJUELITO")&&!ne.equalsIgnoreCase("USAQUEN")&&!ne.equalsIgnoreCase("USME")) {
					view.mostrarInfo("Por favor ingrese Valores validos en el campo de Barrio\n(A. NARINO/B. UNIDOS/BOSA/C. BOLIVAR/CHAPINERO/ENGATIVA/FONTIBON/KENNEDY/LA CANDELARIA/LOS MARTIRES/P. ARANDA/R. URIBE/SAN CRISTOBAL/SANTA FE/SUBA/SUMAPAZ/TEUSAQUILLO/TUNJUELITO/USAQUEN/USME/MUNICIPIOS ALEDAÑOS BOGOTA D.C./SIN IDENTIFICAR ", "error");
					view.getCustom().getpCustom().getTxtNeighborhood().setText("");
				}else {
					view.getP().getArea().setText(model.getManager().findByMultipleFields(n,p,s,se,si,da,ne));
					view.bigData();
					view.getCustom().setVisible(false);	
					model.getManager().getBusqueda().clear();
					model.getManager().getResul().clear();
				}
			}catch(NumberFormatException k) {
				view.mostrarInfo("Por favor ingrese un Numero en el campo de Número de mascotas que desea ver ", "error");
				view.getCustom().getpCustom().getTxtNumero().setText("");
			}
			
		}
	}	
}




























