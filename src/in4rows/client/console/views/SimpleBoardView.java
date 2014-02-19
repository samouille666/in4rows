package in4rows.client.console.views;

import in4rows.client.graphical.board.Board;
import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.client.view.abstractviews.AbstractBoardView;

public class SimpleBoardView extends AbstractBoardView {

	public SimpleBoardView() {
	}

	public SimpleBoardView(Board board) {
		super(board);
	}
	
	@Override
	public void setBoard(IGraphicalComponent board) {
		super.setBoard(board);
	}

}
