package model.vak;

public class Vak {
	private String code;
	private String naam;
	
	Vak(String cd, String nm){
		code = cd;
		naam = nm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
}
