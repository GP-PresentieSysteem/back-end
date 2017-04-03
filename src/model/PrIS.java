package model;

import java.util.ArrayList;

import model.klas.Klas;
import model.persoon.Docent;
import model.persoon.Persoon;
import model.persoon.Status;
import model.persoon.Student;
import model.rooster.Les;
import model.rooster.Lokaal;
import model.rooster.Rooster;
import model.vak.Vak;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class PrIS {
	private ArrayList<Docent> deDocenten;
	private ArrayList<Student> deStudenten;
	private ArrayList<Klas> deKlassen;
	private ArrayList<Lokaal> deLokalen;
	private ArrayList<Vak> deVakken;
	private ArrayList<Les> deLessen;
	private Rooster hetRooster;

	/**
	 * De constructor maakt een set met standaard-data aan. Deze data moet nog
	 * uitgebreidt worden met rooster gegevens die uit een bestand worden
	 * ingelezen, maar dat is geen onderdeel van deze demo-applicatie!
	 * 
	 * De klasse PrIS (PresentieInformatieSysteem) heeft nu een meervoudige
	 * associatie met de klassen Docent, Student, Vakken en Klassen Uiteraard kan
	 * dit nog veel verder uitgebreid en aangepast worden!
	 * 
	 * De klasse fungeert min of meer als ingangspunt voor het domeinmodel. Op dit
	 * moment zijn de volgende methoden aanroepbaar:
	 * 
	 * String login(String gebruikersnaam, String wachtwoord) Docent
	 * getDocent(String gebruikersnaam) Student getStudent(String gebruikersnaam)
	 * ArrayList<Student> getStudentenVanKlas(String klasCode)
	 * 
	 * Methode login geeft de rol van de gebruiker die probeert in te loggen, dat
	 * kan 'student', 'docent' of 'undefined' zijn! Die informatie kan gebruikt
	 * worden om in de Polymer-GUI te bepalen wat het volgende scherm is dat
	 * getoond moet worden.
	 * 
	 */
	public PrIS() {
		deDocenten = new ArrayList<Docent>();
		deStudenten = new ArrayList<Student>();
		deKlassen = new ArrayList<Klas>();
		deLokalen = new ArrayList<Lokaal>();
		deVakken = new ArrayList<Vak>();
		deLessen = new ArrayList<Les>();
		
		// Inladen klassen
		vulKlassen(deKlassen);

		// Inladen studenten in klassen
		vulStudenten(deStudenten, deKlassen);

		// Inladen docenten
		vulDocenten(deDocenten);

		// Inladen lokalen
		vulLokalen(deLokalen);

		// Inladen vakken
		vulVakken(deVakken);

		// Inladen lessen
		vulLessen(deLessen);

		hetRooster = new Rooster(deLessen);

	} // Einde Pris constructor

	// deze method is static onderdeel van PrIS omdat hij als hulp methode
	// in veel controllers gebruikt wordt
	// een standaardDatumString heeft formaat YYYY-MM-DD
	public static Calendar standaardDatumStringToCal(String pStadaardDatumString) {
		Calendar lCal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			lCal.setTime(sdf.parse(pStadaardDatumString));
		} catch (ParseException e) {
			e.printStackTrace();
			lCal = null;
		}
		return lCal;
	}

	// deze method is static onderdeel van PrIS omdat hij als hulp methode
	// in veel controllers gebruikt wordt
	// een standaardDatumString heeft formaat YYYY-MM-DD
	public static String calToStandaardDatumString(Calendar pCalendar) {
		int lJaar = pCalendar.get(Calendar.YEAR);
		int lMaand = pCalendar.get(Calendar.MONTH) + 1;
		int lDag = pCalendar.get(Calendar.DAY_OF_MONTH);

		String lMaandStr = Integer.toString(lMaand);
		if (lMaandStr.length() == 1) {
			lMaandStr = "0" + lMaandStr;
		}
		String lDagStr = Integer.toString(lDag);
		if (lDagStr.length() == 1) {
			lDagStr = "0" + lDagStr;
		}
		String lString = Integer.toString(lJaar) + "-" + lMaandStr + "-" + lDagStr;
		return lString;
	}

	public Docent getDocent(String gebruikersnaam) {
		Docent resultaat = null;

		for (Docent d : deDocenten) {
			if (d.getGebruikersnaam().equals(gebruikersnaam)) {
				resultaat = d;
				break;
			}
		}

		return resultaat;
	}

	
	//public Docent getSlber(String klascode) { //TBA
		

//		for(Klas k : deKlassen) {
//			if()
//		}
//	}

	public Klas getKlasVanStudent(Student pStudent) {
		// used
		for (Klas lKlas : deKlassen) {
			if (lKlas.bevatStudent(pStudent)) {
				return (lKlas);
			}
		}
		return null;
	}

	public Klas getKlas(String klasCode) {
		for (Klas k : deKlassen) {
			if (k.getKlasCode().equals(klasCode)) {
				return (k);
			}
		}

		return null;
	}

	public ArrayList<Klas> getKlassen() {
		return deKlassen;
	}

	public Rooster getRoosterDocent(String gebruikersNaam) {
		ArrayList<Les> docentLessen = new ArrayList<Les>();
		
		for (Les les : deLessen) {
			if (les.getHuidigeDocent().getGebruikersnaam().equals(gebruikersNaam)) {
				docentLessen.add(les);
				
			}

		}
		Rooster roosterDocent = new Rooster(docentLessen);
		
		return roosterDocent;
	}

	public Rooster getRoosterKlas(String klasCode) {
		ArrayList<Les> klasLessen = new ArrayList<Les>();

		for (Les les : deLessen) {
			if (les.getHuidigeKlas().getKlasCode().equals(klasCode)) {
				klasLessen.add(les);
			}
		}
		Rooster roosterKlas = new Rooster(klasLessen);

		return roosterKlas;
	}

	public Les getLes(Calendar beginTijd, Calendar eindTijd, String klasCode, String vakCode) {

		for (Les les : deLessen) {
			if (les.getBeginTijd().equals(beginTijd) && les.getEindTijd().equals(eindTijd)
					&& les.getHuidigeKlas().getKlasCode().equals(klasCode) && les.getHuidigeVak().getCode().equals(vakCode)) {
				return les;
			}
		}
		return null;
	}

	public ArrayList<Les> getRoosterStudent(Student gebruikersnaam) {
		ArrayList<Les> roosterKlas = new ArrayList<Les>();

		for (Les les : deLessen) {
			if (les.getHuidigeKlas().bevatStudent(gebruikersnaam)) {
				roosterKlas.add(les);
			}
		}
		return roosterKlas;
	}

	public Rooster getRooster() {
		return hetRooster;
	}

	public Student getStudent(String pGebruikersnaam) {
		// used
		Student lGevondenStudent = null;

		for (Student lStudent : deStudenten) {
			if (lStudent.getGebruikersnaam().equals(pGebruikersnaam)) {
				lGevondenStudent = lStudent;
				break;
			}
		}

		return lGevondenStudent;
	}

	public Student getStudent(int pStudentNummer) {
		// used
		Student lGevondenStudent = null;

		for (Student lStudent : deStudenten) {
			if (lStudent.getStudentNummer() == (pStudentNummer)) {
				lGevondenStudent = lStudent;
				break;
			}
		}

		return lGevondenStudent;
	}

	public Vak getVak(String naam) {
		for (Vak v : deVakken) {
			if (v.getNaam().equals(naam)) {
				return v;
			}
		}

		return null;
	}

	public Lokaal getLokaal(String naam) {
		for (Lokaal l : deLokalen) {
			if (l.getNaam().equals(naam)) {
				return l;
			}
		}

		return null;
	}
	
	public ArrayList<Student> getStudenten(){
		return deStudenten;
	}
	
	public ArrayList<Docent> getDocenten(){
		return deDocenten;
	}

	public String login(String gebruikersnaam, String wachtwoord) {
		for (Docent d : deDocenten) {
			if (d.getGebruikersnaam().equals(gebruikersnaam)) {
				if (d.komtWachtwoordOvereen(wachtwoord)) {
					return "docent";
				}
			}
		}

		for (Student s : deStudenten) {
			if (s.getGebruikersnaam().equals(gebruikersnaam)) {
				if (s.komtWachtwoordOvereen(wachtwoord)) {
					return "student";
				}
			}
		}

		return "undefined";
	}

	private void vulDocenten(ArrayList<Docent> pDocenten) {
		String csvFile = "././CSV/docenten.csv";

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] element = line.split(cvsSplitBy);
				String gebruikersnaam = element[0].toLowerCase();
				String voornaam = element[1];
				String tussenvoegsel = element[2];
				String achternaam = element[3];
				
				//deStatussen.get(0) = standaard aanwezig
				pDocenten.add(new Docent(voornaam, tussenvoegsel, achternaam, "geheim", gebruikersnaam, 1));
				
				//System.out.println(gebruikersnaam);
		
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void vulKlassen(ArrayList<Klas> pKlassen) {
		// TICT-SIE-VIA is de klascode die ook in de rooster file voorkomt
		// V1A is de naam van de klas die ook als file naam voor de studenten van
		// die klas wordt gebruikt
		Klas k1 = new Klas("TICT-SIE-V1A", "V1A");
		Klas k2 = new Klas("TICT-SIE-V1B", "V1B");
		Klas k3 = new Klas("TICT-SIE-V1C", "V1C");
		Klas k4 = new Klas("TICT-SIE-V1D", "V1D");
		Klas k5 = new Klas("TICT-SIE-V1E", "V1E");
		Klas k6 = new Klas("TICT-SIE-V1F", "V1F");

		pKlassen.add(k1);
		pKlassen.add(k2);
		pKlassen.add(k3);
		pKlassen.add(k4);
		pKlassen.add(k5);
		pKlassen.add(k6);
	}

	private void vulStudenten(ArrayList<Student> pStudenten, ArrayList<Klas> pKlassen) {
		Student lStudent;
		for (Klas k : pKlassen) {
			String csvFile = "././CSV/" + k.getNaam() + ".csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";

			try {

				br = new BufferedReader(new FileReader(csvFile));

				while ((line = br.readLine()) != null) {

					// use comma as separator
					String[] element = line.split(cvsSplitBy);
					String gebruikersnaam = (element[3] + "." + element[2] + element[1] + "@student.hu.nl").toLowerCase();
					// verwijder spaties tussen dubbele voornamen en tussen bv van der
					gebruikersnaam = gebruikersnaam.replace(" ", "");
					String lStudentNrString = element[0];
					int lStudentNr = Integer.parseInt(lStudentNrString);
					
					//deStatussen.get(0) = standaard aanwezig
					lStudent = new Student(element[3], element[2], element[1], "geheim", gebruikersnaam, lStudentNr, k.getNaam()); //Volgorde 3-2-1 = voornaam, tussenvoegsel en achternaam

					pStudenten.add(lStudent);
					k.voegStudentToe(lStudent);

					// System.out.println(gebruikersnaam);

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private void vulLokalen(ArrayList<Lokaal> pLokalen) {
		String csvFile = "././CSV/rooster.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] element = line.split(cvsSplitBy);
				String naam = element[5];

				if (getLokaal(naam) == null)
					pLokalen.add(new Lokaal(naam));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void vulVakken(ArrayList<Vak> pVakken) {
		String csvFile = "././CSV/vakken.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] element = line.split(cvsSplitBy);
				String code = element[0];
				String naam = element[1];
				pVakken.add(new Vak(code, naam));

				// System.out.println(gebruikersnaam);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void vulLessen(ArrayList<Les> pLessen) {
		String csvFile = "././CSV/rooster.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] element = line.split(cvsSplitBy);
				String dag = element[0];
				String beginTijd = element[1];
				String eindTijd = element[2];
				String vakNaam = element[3];
				String docentGebruikersnaam = element[4];
				String lokaalCode = element[5];
				String klasNaam = element[6];

				Calendar beginDatum = Calendar.getInstance();
				Calendar eindDatum = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy hh:mm", Locale.GERMANY);
				beginDatum.setTime(sdf.parse(dag + " " + beginTijd));
				eindDatum.setTime(sdf.parse(dag + " " + eindTijd));

				pLessen.add(new Les(getVak(vakNaam), getKlas(klasNaam), getDocent(docentGebruikersnaam), getLokaal(lokaalCode),
						beginDatum, eindDatum));

				// System.out.println(gebruikersnaam);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
