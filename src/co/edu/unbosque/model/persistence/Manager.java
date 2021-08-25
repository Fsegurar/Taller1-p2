package co.edu.unbosque.model.persistence;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class Manager {
	
	private ArrayList<Pet> pet;
	private ArrayList<Pet> dato;
	private ArrayList<Pet> omit;
	private CSVReader csvReader;
	private FileReader csvFile;
	
	public Manager() {
		pet = new ArrayList<>();
		dato = new ArrayList<>();
		omit = new ArrayList<>();
		 
	}
	
	/*Por medio de una libreria externa se lee el csv separandolo y guardando cada atributo en el objeto de tipo pet*/
	public boolean uploadData(String file){
		boolean dangerous = false;
		try {
			csvFile = new FileReader(file);
			CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
			String[] fila = null;
			csvReader.readNext();
			while ((fila = csvReader.readNext()) != null) {
				try {
					if(fila[4].equals("NO")) {
						dangerous = false;
					} else if(fila[4].equals("SI")) {
						dangerous = true;
					}
					
					if (fila[5].equals(null)) {
						throw new EmptyAttributeException();
					}
					pet.add(new Pet("NO ASIGNADO", Long.parseLong(fila[0]), fila[1], fila[2], fila[3], dangerous, fila[5]));
				
				}catch (EmptyAttributeException e) {
					omit.add(new Pet("NO ASIGNADO", Long.parseLong(fila[0]), fila[1], fila[2],fila[3], dangerous, "No Asignado"));
				}catch (NumberFormatException e) {
					omit.add(new Pet("NO ASIGNADO", 0000000000, fila[1], fila[2],fila[3], dangerous, fila[5]));
				}
			}
			csvFile.close();
			csvReader.close();
			
		} catch(IOException e) {
			return false;
		}catch (CsvValidationException e) {
				
		}
		return true; 
		}
	
	/* crea un id especifico para cada registro dependiendo de la informacion suministrada*/
	public String assignID() {
		String last="";
		
		for(int i = 0;i<pet.size();i++) {
			boolean error = false ;
			int j =2;
			String digitos = pet.get(i).getMicrochip()+"";
			if(pet.get(i).isPotentDangerous()==true) {
				last = "T";
			}else if(pet.get(i).isPotentDangerous()==false) {
				last = "F";
			}
			String id = digitos.substring(digitos.length()-j, digitos.length())+"-"+pet.get(i).getSpecies().substring(0, 1)+pet.get(i).getSex().substring(0, 1)+pet.get(i).getSize().substring(0, 1)+last;
			try {
				for(int k = 0;k<pet.size();k++) {
					if(pet.get(k).getId().equals(id)) {
						error = true;
						throw new IdentifierExistsException();
					}
				}
			}catch (IdentifierExistsException e) {
				do {
					int contador = 0;
					j++;
					id= digitos.substring(digitos.length()-j, digitos.length())+"-"+pet.get(i).getSpecies().substring(0, 1)+pet.get(i).getSex().substring(0, 1)+pet.get(i).getSize().substring(0, 1)+last;
					for(int f = 0;f<i;f++) {
						if(pet.get(f).getId().equals(id)) {
							contador = 1;
							f=i;
						}else {
							contador=0;
						}
					}
					if(contador!=1) {
					 error = false;	
					 j=2;
					}
				}while(error);
				
			}
				pet.get(i).setId(id);
		}
		
		return "Asignacion de id exitosa";
	}
	
	public String findByMicrochip(long number) {
		int j=0;
		boolean flag = false;
		for(int i=0;i<pet.size();i++) {	
			if(pet.get(i).getMicrochip()==number) {
				j=i;
				i=pet.size();
				flag=true;
			}
		}
		if(flag) {
			return pet.get(j).toString();
		}else {
			return "Microchip no encontrado";
		}
		
	}
	
	public String countBySpecies() {
		long dog=0, cat=0, nn=0;
		for(int i=0;i<pet.size();i++) {
			if(pet.get(i).getSpecies().equals("CANINO")) {
				dog++;
			} else if(pet.get(i).getSpecies().equals("FELINO")) {
				cat++;
			}else {
				nn++;
			}
		}
		return "Caninos : "+dog+".\nFelinos : "+cat+".\nNo Identificado : "+nn+".";
	}
	
	public String countByNeighborhood() {
		long anarino=0, bunidos=0, bosa=0, cbolivar=0, chapinero=0, engativa=0, fontibon=0, kenedy=0, candelaria=0, martires=0, aledaños=0, aranda=0, uribe=0, cristobal=0, santafe=0, nn=0, suba=0, sumapaz=0, teusaquillo=0, tunjuelito=0, usaquen=0, usme=0;
		for(int i=0;i<pet.size();i++) {
			if(pet.get(i).getNeighborhood().equals("A. NARINO")) {
				anarino++;
			}else if(pet.get(i).getNeighborhood().equals("B. UNIDOS")) {
				bunidos++;
			}else if(pet.get(i).getNeighborhood().equals("BOSA")) {
				bosa++;
			}else if(pet.get(i).getNeighborhood().equals("C. BOLIVAR")) {
				cbolivar++;
			}else if(pet.get(i).getNeighborhood().equals("CHAPINERO")) {
				chapinero++;
			}else if(pet.get(i).getNeighborhood().equals("ENGATIVA")) {
				engativa++;
			}else if(pet.get(i).getNeighborhood().equals("FONTIBON")) {
				fontibon++;
			}else if(pet.get(i).getNeighborhood().equals("KENNEDY")) {
				kenedy++;
			}else if(pet.get(i).getNeighborhood().equals("LA CANDELARIA")) {
				candelaria++;
			}else if(pet.get(i).getNeighborhood().equals("LOS MARTIRES")) {
				martires++;
			}else if(pet.get(i).getNeighborhood().equals("MUNICIPIOS ALEDAÑOS BOGOTA D.C.")) {
				aledaños++;
			}else if(pet.get(i).getNeighborhood().equals("P. ARANDA")) {
				aranda++;
			}else if(pet.get(i).getNeighborhood().equals("R. URIBE")) {
				uribe++;
			}else if(pet.get(i).getNeighborhood().equals("SAN CRISTOBAL")) {
				cristobal++;
			}else if(pet.get(i).getNeighborhood().equals("SANTA FE")) {
				santafe++;
			}else if(pet.get(i).getNeighborhood().equals("SIN IDENTIFICAR")) {
				nn++;
			}else if(pet.get(i).getNeighborhood().equals("SUBA")) {
				suba++;
			}else if(pet.get(i).getNeighborhood().equals("SUMAPAZ")) {
				sumapaz++;
			}else if(pet.get(i).getNeighborhood().equals("TEUSAQUILLO")) {
				teusaquillo++;
			}else if(pet.get(i).getNeighborhood().equals("TUNJUELITO")) {
				tunjuelito++;
			}else if(pet.get(i).getNeighborhood().equals("USAQUEN")) {
				usaquen++;
			}else if(pet.get(i).getNeighborhood().equals("USME")) {
				usme++;
			}
		}
		return "A. NARINO : "+anarino
				+"\nB. UNIDOS : "+bunidos
				+"\nBOSA : "+bosa
				+"\nC. BOLIVAR : "+cbolivar
				+"\nCHAPINERO : "+chapinero
				+"\nENGATIVA : "+engativa
				+"\nFONTIBON : "+fontibon
				+"\nKENNEDY : "+kenedy
				+"\nLA CANDELARIA : "+candelaria
				+"\nLOS MARTIRES : "+martires
				+"\nP. ARANDA : "+aranda
				+"\nR. URIBE : "+uribe
				+"\nSAN CRISTOBAL : "+cristobal
				+"\nSANTA FE : "+santafe
				+"\nSUBA : "+suba
				+"\nSUMAPAZ : "+sumapaz
				+"\nTEUSAQUILLO : "+teusaquillo
				+"\nTUNJUELITO : "+tunjuelito
				+"\nUSAQUEN : "+usaquen
				+"\nUSME : "+usme
				+"\nMUNICIPIOS ALEDAÑOS BOGOTA D.C. : "+aledaños
				+"\nSIN IDENTIFICAR : "+nn;
	}	
	
	public ArrayList<Pet> getPet() {
		return pet;
	}

	public void setPet(ArrayList<Pet> pet) {
		this.pet = pet;
	}

	public ArrayList<Pet> getDato() {
		return dato;
	}

	public void setDato(ArrayList<Pet> dato) {
		this.dato = dato;
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

