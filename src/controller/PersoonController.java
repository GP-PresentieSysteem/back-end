package controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.PrIS;
import model.klas.Klas;
import model.persoon.Docent;
import model.persoon.Student;
import server.Conversation;
import server.Handler;

public class PersoonController implements Handler{
	private PrIS informatieSysteem;
	
	public PersoonController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
	  if (conversation.getRequestedURI().startsWith("/persoon/zetstatus")) {
	  	zetStatus(conversation);
		}
	  
		if (conversation.getRequestedURI().startsWith("/persoon/info")) {
			persoonInfo(conversation);
		}
		
		if (conversation.getRequestedURI().startsWith("/studenten/info")) {
			alleStudenten(conversation);
		}
		
		if (conversation.getRequestedURI().startsWith("/docenten/info")) {
			alleDocenten(conversation);
		}
	}
	
	private void zetStatus(Conversation conversation) {
		

	}
	
	private void persoonInfo(Conversation conversation){
		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		
		System.out.println(conversation.getRequestBodyAsString());
		
		try {
			JsonObject request = parser.parse(conversation.getRequestBodyAsString()).getAsJsonObject();
			
			if (request.get("gebruikersnaam") != null){
				
				if (informatieSysteem.getStudent(request.get("gebruikersnaam").getAsString()) != null){
					Student student = informatieSysteem.getStudent(request.get("gebruikersnaam").getAsString());
					
					conversation.sendJSONMessage(gson.toJson(student));
					
					return;
				}
				
				if (informatieSysteem.getDocent(request.get("gebruikersnaam").getAsString()) != null){
					Docent docent = informatieSysteem.getDocent(request.get("gebruikersnaam").getAsString());
					
					conversation.sendJSONMessage(gson.toJson(docent));
					
					return;
				}
				
				
			}
			
			if (request.get("studentNummer") != null){
				if (informatieSysteem.getStudent(request.get("naam").getAsInt()) != null){
					Student student = informatieSysteem.getStudent(request.get("naam").getAsInt());
					
					conversation.sendJSONMessage(gson.toJson(student));
					
					return;
				}
			}
    } catch (Exception ex) {
    	ex.printStackTrace();
    }
		
		conversation.sendJSONMessage("{'error':'Geen persoon gevonden'}");
	}
	
	private void alleStudenten(Conversation conversation){
		Gson gson = new Gson();
		String jsonOut = "";
		
		jsonOut = gson.toJson(informatieSysteem.getStudenten()); 
		
		conversation.sendJSONMessage(jsonOut);
	}
	
	private void alleDocenten(Conversation conversation){
		Gson gson = new Gson();
		String jsonOut = "";
		
		jsonOut = gson.toJson(informatieSysteem.getDocenten()); 
		
		conversation.sendJSONMessage(jsonOut);
	}
}
