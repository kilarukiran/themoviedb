package org.github.kilarukirankumar.view;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Base view for all the views.
 */

public interface IView {
    Context getContext();

    void showEmptyView();

    void showLoading();

    void hideLoading();

    void showErrorMessage(@NonNull String message);
}
