package controller;

import model.PrIS;
import server.Conversation;
import server.Handler;

public class KlasController implements Handler{
	private PrIS informatieSysteem;
	
	public KlasController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {

	}
	
	private void ophalenKlas(Conversation conversation){
		
	}
}
