package co.edu.unbosque.model.persistence;

import java.io.Serializable;

public class Pet implements Serializable {
	private String id;
	private long microchip;
	private String species;
	private String sex;
	private boolean potentDangerous;
	private String neighborhood;
	
	public Pet(String id, long microchip, String species, String sex, boolean potenrDangerous, String neighborhood) {
		this.id =id;
		this.microchip =microchip;
		this.species = species;
		this.sex=sex;
		this.potentDangerous=potenrDangerous;
		this.neighborhood=neighborhood;
	}
	
	public String toString() {
		return "Id: "+this.id+"\nMicrochip: "+this.microchip+"\nSpecies: "+this.species+"\nSex: "+this.sex+"\nPotential Dangerous: "+this.potentDangerous+"\nNeighborhood: "+this.neighborhood;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getMicrochip() {
		return microchip;
	}

	public void setMicrochip(long microchip) {
		this.microchip = microchip;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isPotentDangerous() {
		return potentDangerous;
	}

	public void setPotentDangerous(boolean potentDangerous) {
		this.potentDangerous = potentDangerous;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	

}
