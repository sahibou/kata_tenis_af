package com.doucoure.sahibou.kata.sg01.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.doucoure.sahibou.kata.sg01.dfa.GameDFA;
import com.doucoure.sahibou.kata.sg01.dfa.GameDFA.GameDfaEvent;
import com.doucoure.sahibou.kata.sg01.dfa.GameTieBreakDFA;
import com.doucoure.sahibou.kata.sg01.dfa.GameTieBreakDFA.GameTieBreakDFAEvent;
import com.doucoure.sahibou.kata.sg01.dfa.SetDFA;
import com.doucoure.sahibou.kata.sg01.dfa.SetDFA.SetDfaEvent;
import com.doucoure.sahibou.kata.sg01.dfa.SetState;

public class App {
    public static void main(String args[]) throws IOException {
    	
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

    	int P1Match=0;
    	int P2Match=0;
    	
    	String input="a";
    	String player1=null;
    	String player2=null;
    	System.out.println("What is the name of the first player");
    	player1 = br.readLine();
    	System.out.println("What is the name of the second player");
    	player2 = br.readLine();
    	
    	StringBuilder score=new StringBuilder();
    	
    	GameTieBreakDFA gameTieBreakAutomata = new GameTieBreakDFA();
    	  
    	SetState setState=null;
    	
    	while(P1Match!=3 && P2Match!=3){
    	
    		//SET
    		SetDFA setAutomata = new SetDFA();
    		while( ! setAutomata.getCurrentState().getStateId().equals("P1_WIN_SET")
    				&& ! setAutomata.getCurrentState().getStateId().equals("P2_WIN_SET")){
	    	
    			//GAME
    			GameDFA gameAutomata = new GameDFA();
		    	while(
		    			! gameAutomata.getCurrentState().getStateId().equals("P1W")
		    			&& ! gameAutomata.getCurrentState().getStateId().equals("P2W") ){
		    		input = br.readLine();
		    		if(input.equals("a"))
		    			gameAutomata.executeEvent(GameDfaEvent.P1scores);
		    		if(input.equals("b"))
		    			gameAutomata.executeEvent(GameDfaEvent.P2scores);
		    		System.out.println("Current game status: "+gameAutomata.getCurrentStatus());
		    	}
		    	
		    	if(gameAutomata.getCurrentState().getStateId().equals("P1W")){
		    		setAutomata.executeEvent(SetDfaEvent.P1scoresSet);
		    	}else if(gameAutomata.getCurrentState().getStateId().equals("P2W")){
		    		setAutomata.executeEvent(SetDfaEvent.P2scoresSet);
		    	}
//		    	System.out.println("Set ends with : "+setAutomata.getCurrentSetInfo());
    		}
    		
    		score.append(setAutomata.getCurrentSetInfo());
    		if(setAutomata.getCurrentState().getStateId().equals("P1_WIN_SET")){
    			P1Match++;
    		}else{
    			P2Match++;
    		}
    		System.out.println("Score :"+score);
    	}
    }
}
