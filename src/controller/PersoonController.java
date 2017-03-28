package controller;

import model.PrIS;
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
	}
	
	private void zetStatus(Conversation conversation) {
		
	}
	
	private void afmelden(Conversation conversation){
		
	}
	
	private void aanmelden(Conversation conversation){
		
	}
	
	private void gegevensOphalen(Conversation conversation){
		
	}
}
