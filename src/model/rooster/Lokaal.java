package model.rooster;

public class Lokaal {
	private String naam;
	private String code;

	Lokaal(String nm, String cd){
		naam = nm;
		code = cd;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
