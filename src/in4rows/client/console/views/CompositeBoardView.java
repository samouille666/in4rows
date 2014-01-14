package in4rows.client.console.views;

import in4rows.client.graphical.decorator.IGraphicalComponent;
import in4rows.client.view.abstractviews.AbstractTextView;
import in4rows.client.view.composite.CompositeView;
import in4rows.client.view.composite.IBoardView;

public class CompositeBoardView extends CompositeView implements
		IBoardScreenView {

	private IBoardView board;

	private AbstractTextView upperMsg;
	private AbstractTextView info;
	private AbstractTextView userMoveInputMsg;

	public void setBoard(IGraphicalComponent board) {
		this.board.setBoard(board);
	}

	public void setBoardView(IBoardView v) {
		addView(v);
		board = v;
	}

	@Override
	public AbstractTextView getInfoMsgView() {
		return info;
	}

	@Override
	public void setInfoMsgView(AbstractTextView v) {
		info = v;
	}

	@Override
	public AbstractTextView getInputMoveMsgView() {
		return userMoveInputMsg;
	}

	@Override
	public void setInputMoveMsgView(AbstractTextView v) {
		userMoveInputMsg = v;
	}

	@Override
	public AbstractTextView getUpperMsgView() {
		return upperMsg;
	}

	@Override
	public void setUpperMsgView(AbstractTextView v) {
		upperMsg = v;
	}
}
