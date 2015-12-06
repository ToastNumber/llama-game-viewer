package llama;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class LlamaGui {

	/**
	 * @param s
	 *            of the form ["L"; "O"; "R"; ...]
	 * @return
	 */
	public static State stringToState(String s) {
		// Strip the leading and trailing square brackets
		s = s.substring(1, s.length() - 1);
		String[] parts = s.split("; ");

		List<LlamaPiece> llamas = new ArrayList<>();
		for (int i = 0; i < parts.length; ++i) {
			llamas.add(LlamaPiece.toLlama(parts[i]));
		}

		return new State(llamas);
	}

	/**
	 * @param s
	 *            in the form [(1, "MR"); ...].
	 * @return
	 */
	public static MoveSequence stringToMoveSequence(String s) {
		// Strip the leading and trailing square brackets
		s = s.substring(1, s.length() - 1);

		List<Move> moves = new ArrayList<>();

		String[] parts = s.split("; ");
		for (int i = 0; i < parts.length; ++i) {
			// Strip the leading and trailing round brackets
			String part = parts[i].substring(1, parts[i].length() - 1);
			String[] indexWithMove = part.split(", ");
			moves.add(new Move(Integer.valueOf(indexWithMove[0]), MoveType.toMove(indexWithMove[1])));
		}

		return new MoveSequence(moves);
	}

	public static void main(String[] args) {
		try {
			State state = stringToState(args[0]);
			MoveSequence moveSequence = stringToMoveSequence(args[1]);

			JFrame frame = new JFrame("Llama Game Viewer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			/*
			 * State state = new State(Arrays.asList(LlamaPiece.R, LlamaPiece.R,
			 * LlamaPiece.R, LlamaPiece.O, LlamaPiece.L, LlamaPiece.L));
			 * MoveSequence moveSequence = new MoveSequence( Arrays.asList( new
			 * Move(2, MoveType.MR), new Move(4, MoveType.JL), new Move(3,
			 * MoveType.MR), new Move(1, MoveType.JL), new Move(4,
			 * MoveType.MR)));
			 */

			StatePanel panel = new StatePanel(state, moveSequence);
			frame.add(panel);

			frame.setSize(1500, 150);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Usage: \"[L; O; R; ...]\" \"[(2, MR), (5, JL) ...]\"");
		}

	}
}
