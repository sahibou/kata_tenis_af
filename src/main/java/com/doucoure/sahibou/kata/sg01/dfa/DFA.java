package com.doucoure.sahibou.kata.sg01.dfa;

import java.util.Map;

//Deterministic finite automata
public abstract class DFA {

	protected Map<String,State> DFAStates;
	protected State currentState;

	public State getCurrentState() {
		return currentState;
	}
	
	
	
}
