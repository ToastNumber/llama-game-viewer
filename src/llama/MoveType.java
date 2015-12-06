package llama;

public enum MoveType {
	MR, JR, ML, JL;
	
	public static MoveType toMove(String s) {
		switch (s.toUpperCase()) {
			case "MR": return MR;
			case "JR": return JR;
			case "ML": return ML;
			case "JL": return JL;
			default : throw new IllegalArgumentException();
		}
	}
}
