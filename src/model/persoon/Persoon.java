package model.persoon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Persoon {

	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private transient String wachtwoord;
	private String gebruikersnaam;
	private ArrayList<Status> statussen;

	public Persoon(String voornaam, String tussenvoegsel, String achternaam, String wachtwoord, String gebruikersnaam) {
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.wachtwoord = wachtwoord;
		this.gebruikersnaam = gebruikersnaam;
		this.statussen = new ArrayList<Status>();
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	private String getAchternaam() {
		return this.achternaam;
	}

	protected String getWachtwoord() {
		return this.wachtwoord;
	}

	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}
	
	public void ziekMelden(){
		statussen.add(new Status("Ziek"));
	}
	
	public void beterMelden(){
		for (int i = 0; i < statussen.size(); i ++) {
			if(statussen.get(i).getStatus().equals("Ziek")){
				statussen.remove(i);
			}
		}
	}
	
	public void nieuweStatus(String naam, LocalDate datum, String dagdeel){
		Status status = new Status(naam, datum, dagdeel);
		
		for (Status s : statussen) {
  		if(s.equals(status)) return;
		}
		
		statussen.add(status);
	}

	public String getVolledigeAchternaam() {
		String lVolledigeAchternaam="";
		if (this.tussenvoegsel != null && this.tussenvoegsel != "" && this.tussenvoegsel.length() > 0) {
			lVolledigeAchternaam += this.tussenvoegsel + " ";
		}
		lVolledigeAchternaam += this.getAchternaam();
		return lVolledigeAchternaam;
	}

	public boolean komtWachtwoordOvereen(String pWachtwoord) {
		boolean lStatus = false;
		if (this.getWachtwoord().equals(pWachtwoord)) {
			lStatus = true;
		}
		return lStatus;
	}
}
