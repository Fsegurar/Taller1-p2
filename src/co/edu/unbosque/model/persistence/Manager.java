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
	public String uploadData(String file){
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
			return "Carga no exitosa";
		}catch (CsvValidationException e) {
				
		}
		return "Carga exitosa";
		}
	
	/* crea un id especifico para cada registro dependiendo de la informacion suministrada*/
	public String assignID() {
		String last="";
		
		for(int i = 0;i<pet.size();i++) {
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
						throw new IdentifierExistsException();
						
					}
				}
			}catch (IdentifierExistsException e) {
				j++;
				id= digitos.substring(digitos.length()-j, digitos.length())+"-"+pet.get(i).getSpecies().substring(0, 1)+pet.get(i).getSex().substring(0, 1)+pet.get(i).getSize().substring(0, 1)+last;
				j=2;
			}
				pet.get(i).setId(id);
				
			}
		
		return "Asignacion de id exitosa";
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

