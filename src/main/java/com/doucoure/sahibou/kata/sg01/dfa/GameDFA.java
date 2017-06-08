package com.doucoure.sahibou.kata.sg01.dfa;

import java.util.HashMap;

public class GameDFA extends DFA {
 
	private String[] scoreValue = new String[]{"0", "15", "30", "40", "WON"};
	private int scoreP1=0;
	private int scoreP2=0;
	
	public GameDFA() {
		DFAStates = new HashMap<String, State>();
		
		State nDeuce = new SetState("NDEUCE");
		State deuceP1adv = new SetState("DEUCE_P1ADV");
		State deuceP2adv = new SetState("DEUCE_P2ADV");
		State p1w = new SetState("P1W");	
		State p2w = new SetState("P2W");
		
		DFAStates.put("nDeuce",nDeuce);
		DFAStates.put("deuceP1adv",deuceP1adv);
		DFAStates.put("deuceP2adv",deuceP2adv);		
		DFAStates.put("p1w",p1w);
		DFAStates.put("p2w",p2w);
		
		currentState = nDeuce;
	}	
	
	public enum GameDfaEvent {
		P1scores,
		P2scores
	}


	public State executeEvent(GameDfaEvent event) {
		
		if(currentState.getStateId().equals("NDEUCE")){			
			if(event==GameDfaEvent.P1scores && scoreValue[scoreP1].equals("30") && scoreValue[scoreP2].equals("40")){//DEUCE advantage for P1
				scoreP1++;
				currentState = DFAStates.get("deuceP1adv");
			}else if(event==GameDfaEvent.P2scores && scoreValue[scoreP2].equals("30") && scoreValue[scoreP1].equals("40")){//DEUCE advantage for P2
				scoreP2++;
				currentState = DFAStates.get("deuceP2adv");
			}else if(event==GameDfaEvent.P1scores && ! scoreValue[scoreP1].equals("40"))//P1 scores and doesnt win
				scoreP1++;			
			else if(event==GameDfaEvent.P2scores && ! scoreValue[scoreP2].equals("40"))//P2 scores and doesnt win
				scoreP2++;
			else if(event==GameDfaEvent.P1scores && scoreValue[scoreP1].equals("40")){//P1 Wins without deuce
				scoreP1++;
				currentState = DFAStates.get("p1w");
			}else if(event==GameDfaEvent.P2scores && scoreValue[scoreP2].equals("40")){//P2 Wins without deuce
				scoreP2++;
				currentState = DFAStates.get("p2w");
			}
				
		} else	if(currentState.getStateId().equals("DEUCE_P1ADV")){			
			if(event==GameDfaEvent.P1scores){//P1 Wins with deuce
				scoreP1++;
				currentState = DFAStates.get("p1w");
			}else if(event==GameDfaEvent.P2scores)//P2 gets advantage
				currentState = DFAStates.get("deuceP2adv");
			
		} else if(currentState.getStateId().equals("DEUCE_P2ADV")){			
			if(event==GameDfaEvent.P2scores){//P2 Wins with deuce
				scoreP2++;
				currentState = DFAStates.get("p2w");
			}else if(event==GameDfaEvent.P1scores)//P1 gets advantage
				currentState = DFAStates.get("deuceP1adv");
			
		}
		
		
		return currentState;
	}

	public String getCurrentStatus() {
		String stat = "In progress";
		if(getCurrentState().getStateId().equals("NDEUCE"))
			stat = scoreValue[scoreP1]+"-"+scoreValue[scoreP2];
		if(getCurrentState().getStateId().equals("DEUCE_P1ADV") || getCurrentState().getStateId().equals("DEUCE_P2ADV") )
			stat = "deuce";
		if(getCurrentState().getStateId().equals("P1W"))
			stat = "Player1 wins";
		if(getCurrentState().getStateId().equals("P2W"))
			stat = "Player2 wins";
		return stat ;
	}

}
