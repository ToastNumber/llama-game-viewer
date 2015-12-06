package llama;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
	private final LlamaPiece[] initialState;
	private List<LlamaPiece> state = new ArrayList<>();

	public State(LlamaPiece[] state) {
		this.initialState = Arrays.copyOf(state, state.length);
		reset();
	}

	public State(List<LlamaPiece> state) {
		initialState = new LlamaPiece[state.size()];
		for (int i = 0; i < state.size(); ++i) {
			initialState[i] = state.get(i);
		}

		reset();
	}

	public void reset() {
		state.clear();

		for (LlamaPiece llama : initialState) {
			state.add(llama);
		}
	}

	public String toString() {
		String svar = "";

		for (int i = 0; i < state.size(); ++i) {
			svar += state.get(i);
		}

		return svar;
	}

	/**
	 * @param move
	 *            the move to be performed
	 * @return true if the move could be performed; false otherwise
	 */
	public boolean performMove(Move move) {
		boolean svar = true;

		if (state.size() == 0) {
			svar = false;
		} else {
			// Pad the state
			state.add(0, LlamaPiece.O);
			state.add(0, LlamaPiece.O);
			state.add(LlamaPiece.O);
			state.add(LlamaPiece.O);

			// Add 2 onto the index to account for the padding
			int index = move.getIndex() + 2;
			MoveType moveType = move.getMoveType();

			// These checks ensure that the llama at the given index is of the
			// correct type
			if (index < 0 || index >= state.size()) svar = false;
			else if (moveType == MoveType.MR || moveType == MoveType.JR) {
				if (state.get(index) != LlamaPiece.R) svar = false;
			} else {
				if (state.get(index) != LlamaPiece.L) svar = false;
			}

			if (svar) {
				if (moveType == MoveType.MR) {
					// If the next piece is free
					if (state.get(index + 1) == LlamaPiece.O) {
						state.set(index, LlamaPiece.O);
						state.set(index + 1, LlamaPiece.R);
					} else svar = false;
				} else if (moveType == MoveType.ML) {
					// If the space to the left is free
					if (state.get(index - 1) == LlamaPiece.O) {
						state.set(index, LlamaPiece.O);
						state.set(index - 1, LlamaPiece.L);
					} else svar = false;
				} else if (moveType == MoveType.JR) {
					if (state.get(index + 1) == LlamaPiece.L && state.get(index + 2) == LlamaPiece.O) {
						state.set(index, LlamaPiece.O);
						state.set(index + 2, LlamaPiece.R);
					} else svar = false;
				} else if (moveType == MoveType.JL) {
					if (state.get(index - 1) == LlamaPiece.R && state.get(index - 2) == LlamaPiece.O) {
						state.set(index, LlamaPiece.O);
						state.set(index - 2, LlamaPiece.L);
					} else svar = false;
				} else throw new IllegalArgumentException();
			}
		}

		normalise();
		return svar;
	}

	public void normalise() {
		while (state.size() > 0 && state.get(0) == LlamaPiece.O) {
			state.remove(0);
		}

		while (state.size() > 0 && state.get(state.size() - 1) == LlamaPiece.O) {
			state.remove(state.size() - 1);
		}
	}
}
