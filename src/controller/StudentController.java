package controller;

import model.PrIS;
import server.Conversation;
import server.Handler;

public class StudentController implements Handler{
	private PrIS informatieSysteem;
	
	public StudentController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
	  if (conversation.getRequestedURI().startsWith("/student/status")) {
	  	zetStatus(conversation);
		}
	}
	
	private void zetStatus(Conversation conversation) {
		
	}

}
