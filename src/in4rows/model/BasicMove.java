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

}
