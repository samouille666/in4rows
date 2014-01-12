package in4rows.client.view.abstractviews;

import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.client.view.composite.IBoardView;

public abstract class AbstractBoardView implements IBoardView {

	private IGraphicalComponent board;

	public AbstractBoardView() {
		this(null);
	}

	public AbstractBoardView(IGraphicalComponent board) {
		super();
		this.board = board;
	}

	@Override
	public void display() {
		board.draw();
	}

	@Override
	public void setBoard(IGraphicalComponent board) {
		this.board = board;
	}

}
