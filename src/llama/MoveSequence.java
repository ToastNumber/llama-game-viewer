package llama;

import java.util.List;
import java.util.Observable;

public class MoveSequence extends Observable {
	private List<Move> moves;
	private int index;

	public MoveSequence(List<Move> moves) {
		this.moves = moves;
	}
	
	public String toString() {
		String svar = "<html>";
		
		for (int i = 0; i < moves.size(); ++i) {
			if (i == index) svar += ">";
			svar += moves.get(i);
			svar += "  ";
			
			if (i > 0 && i % 20 == 0) svar += "<br>";
		}
		
		return svar + "</html>";
	}

	/**
	 * @return the element at the current position. Return null if past the end
	 */
	public Move get() {
		if (index >= moves.size()) return null;
		else return moves.get(index);
	}

	/**
	 * Moves the current index fowards
	 */
	public void forwards() {
		++index;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Moves the current index backwards
	 */
	public void backwards() {
		--index;
		setChanged();
		notifyObservers();
	}
	
	public void reset() {
		index = 0;
		setChanged();
		notifyObservers();
	}

	public boolean atEnd() {
		return index >= moves.size();
	}

	public boolean atStart() {
		return index == 0;
	}
	
}
