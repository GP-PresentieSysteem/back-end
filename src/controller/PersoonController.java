package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	//Print op dit moment het rooster uit in json
	private void gegevensOphalen(Conversation conversation){
		Gson gson = new Gson();
		String jsonOut = "";
		
		jsonOut = gson.toJson(informatieSysteem.getRooster()); 
		
		conversation.sendJSONMessage(jsonOut);		
	}
}
