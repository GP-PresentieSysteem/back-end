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
		
		JsonObject request = parser.parse(conversation.getRequestBodyAsString()).getAsJsonObject();
		
		//request.get("naam").getAsString();
		Student student = informatieSysteem.getStudent(request.get("naam").getAsString());
		
		if (student != null) {
			conversation.sendJSONMessage(gson.toJson(student));
			return;
		}

		String jsonOut = "";
		
		conversation.sendJSONMessage(request.get("naam").getAsString());
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
