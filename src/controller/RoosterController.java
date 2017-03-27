package controller;

import model.PrIS;
import server.Conversation;
import server.Handler;

public class RoosterController implements Handler{
	private PrIS informatieSysteem;
	
	public RoosterController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
	  if (conversation.getRequestedURI().startsWith("/rooster")) {
	  	ophalenRooster(conversation);
		}
	  
	  if (conversation.getRequestedURI().startsWith("/rooster/les")){
	  	ophalenLes(conversation);
	  }
	}
	
	private void ophalenRooster(Conversation conversation) {
		
	}
	
	private void ophalenLes(Conversation conversation){
		
	}

}
