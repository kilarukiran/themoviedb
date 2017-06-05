package org.github.kilarukirankumar.presenter;

import android.support.annotation.NonNull;

import org.github.kilarukirankumar.interactor.Interactor;
import org.github.kilarukirankumar.interactor.Movies.GetMoviesUserCase;
import org.github.kilarukirankumar.model.mapper.videos.MoviesModelMapper;
import org.github.kilarukirankumar.model.movies.MoviesBo;
import org.github.kilarukirankumar.model.videos.MoviesModel;
import org.github.kilarukirankumar.util.ErrorUtils;
import org.github.kilarukirankumar.view.MovieListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 *
 */

public class MovieListPresenter extends BasePresenter<MovieListView> {

    private GetMoviesUserCase mGetMoviesUserCase;

    @Inject
    public MovieListPresenter(@Named("getMoviesUseCase") Interactor moveisInteractor) {
        mGetMoviesUserCase = (GetMoviesUserCase) moveisInteractor;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mGetMoviesUserCase.unsubscribe();
    }

    public void findMovies(@NonNull String searchText, @NonNull int pageNo) throws UnsupportedEncodingException{

        /**
         * Show error message if nothing is entered in the search edit text
         */
        if(searchText.trim().length() == 0) {
            view.showQueryError();
            return;
        }
        searchText = URLEncoder.encode(searchText, "UTF-8");

        view.showLoading();
        mGetMoviesUserCase.getMovies(searchText, pageNo, new Subscriber<MoviesBo>() {
            private MoviesBo mMoviesBo;
            @Override
            public void onCompleted() {
                final MoviesModel moviesModel = MoviesModelMapper.boToModel(mMoviesBo);
                view.showMovies(moviesModel);
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.showErrorMessage(ErrorUtils.getErrorMessage(view.getContext(), e));
                view.hideLoading();
            }

            @Override
            public void onNext(MoviesBo moviesBo) {
                mMoviesBo = moviesBo;
            }
        });
    }
}
