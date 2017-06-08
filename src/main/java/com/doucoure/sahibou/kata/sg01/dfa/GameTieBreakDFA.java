package com.doucoure.sahibou.kata.sg01.dfa;

import java.util.HashMap;

public class GameTieBreakDFA extends DFA {
 
	private int scoreP1=0;
	private int scoreP2=0;
	
	public GameTieBreakDFA() {
		DFAStates = new HashMap<String, State>();
		
		State nDeuce = new SetState("NDEUCE");
		State deuce = new SetState("DEUCE");
		State p1w = new SetState("P1W");	
		State p2w = new SetState("P2W");
		
		DFAStates.put("nDeuce",nDeuce);
		DFAStates.put("deuce",deuce);
		DFAStates.put("p1w",p1w);
		DFAStates.put("p2w",p2w);
		
		currentState = nDeuce;
	}	
	
	public enum GameTieBreakDFAEvent {
		P1scores,
		P2scores
	}


	public State executeEvent(GameTieBreakDFAEvent event) {
		
		if(currentState.getStateId().equals("NDEUCE")){			
			if(event==GameTieBreakDFAEvent.P1scores && scoreP1 == 6){//P1 Wins without deuce
				scoreP1++;
				currentState = DFAStates.get("p1w");
			}else if(event==GameTieBreakDFAEvent.P2scores && scoreP2==6){//P2 Wins without deuce
				scoreP2++;
				currentState = DFAStates.get("p2w");
			}else if(event==GameTieBreakDFAEvent.P1scores && scoreP1 == 5 && scoreP2== 6){//DEUCE advantage for P1
				scoreP1++;
				currentState = DFAStates.get("deuce");
			}else if(event==GameTieBreakDFAEvent.P2scores && scoreP2 == 5 && scoreP1 == 6){//DEUCE advantage for P2
				scoreP2++;
				currentState = DFAStates.get("deuce");
			}else if(event==GameTieBreakDFAEvent.P1scores && scoreP1 < 6)//P1 scores and doesnt win
				scoreP1++;			
			else if(event==GameTieBreakDFAEvent.P2scores  && scoreP2 < 6 )//P2 scores and doesnt win
				scoreP2++;
			
				
		} else	if(currentState.getStateId().equals("DEUCE")){			
			if(event==GameTieBreakDFAEvent.P1scores && scoreP1 == scoreP2 + 1){//P1 Wins with deuce - Marque avec un point d avance
				scoreP1++;
				currentState = DFAStates.get("p1w");
			}else if(event==GameTieBreakDFAEvent.P2scores && scoreP2 == scoreP1 + 1){//P2 Wins with deuce - Marque avec un point d avance
				scoreP2++;
				currentState = DFAStates.get("p2w");
			}else if(event==GameTieBreakDFAEvent.P1scores){//P1 takes advantage
				scoreP1++;
			}else if(event==GameTieBreakDFAEvent.P2scores){//P2 takes advantage
				scoreP2++;
			}
			
		}
		
		
		return currentState;
	}

	public String getCurrentStatus() {
		String stat = "In progress";
		if(getCurrentState().getStateId().equals("NDEUCE"))
			stat = scoreP1+"-"+scoreP2;
		if(getCurrentState().getStateId().equals("DEUCE"))
			stat = "deuce";
		if(getCurrentState().getStateId().equals("P1W"))
			stat = "Player1 wins";
		if(getCurrentState().getStateId().equals("P2W"))
			stat = "Player2 wins";
		return "Current Game status: "+stat;
	}

}
