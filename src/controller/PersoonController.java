package controller;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.klas.Klas;
import model.persoon.Student;
import server.Conversation;
import server.Handler;

public class PersoonController implements Handler{
	private PrIS informatieSysteem;
	
	public PersoonController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
	  if (conversation.getRequestedURI().startsWith("/persoon/status")) {
	  	zetStatus(conversation);
		}
	  
		if (conversation.getRequestedURI().startsWith("/persoon/info")) {
			gegevensOphalen(conversation);
		}
	}
	
	private void zetStatus(Conversation conversation) {
		
	}
	
	private void afmelden(Conversation conversation){
		
	}
	
	private void aanmelden(Conversation conversation){
		
	}
	
	//Print op dit moment alle klassen met namen uit in json
	private void gegevensOphalen(Conversation conversation){
		JsonArrayBuilder klassenArray = Json.createArrayBuilder();
		JsonObjectBuilder lJsonObjectBuilder = Json.createObjectBuilder();
		
		for (Klas k : informatieSysteem.getKlassen()) {
			JsonArrayBuilder leerlingenArray = Json.createArrayBuilder();
			
			for (Student s : k.getStudenten()){
				leerlingenArray.add(s.getVoornaam());
			}
			
			klassenArray.add(Json.createObjectBuilder().add(k.getKlasCode(), leerlingenArray));
		}
		
		lJsonObjectBuilder.add("klassen", klassenArray);

		String lJsonOut = lJsonObjectBuilder.build().toString();
		
		conversation.sendJSONMessage(lJsonOut);		
	}
}
