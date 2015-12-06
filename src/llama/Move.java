package llama;

public class Move {
	private final int index;
	private final MoveType moveType;
	
	public Move (int index, MoveType moveType) {
		this.index = index;
		this.moveType = moveType;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public int getIndex() {
		return index;
	}
	
	public String toString() {
		return String.format("(%d, %s)", index, moveType.name());
	}
}
