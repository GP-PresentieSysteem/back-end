package model.persoon;

public class Status {
	private String naam;
	
	Status(String nm){
		naam = nm;
		
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
}
