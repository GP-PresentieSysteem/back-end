package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.PrIS;
import server.Conversation;
import server.Handler;

public class KlasController implements Handler{
	private PrIS informatieSysteem;
	
	public KlasController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		 // data in backend moet naar frontend
		// klas/info geeft data weer van een klas
		// 
		//TODO: postRequest
		if (conversation.getRequestedURI().startsWith("/klas/info")) {
			ophalenKlas(conversation);
		}
		
//		if (conversation.getRequestedURI().startsWith("/klas/slb")) {
//			ophalenSlb(conversation);
//		}
		
		if (conversation.getRequestedURI().startsWith("/klassen/info")) {
			alleKlassenOphalen(conversation);
		}
		
		//opdracht toegewijzen aan les
			// controleer rooster
		 
  			// controleer bijbehorende studenten 
  		  // controleer 
        // controleer bijbehorende 
        // controleer huidige 
        // controleer huidige 
  		  // controleer huidige 
  		  // controleer huidige 
		// klas eruit halen
		// 
	}
	
//	private void ophalenSlb(Conversation conversation) {
//		Gson gson = new Gson();
//		String jsonOut = "";
//		
//		jsonOut = gson.toJson(informatieSysteem.getDocent("V1A")); //hardcoded een klas, als voorbeeld
//		
//		conversation.sendJSONMessage(jsonOut);		
//	}
	
	private void alleKlassenOphalen(Conversation conversation) {
		//TODO:getAlleKlasCodes: informatieSysteem.getKlas(klascode);
		Gson gson = new Gson();
		String jsonOut = "";
		
		jsonOut = gson.toJson(informatieSysteem.getKlassen()); //hardcoded een klas, als voorbeeld
		
		conversation.sendJSONMessage(jsonOut);		

	}
	
	private void ophalenKlas(Conversation conversation){
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
	  JsonObject request = parser.parse(conversation.getRequestBodyAsString()).getAsJsonObject();
	  System.out.println(request.get("klascode").getAsString());
		String jsonOut = "";
		System.out.println(conversation.getRequestBodyAsString());
		
		jsonOut = gson.toJson(informatieSysteem.getKlas("V1D")); 
	}
}
