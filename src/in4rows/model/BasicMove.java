package in4rows.model;

public class BasicMove implements Move {

	private Vertex v = null;

	public BasicMove(int row, int col) {
		v = new BasicVertex(row, col);
	}

	public BasicMove(Vertex v) {
		this(v.getRow(), v.getCol());

	}

	@Override
	public Vertex getVertex() {
		return v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v == null) ? 0 : v.hashCode());
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
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicMove [v=" + v + "]";
	}

}
