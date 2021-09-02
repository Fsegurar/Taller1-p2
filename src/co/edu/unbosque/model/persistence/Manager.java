package co.edu.unbosque.model.persistence;

import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import co.edu.unbosque.view.VentanaCustomSearch;

public class Manager {

	private ArrayList<Pet> pet;
	private ArrayList<Pet> omit;
	private ArrayList<Pet> busqueda;
	private ArrayList<Pet> resul;
	private CSVReader csvReader;
	private FileReader csvFile;

	public Manager() {
		resul = new ArrayList<>();
		pet = new ArrayList<>();
		omit = new ArrayList<>();
		busqueda = new ArrayList<>();

	}

	/*
	 * Por medio de una libreria externa se lee el csv separandolo y guardando cada
	 * atributo en el objeto de tipo pet
	 */
	public boolean uploadData(String file) {
		boolean dangerous = false;
		try {
			csvFile = new FileReader(file);
			CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
			String[] fila = null;
			csvReader.readNext();
			while ((fila = csvReader.readNext()) != null) {
				try {
					if (fila[4].equals("NO")) {
						dangerous = false;
					} else if (fila[4].equals("SI")) {
						dangerous = true;
					}

					if (fila[5].equals(null)) {
						throw new EmptyAttributeException();
					}
					pet.add(new Pet("NO ASIGNADO", Long.parseLong(fila[0]), fila[1], fila[2], fila[3], dangerous,
							fila[5]));

				} catch (EmptyAttributeException e) {
					omit.add(new Pet("NO ASIGNADO", Long.parseLong(fila[0]), fila[1], fila[2], fila[3], dangerous,
							"No Asignado"));
				} catch (NumberFormatException e) {
					omit.add(new Pet("NO ASIGNADO", 0000000000, fila[1], fila[2], fila[3], dangerous, fila[5]));
				}
			}
			csvFile.close();
			csvReader.close();

		} catch (IOException e) {
			return false;
		} catch (CsvValidationException e) {

		}
		return true;
	}

	/*
	 * crea un id especifico para cada registro dependiendo de la informacion
	 * suministrada
	 */
	public String assignID() {
		String last = "";

		for (int i = 0; i < pet.size(); i++) {
			boolean error = false;
			int j = 2;
			String digitos = pet.get(i).getMicrochip() + "";
			if (pet.get(i).isPotentDangerous() == true) {
				last = "T";
			} else if (pet.get(i).isPotentDangerous() == false) {
				last = "F";
			}
			String id = digitos.substring(digitos.length() - j, digitos.length()) + "-"
					+ pet.get(i).getSpecies().substring(0, 1) + pet.get(i).getSex().substring(0, 1)
					+ pet.get(i).getSize().substring(0, 1) + last;
			try {
				for (int k = 0; k < pet.size(); k++) {
					if (pet.get(k).getId().equals(id)) {
						error = true;
						throw new IdentifierExistsException();
					}
				}
			} catch (IdentifierExistsException e) {
				do {
					int contador = 0;
					j++;
					id = digitos.substring(digitos.length() - j, digitos.length()) + "-"
							+ pet.get(i).getSpecies().substring(0, 1) + pet.get(i).getSex().substring(0, 1)
							+ pet.get(i).getSize().substring(0, 1) + last;
					for (int f = 0; f < i; f++) {
						if (pet.get(f).getId().equals(id)) {
							contador = 1;
							f = i;
						} else {
							contador = 0;
						}
					}
					if (contador != 1) {
						error = false;
						j = 2;
					}
				} while (error);

			}
			pet.get(i).setId(id);
		}

		return "Asignacion de id exitosa";
	}

	// método para búsqueda por medio del microchip
	public String findByMicrochip(long number) {
		int j = 0;
		boolean flag = false;
		for (int i = 0; i < pet.size(); i++) {
			if (pet.get(i).getMicrochip() == number) {
				j = i;
				i = pet.size();
				flag = true;
			}
		}
		if (flag) {
			return pet.get(j).toString();
		} else {
			return "Microchip no encontrado";
		}

	}

	// método para contar las especies
	public String countBySpecies() {
		long dog = 0, cat = 0, nn = 0;
		for (int i = 0; i < pet.size(); i++) {
			if (pet.get(i).getSpecies().equals("CANINO")) {
				dog++;
			} else if (pet.get(i).getSpecies().equals("FELINO")) {
				cat++;
			} else {
				nn++;
			}
		}
		return "Caninos : " + dog + ".\nFelinos : " + cat + ".\nNo Identificado : " + nn + ".";
	}

	// método para contar los animales por localidades
	public String countByNeighborhood() {
		long anarino = 0, bunidos = 0, bosa = 0, cbolivar = 0, chapinero = 0, engativa = 0, fontibon = 0, kenedy = 0,
				candelaria = 0, martires = 0, aledaños = 0, aranda = 0, uribe = 0, cristobal = 0, santafe = 0, nn = 0,
				suba = 0, sumapaz = 0, teusaquillo = 0, tunjuelito = 0, usaquen = 0, usme = 0;
		for (int i = 0; i < pet.size(); i++) {
			if (pet.get(i).getNeighborhood().equals("A. NARINO")) {
				anarino++;
			} else if (pet.get(i).getNeighborhood().equals("B. UNIDOS")) {
				bunidos++;
			} else if (pet.get(i).getNeighborhood().equals("BOSA")) {
				bosa++;
			} else if (pet.get(i).getNeighborhood().equals("C. BOLIVAR")) {
				cbolivar++;
			} else if (pet.get(i).getNeighborhood().equals("CHAPINERO")) {
				chapinero++;
			} else if (pet.get(i).getNeighborhood().equals("ENGATIVA")) {
				engativa++;
			} else if (pet.get(i).getNeighborhood().equals("FONTIBON")) {
				fontibon++;
			} else if (pet.get(i).getNeighborhood().equals("KENNEDY")) {
				kenedy++;
			} else if (pet.get(i).getNeighborhood().equals("LA CANDELARIA")) {
				candelaria++;
			} else if (pet.get(i).getNeighborhood().equals("LOS MARTIRES")) {
				martires++;
			} else if (pet.get(i).getNeighborhood().equals("MUNICIPIOS ALEDAÑOS BOGOTA D.C.")) {
				aledaños++;
			} else if (pet.get(i).getNeighborhood().equals("P. ARANDA")) {
				aranda++;
			} else if (pet.get(i).getNeighborhood().equals("R. URIBE")) {
				uribe++;
			} else if (pet.get(i).getNeighborhood().equals("SAN CRISTOBAL")) {
				cristobal++;
			} else if (pet.get(i).getNeighborhood().equals("SANTA FE")) {
				santafe++;
			} else if (pet.get(i).getNeighborhood().equals("SIN IDENTIFICAR")) {
				nn++;
			} else if (pet.get(i).getNeighborhood().equals("SUBA")) {
				suba++;
			} else if (pet.get(i).getNeighborhood().equals("SUMAPAZ")) {
				sumapaz++;
			} else if (pet.get(i).getNeighborhood().equals("TEUSAQUILLO")) {
				teusaquillo++;
			} else if (pet.get(i).getNeighborhood().equals("TUNJUELITO")) {
				tunjuelito++;
			} else if (pet.get(i).getNeighborhood().equals("USAQUEN")) {
				usaquen++;
			} else if (pet.get(i).getNeighborhood().equals("USME")) {
				usme++;
			}
		}
		return "A.Nariño: " + anarino + "\nB.Unidos: " + bunidos + "\nBosa: " + bosa + "\nC.Bolivar: " + cbolivar
				+ "\nChapinero: " + chapinero + "\nEgativá: " + engativa + "\nFontibón: " + fontibon + "\nKennedy: "
				+ kenedy + "\nLa Candelaria: " + candelaria + "\nLos Mártires: " + martires + "\nP.Aranda: " + aranda
				+ "\nR.Uribe: " + uribe + "\nSan cristobal: " + cristobal + "\nSantafé: " + santafe + "\nSuba: " + suba
				+ "\nSumapaz: " + sumapaz + "\nTeusaquillo: " + teusaquillo + "\nTunjuelito: " + tunjuelito
				+ "\nUsaquén: " + usaquen + "\nUsme: " + usme + "\nMunicipios aledaños Bgotá D.C: " + aledaños
				+ "\nSin identificar: " + nn;
	}

	// método para búsqueda personalizada
	public String findByMultipleFields(int n, String p, String s, String se, String si, String da, String ne) {	
		int numero = n;
		if(p.equalsIgnoreCase("top")) {
			if(s.equals("")&&se.equals("")&&si.equals("")&&da.equals("")&&ne.equals("")) {
				for(int i=0;i<pet.size();i++) {
					busqueda.add(pet.get(i));
				}
			}else {
				for(int i=0;i<pet.size();i++) {
					if(pet.get(i).getSpecies().equalsIgnoreCase(s)) {			
						if(pet.get(i).getSex().equalsIgnoreCase(se)) {				
							if(pet.get(i).getSize().equalsIgnoreCase(si)) {	
								if(da.equalsIgnoreCase("si")) {	
									if(pet.get(i).isPotentDangerous()==true) {
										if(pet.get(i).getNeighborhood().equalsIgnoreCase(ne)) {
											busqueda.add(pet.get(i));
									}
								}
							}else if(da.equalsIgnoreCase("no")) {	
								if(pet.get(i).isPotentDangerous()==false) {
									if(pet.get(i).getNeighborhood().equalsIgnoreCase(ne)) {
										busqueda.add(pet.get(i));
										}
									}
								}
							}
						}
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(se.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))) {
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&(s.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))) {
						busqueda.add(pet.get(i));
					}if(pet.get(i).getSize().equalsIgnoreCase(si)&(s.equals(""))&(se.equals(""))&(da.equals(""))&(ne.equals(""))) {
						busqueda.add(pet.get(i));
					}if((da.equalsIgnoreCase("si"))&((se.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))&s.equals(""))) {
						if(pet.get(i).isPotentDangerous()==true) {
							busqueda.add(pet.get(i));
						}
					}else if((da.equalsIgnoreCase("no"))&((se.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))&s.equals(""))) {	
						if(pet.get(i).isPotentDangerous()==false) {
								busqueda.add(pet.get(i));
						}
					}if(pet.get(i).getNeighborhood().equalsIgnoreCase(ne)&(s.equals(""))&(se.equals(""))&(da.equals(""))&(si.equals(""))) {
						busqueda.add(pet.get(i));
					}
					
					///Búsqueda de especie y cualquier otro campo
					
					if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(pet.get(i).getSex().equalsIgnoreCase(se))&(si.equals(""))&(da.equals(""))&(ne.equals(""))){                              
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(pet.get(i).getSize().equalsIgnoreCase(si))&(se.equals(""))&(da.equals(""))&(ne.equals(""))){                              
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(da.equalsIgnoreCase("si"))&(se.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==true) {
							busqueda.add(pet.get(i));
					}
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(da.equalsIgnoreCase("no"))&(se.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==false) {
							busqueda.add(pet.get(i));
						}
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&(se.equals(""))&(da.equals(""))&(si.equals(""))){                              
						busqueda.add(pet.get(i));
						
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&&(da.equalsIgnoreCase(""))) {			                                      																					
									busqueda.add(pet.get(i));	
					}	
									
					///Búsqueda de sexo y cualquier otro campo
			
					if((pet.get(i).getSex().equalsIgnoreCase(se))&(pet.get(i).getSize().equalsIgnoreCase(si))&(s.equals(""))&(da.equals(""))&(ne.equals(""))){                              
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSex().equalsIgnoreCase(s))&(da.equalsIgnoreCase("si"))&(s.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==true) {
							busqueda.add(pet.get(i));
					}
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&(da.equalsIgnoreCase("no"))&(s.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==false) {
							busqueda.add(pet.get(i));
						}
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&(s.equals(""))&(da.equals(""))&(si.equals(""))){                              
						busqueda.add(pet.get(i));
						
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&&(da.equalsIgnoreCase(""))) {			                                      																					
									busqueda.add(pet.get(i));	
					}	
					
					///Todos menos uno		
					
					if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(ne.equalsIgnoreCase(""))) {			                                      																					
						if(da.equalsIgnoreCase("si")) {
							if(pet.get(i).isPotentDangerous()==true) {
									busqueda.add(pet.get(i));
							}
							}else if(da.equalsIgnoreCase("no")) {	
										if(pet.get(i).isPotentDangerous()==false) {									
												busqueda.add(pet.get(i));	
										}
								}
						}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(si.equalsIgnoreCase(""))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))) {			                                      																					
							if(da.equalsIgnoreCase("si")) {
								if(pet.get(i).isPotentDangerous()==true) {
										busqueda.add(pet.get(i));
								}
								}else if(da.equalsIgnoreCase("no")) {	
											if(pet.get(i).isPotentDangerous()==false) {									
													busqueda.add(pet.get(i));	
									}
								}
							}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(se.equalsIgnoreCase(""))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))) {			                                      																											
								if(da.equalsIgnoreCase("si")) {
									if(pet.get(i).isPotentDangerous()==true) {
										busqueda.add(pet.get(i));
									}
									}else if(da.equalsIgnoreCase("no")) {	
												if(pet.get(i).isPotentDangerous()==false) {									
													busqueda.add(pet.get(i));
								}
							}
						}if((s.equalsIgnoreCase(""))&(pet.get(i).getSex().equalsIgnoreCase(se))&(pet.get(i).getSize().equalsIgnoreCase(si))&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))) {			                                      																					
							if(da.equalsIgnoreCase("si")) {
								if(pet.get(i).isPotentDangerous()==true) {
									busqueda.add(pet.get(i));
								}
								}else if(da.equalsIgnoreCase("no")) {	
											if(pet.get(i).isPotentDangerous()==false) {									
													busqueda.add(pet.get(i));
											}
								}
						}
				}
		}
	}		
		if(p.equalsIgnoreCase("last")) {
			if(s.equals("")&&se.equals("")&&si.equals("")&&da.equals("")&&ne.equals("")) {
				for(int i=pet.size()-1;i>=0;i--) {
					busqueda.add(pet.get(i));
				}
			}else {
				for(int i=pet.size()-1;i>=0;i--) {
					if(pet.get(i).getSpecies().equalsIgnoreCase(s)) {			
						if(pet.get(i).getSex().equalsIgnoreCase(se)) {				
							if(pet.get(i).getSize().equalsIgnoreCase(si)) {	
								if(da.equalsIgnoreCase("si")) {	
									if(pet.get(i).isPotentDangerous()==true) {
										if(pet.get(i).getNeighborhood().equalsIgnoreCase(ne)) {
											busqueda.add(pet.get(i));
									}
								}
							}else if(da.equalsIgnoreCase("no")) {	
								if(pet.get(i).isPotentDangerous()==false) {
									if(pet.get(i).getNeighborhood().equalsIgnoreCase(ne)) {
										busqueda.add(pet.get(i));
										}
									}
								}
							}
						}
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(se.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))) {
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&(s.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))) {
						busqueda.add(pet.get(i));
					}if(pet.get(i).getSize().equalsIgnoreCase(si)&(s.equals(""))&(se.equals(""))&(da.equals(""))&(ne.equals(""))) {
						busqueda.add(pet.get(i));
					}if((da.equalsIgnoreCase("si"))&((se.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))&s.equals(""))) {
						if(pet.get(i).isPotentDangerous()==true) {
							busqueda.add(pet.get(i));
					}
					}else if((da.equalsIgnoreCase("no"))&((se.equals(""))&(si.equals(""))&(da.equals(""))&(ne.equals(""))&s.equals(""))) {	
						if(pet.get(i).isPotentDangerous()==false) {
								busqueda.add(pet.get(i));
						}
					}if(pet.get(i).getNeighborhood().equalsIgnoreCase(ne)&(s.equals(""))&(se.equals(""))&(da.equals(""))&(si.equals(""))) {
						busqueda.add(pet.get(i));
					}
					
					///Búsqueda de especie y cualquier otro campo
					
					if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(pet.get(i).getSex().equalsIgnoreCase(se))&(si.equals(""))&(da.equals(""))&(ne.equals(""))){                              
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(pet.get(i).getSize().equalsIgnoreCase(si))&(se.equals(""))&(da.equals(""))&(ne.equals(""))){                              
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(da.equalsIgnoreCase("si"))&(se.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==true) {
							busqueda.add(pet.get(i));
					}
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(da.equalsIgnoreCase("no"))&(se.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==false) {
							busqueda.add(pet.get(i));
						}
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&(se.equals(""))&(da.equals(""))&(si.equals(""))){                              
						busqueda.add(pet.get(i));
						
					}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&&(da.equalsIgnoreCase(""))) {			                                      																					
									busqueda.add(pet.get(i));	
					}	
									
					///Búsqueda de sexo y cualquier otro campo
			
					if((pet.get(i).getSex().equalsIgnoreCase(se))&(pet.get(i).getSize().equalsIgnoreCase(si))&(s.equals(""))&(da.equals(""))&(ne.equals(""))){                              
						busqueda.add(pet.get(i));
					}if((pet.get(i).getSex().equalsIgnoreCase(s))&(da.equalsIgnoreCase("si"))&(s.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==true) {
							busqueda.add(pet.get(i));
					}
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&(da.equalsIgnoreCase("no"))&(s.equals(""))&(ne.equals(""))&(si.equals(""))){                              
						if(pet.get(i).isPotentDangerous()==false) {
							busqueda.add(pet.get(i));
						}
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&(s.equals(""))&(da.equals(""))&(si.equals(""))){                              
						busqueda.add(pet.get(i));
						
					}if((pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))&&(da.equalsIgnoreCase(""))) {			                                      																					
									busqueda.add(pet.get(i));	
					}	
					
					///Todos menos uno		
					
					if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(ne.equalsIgnoreCase(""))) {			                                      																					
						if(da.equalsIgnoreCase("si")) {
							if(pet.get(i).isPotentDangerous()==true) {
									busqueda.add(pet.get(i));
							}
							}else if(da.equalsIgnoreCase("no")) {	
										if(pet.get(i).isPotentDangerous()==false) {									
												busqueda.add(pet.get(i));	
										}
								}
						}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(pet.get(i).getSex().equalsIgnoreCase(se))&&(si.equalsIgnoreCase(""))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))) {			                                      																					
							if(da.equalsIgnoreCase("si")) {
								if(pet.get(i).isPotentDangerous()==true) {
										busqueda.add(pet.get(i));
								}
								}else if(da.equalsIgnoreCase("no")) {	
											if(pet.get(i).isPotentDangerous()==false) {									
													busqueda.add(pet.get(i));	
									}
								}
							}if((pet.get(i).getSpecies().equalsIgnoreCase(s))&&(se.equalsIgnoreCase(""))&&(pet.get(i).getSize().equalsIgnoreCase(si))&&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))) {			                                      																											
								if(da.equalsIgnoreCase("si")) {
									if(pet.get(i).isPotentDangerous()==true) {
											busqueda.add(pet.get(i));
									}
									}else if(da.equalsIgnoreCase("no")) {	
												if(pet.get(i).isPotentDangerous()==false) {									
														busqueda.add(pet.get(i));
								}
							}
						}if((s.equalsIgnoreCase(""))&(pet.get(i).getSex().equalsIgnoreCase(se))&(pet.get(i).getSize().equalsIgnoreCase(si))&(pet.get(i).getNeighborhood().equalsIgnoreCase(ne))) {			                                      																					
							if(da.equalsIgnoreCase("si")) {
								if(pet.get(i).isPotentDangerous()==true) {
										busqueda.add(pet.get(i));
								}
								}else if(da.equalsIgnoreCase("no")) {	
											if(pet.get(i).isPotentDangerous()==false) {									
													busqueda.add(pet.get(i));
							}
						}
					}
				}
			}
	}
	for(int i=0;i<numero;i++) {
		resul.add(busqueda.get(i));
	}	
	return resul.toString();		
}

	///////////////////////////////////////////////////////////////"""
	public ArrayList<Pet> getPet() {
		return pet;
	}

	public void setPet(ArrayList<Pet> pet) {
		this.pet = pet;
	}

	public ArrayList<Pet> getBusqueda() {
		return busqueda;
	}

	public ArrayList<Pet> getResul() {
		return resul;
	}

	public void setResul(ArrayList<Pet> resul) {
		this.resul = resul;
	}

	public void setBusqueda(ArrayList<Pet> busqueda) {
		this.busqueda = busqueda;
	}

	public ArrayList<Pet> getOmit() {
		return omit;
	}

	public void setOmit(ArrayList<Pet> omit) {
		this.omit = omit;
	}

	public CSVReader getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CSVReader csvReader) {
		this.csvReader = csvReader;
	}

	public FileReader getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(FileReader csvFile) {
		this.csvFile = csvFile;
	}

}
