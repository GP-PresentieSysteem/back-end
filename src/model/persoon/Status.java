package model.persoon;

import java.util.Calendar;

public class Status {
	private String naam;
	private Calendar van;
	private Calendar tot;
	
	public Status(String naam){
		this.naam = naam;
		
	}
	
	public Status (String naam, Calendar van, Calendar tot){
		this.naam = naam;
		this.van = van;
		this.tot = tot;
	}

	public String getStatus() {
		return naam;
	}
}
