package in4rows.client.console.views;

import in4rows.client.view.abstractviews.AbstractTextView;
import in4rows.client.view.abstractviews.AbstractUserInputView;
import in4rows.client.view.composite.CompositeView;

public class SimpleUserRegistrationView extends CompositeView implements
		IPlayerRegistrationView {

	private AbstractTextView title;
	private AbstractTextView subTitle;
	private AbstractUserInputView inputView;

	@Override
	public void setTitleOfScreen(AbstractTextView titleView) {
		title = titleView;
		addView(title);
	}

	@Override
	public AbstractTextView getTitleOfScreen() {
		return title;
	}

	@Override
	public void setSubTitleOfScreen(AbstractTextView subTitleView) {
		subTitle = subTitleView;
		addView(subTitle);
	}

	@Override
	public AbstractTextView getSubTitleOfScreen() {
		return subTitle;
	}

	@Override
	public void setUserNameInputView(AbstractUserInputView userNameInputView) {
		inputView = userNameInputView;
		addView(inputView);
	}

	@Override
	public AbstractUserInputView getUserNameInputView() {
		return inputView;
	}

}
