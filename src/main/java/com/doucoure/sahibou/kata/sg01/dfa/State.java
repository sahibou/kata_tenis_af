package com.doucoure.sahibou.kata.sg01.dfa;

import java.util.Map;

public abstract class State {
	
	protected String stateId;
	
	public State(String stateId) {
		super();
		this.stateId = stateId;
	}

	
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	

}
