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
		
	}
	
	public void readFile(String file){
		boolean dangerous = false;
		try {
			csvFile = new FileReader(file);
			CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
			String[] fila = null;
			csvReader.readNext();
			while ((fila = csvReader.readNext()) != null) {
				try {
					if(fila[3].equals("NO")) {
						dangerous = false;
					} else if(fila[3].equals("SI")) {
						dangerous = true;
					}
					
					if (fila[4].equals(null)) {
						throw new EmptyAttributeException();
					}
					pet.add(new Pet("NO ASIGNADO", Long.parseLong(fila[0]), fila[1], fila[2], dangerous, fila[4]));
				
				}catch (EmptyAttributeException e) {
					omit.add(new Pet("NO ASIGNADO", Long.parseLong(fila[0]), fila[1], fila[2], dangerous, "No Asignado"));
				}catch (NumberFormatException e) {
					omit.add(new Pet("NO ASIGNADO", 0000000000, fila[1], fila[2], dangerous, fila[4]));
				}
			}
			csvFile.close();
			csvReader.close();
			
		} catch(IOException e) {
				
		}catch (CsvValidationException e) {
				
	}
}

}
