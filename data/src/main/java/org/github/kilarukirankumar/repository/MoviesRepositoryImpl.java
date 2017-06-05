package org.github.kilarukirankumar.repository;

import org.github.kilarukirankumar.model.movies.MoviesBo;
import org.github.kilarukirankumar.repository.datasource.movies.MoviesDataSource;

import javax.inject.Inject;

import rx.Observable;

public class MoviesRepositoryImpl implements MoviesRepository {
    private final MoviesDataSource mMoviesDataSource;

    @Inject
    public MoviesRepositoryImpl(MoviesDataSource moviesDataSource) {
        mMoviesDataSource = moviesDataSource;
    }

    @Override
    public Observable<MoviesBo> getMovies(String query, int pageNo) {
        return mMoviesDataSource.getMovies(query, pageNo);
    }
}
