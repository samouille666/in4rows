package in4rows.client.console.views;

import in4rows.client.view.abstractviews.AbstractTextView;
import in4rows.client.view.abstractviews.AbstractUserInputView;
import in4rows.client.view.composite.IView;

public interface IPlayerRegistrationView extends IView {

	public void setTitleOfScreen(AbstractTextView titleView);

	public AbstractTextView getTitleOfScreen();

	public void setSubTitleOfScreen(AbstractTextView subTitleView);

	public AbstractTextView getSubTitleOfScreen();

	public void setUserNameInputView(AbstractUserInputView userNameInputView);

	public AbstractUserInputView getUserNameInputView();

}
