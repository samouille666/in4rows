package in4rows.client.console.views;

import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.client.view.composite.CompositeView;
import in4rows.client.view.composite.IBoardView;

public class CompositeBoardView extends CompositeView implements IBoardView {
	
	private IBoardView board;
		
	public void setBoard(IGraphicalComponent board) {
		this.board.setBoard(board);
	}
	
	public void setBoardView(IBoardView v){
		addView(v);
		board = v;
	}
}
