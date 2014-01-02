package in4rows;

import in4rows.model.BasicVertex;
import in4rows.model.Disk;
import in4rows.model.GameReadable;
import in4rows.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class GridHelper {

	public static List<Vertex> possibleSquares(GameReadable g) {

		List<Vertex> res = new ArrayList<>(g.getWidth());
		Vertex first = null;

		for (int i = 0; i < g.getWidth(); i++) {

			first = firstInCol_ModeCol(g, i);

			if (first != null)
				res.add(first);
		}

		return res;
	}

	public static Vertex firstInGame_ModeCol(GameReadable g) {
		Vertex first = null;
		int i = 0;
		while (i < g.getWidth() && first == null) {
			first = firstInCol_ModeCol(g, i);
			i++;
		}
		return first;
	}

	public static Vertex firstInCol_ModeCol(GameReadable g, int col) {

		int cptRow = g.getHeight() - 1;
		while (cptRow >= 0 && g.getDisk(cptRow, col) == null)
			cptRow--;

		if (cptRow > -2 && cptRow < g.getHeight() - 1)
			return new BasicVertex(cptRow + 1, col);
		else
			return null;
	}

	public static boolean hasWon(GameReadable g, Vertex v) {

		return false;
	}

	public static int countUp(GameReadable g, Vertex v) {
		return countSequence(g, v, 1, 0);
	}

	public static int countDown(GameReadable g, Vertex v) {
		return countSequence(g, v, -1, 0);
	}

	public static int countRight(GameReadable g, Vertex v) {
		return countSequence(g, v, 0, 1);
	}

	public static int countLeft(GameReadable g, Vertex v) {
		return countSequence(g, v, 0, -1);
	}

	public static int countDiagRight(GameReadable g, Vertex v) {
		return countSequence(g, v, 1, 1);
	}

	public static int countDiagLeft(GameReadable g, Vertex v) {
		return countSequence(g, v, -1, -1);
	}

	private static int countSequence(GameReadable g, Vertex vStart,
			int coefRow, int coefCol) {
		Disk currDisk = g.getDisk(vStart.getRow(), vStart.getCol());
		int cptDisk = 0;
		int row = vStart.getRow() + coefRow;
		int col = vStart.getCol() + coefCol;
		boolean inBoard = row >= 0 && row < g.getHeight() && col >= 0
				&& col < g.getWidth();

		while (inBoard && g.getDisk(row, col)!= null && currDisk.equals(g.getDisk(row, col))) {
			cptDisk++;
			row += coefRow;
			col += coefCol;
			inBoard = row >= 0 && row < g.getHeight() && col >= 0
					&& col < g.getWidth();
		}

		return cptDisk;
	}

}
