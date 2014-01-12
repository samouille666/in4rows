package in4rows.client.view.abstractviews;

import in4rows.client.graphical.Board;
import in4rows.client.view.composite.IView;

public abstract class AbstractBoardView implements IView {

	private Board board;

	public AbstractBoardView() {

	}
	
	@Override
	public void display() {
		board.draw();
	}

	public AbstractBoardView(Board board) {
		this.board = board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
