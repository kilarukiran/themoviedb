package org.github.kilarukirankumar.repository.datasource.movies;

import org.github.kilarukirankumar.model.movies.MoviesBo;

import rx.Observable;


public interface MoviesDataSource {
    Observable<MoviesBo> getMovies(final String query, final int pageNo);
}
