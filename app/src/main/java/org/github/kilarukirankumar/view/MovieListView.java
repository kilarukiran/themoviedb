package org.github.kilarukirankumar.view;

import org.github.kilarukirankumar.model.videos.MoviesModel;

/**
 *
 */

public interface MovieListView extends IView {

    void showMovies(MoviesModel moviesModel);

    void showQueryError();

}
