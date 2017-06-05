package org.github.kilarukirankumar.presenter;

import org.github.kilarukirankumar.view.IView;

/**
 * BasePresenter base class. .
 */

public abstract class BasePresenter<V extends IView> {

    protected V view;

    public V getView() {
        return view;
    }

    public void setView(final V view) {
        if (view == null) {
            throw new IllegalArgumentException("Please set a View for this Presenter");
        }
        this.view = view;
    }

    /**
     * Method to initialize the presenter
     */
    public abstract void initialize();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment) onResume() method.
     */
    public abstract void resume();


    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment) onPause() method.
     */
    public abstract void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment) onDestroy()
     * method.
     */
    public abstract void destroy();
}
