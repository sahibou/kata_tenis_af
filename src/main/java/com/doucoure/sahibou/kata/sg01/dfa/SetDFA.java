package com.doucoure.sahibou.kata.sg01.dfa;

import java.util.HashMap;

public class SetDFA extends DFA {
 
	private int setScoreP1=0;
	private int setScoreP2=0;
	
	public SetDFA() {
		DFAStates = new HashMap<String, State>();
		
		State game = new SetState("GAME");
		State tbreak_game = new SetState("TBREAK_GAME");
		State p1_win_set = new SetState("P1_WIN_SET");
		State p2_win_set = new SetState("P2_WIN_SET");	
		
		DFAStates.put("GAME",game);
		DFAStates.put("TBREAK_GAME",tbreak_game);
		DFAStates.put("P1_WIN_SET",p1_win_set);		
		DFAStates.put("P2_WIN_SET",p2_win_set);
		
		currentState = game;
	}	
	
	public enum SetDfaEvent {
		P1scoresSet,
		P2scoresSet
	}


	public State executeEvent(SetDfaEvent event) {
		
		if(currentState.getStateId().equals("GAME")){			
			if(event==SetDfaEvent.P1scoresSet && setScoreP1 == 5 && setScoreP2 == 6){//Go to TIE BREAK due to P1 scoring the game
				setScoreP1++;
				currentState = DFAStates.get("TBREAK_GAME");
			}else if(event==SetDfaEvent.P2scoresSet && setScoreP2 == 5 && setScoreP1 == 6){//Go to TIE BREAK due to P2 scoring the game
				setScoreP2++;
				currentState = DFAStates.get("TBREAK_GAME");
			}else if(event==SetDfaEvent.P1scoresSet && setScoreP1 == 5 && setScoreP1 - setScoreP2 >= 2){//Win
				setScoreP1++;
				currentState = DFAStates.get("P1_WIN_SET");
			}else if(event==SetDfaEvent.P2scoresSet && setScoreP2 == 5 && setScoreP2 - setScoreP1 >= 2){
				setScoreP2++;
				currentState = DFAStates.get("P2_WIN_SET");
			}else if(event==SetDfaEvent.P1scoresSet){//Stay
				setScoreP1++;
			}else if(event==SetDfaEvent.P2scoresSet){
				setScoreP2++;
			}
				
		} else	if(currentState.getStateId().equals("TBREAK_GAME")){			
			if(event==SetDfaEvent.P1scoresSet && setScoreP1 - setScoreP2 == 1){//Win
				setScoreP1++;
				currentState = DFAStates.get("P1_WIN_SET");
			}else if(event==SetDfaEvent.P2scoresSet && setScoreP2 - setScoreP1 == 1){
				setScoreP2++;
				currentState = DFAStates.get("P2_WIN_SET");
			}else if(event==SetDfaEvent.P1scoresSet){//Stay
				setScoreP1++;
			}else if(event==SetDfaEvent.P2scoresSet){
				setScoreP2++;
			}
			
		}
		
		
		return currentState;
	}

	public String getCurrentSetInfo(){
		return "("+setScoreP1+"-"+setScoreP2+")";
	}
	
	public String getCurrentStatus() {
		String stat = "("+setScoreP1+"-"+setScoreP2+")"+getCurrentState().getStateId();
		if(getCurrentState().getStateId().equals("TBREAK_GAME"))
			stat = "Tie break";
		if(getCurrentState().getStateId().equals("P1_WIN_SET"))
			stat = "Player1 wins";
		if(getCurrentState().getStateId().equals("P2_WIN_SET"))
			stat = "Player2 wins";
		return "Set status: "+stat;
	}

}
