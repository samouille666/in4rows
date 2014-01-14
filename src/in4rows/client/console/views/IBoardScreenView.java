package in4rows.client.console.views;

import in4rows.client.view.abstractviews.AbstractTextView;
import in4rows.client.view.composite.IBoardView;

public interface IBoardScreenView extends IBoardView {
	public AbstractTextView getUpperMsgView();

	public AbstractTextView getInfoMsgView();

	public AbstractTextView getInputMoveMsgView();

	public void setUpperMsgView(AbstractTextView v);

	public void setInfoMsgView(AbstractTextView v);

	public void setInputMoveMsgView(AbstractTextView v);
}
