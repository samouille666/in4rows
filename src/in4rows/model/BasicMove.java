package in4rows.model;

public class BasicMove implements Move {

	private int col = 0;

	public BasicMove(int col) {
		this.col = col;
	}

	public BasicMove(Vertex v) {
		this(v.getCol());
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicMove other = (BasicMove) obj;
		if (col != other.col)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicMove [col=" + col + "]";
	}

}
