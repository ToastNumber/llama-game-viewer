package llama;

public enum LlamaPiece {
	L, O, R;
	
	public static LlamaPiece toLlama(String s) {
		switch (s.toUpperCase()) {
			case "L": return L;
			case "O": return O;
			case "R": return R;
			default : throw new IllegalArgumentException();
		}
	}
	
	public String toString() {
		if (this == L) return "L";
		else if (this == R) return "R";
		else return " ";
	}
}
