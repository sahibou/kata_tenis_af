package com.doucoure.sahibou.kata.sg01.dfa;

public class Transition {

	private String transID;
	private State targetState;
	
	
	public Transition(String transID, State targetState) {
		super();
		this.transID = transID;
		this.targetState = targetState;
	}


	public String getTransID() {
		return transID;
	}


	public void setTransID(String transID) {
		this.transID = transID;
	}


	public State getTargetState() {
		return targetState;
	}


	public void setTargetState(State targetState) {
		this.targetState = targetState;
	}
	
	
}
