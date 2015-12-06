package llama;

import java.util.Observable;

public class StateModel extends Observable {
	private State state;
	
	public StateModel(State state) {
		this.state = state;
	}
	
	public boolean performMove(Move move) {
		boolean svar = state.performMove(move);
		
		setChanged();
		notifyObservers();
		
		return svar;
	}
	
	public void reset() {
		state.reset();
		
		setChanged();
		notifyObservers();
	}
	
	public String toString() {
		return state.toString();
	}
}
