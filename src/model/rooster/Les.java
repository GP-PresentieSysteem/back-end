package model.rooster;
import java.time.LocalDate;

import model.klas.Klas;
import model.persoon.Docent;
import model.vak.Vak;

public class Les {
	private Vak huidigeVak;
	private Klas huidigeKlas;
	private Docent huidigeDocent;
	private Lokaal huidigeLokaal;
	private LocalDate beginTijd;
	private LocalDate eindTijd;
	
	Les(Vak hV, Klas hK, Docent hD, Lokaal hL){
		huidigeVak = hV;
		huidigeKlas = hK;
		huidigeDocent = hD;
		huidigeLokaal = hL;
	}

	public Vak getHuidigeVak() {
		return huidigeVak;
	}

	public void setHuidigeVak(Vak huidigeVak) {
		this.huidigeVak = huidigeVak;
	}

	public Klas getHuidigeKlas() {
		return huidigeKlas;
	}

	public void setHuidigeKlas(Klas huidigeKlas) {
		this.huidigeKlas = huidigeKlas;
	}

	public Docent getHuidigeDocent() {
		return huidigeDocent;
	}

	public void setHuidigeDocent(Docent huidigeDocent) {
		this.huidigeDocent = huidigeDocent;
	}

	public Lokaal getHuidigeLokaal() {
		return huidigeLokaal;
	}

	public void setHuidigeLokaal(Lokaal huidigeLokaal) {
		this.huidigeLokaal = huidigeLokaal;
	}

	public LocalDate getBeginTijd() {
		return beginTijd;
	}

	public void setBeginTijd(LocalDate beginTijd) {
		this.beginTijd = beginTijd;
	}

	public LocalDate getEindTijd() {
		return eindTijd;
	}

	public void setEindTijd(LocalDate eindTijd) {
		this.eindTijd = eindTijd;
	}
}
