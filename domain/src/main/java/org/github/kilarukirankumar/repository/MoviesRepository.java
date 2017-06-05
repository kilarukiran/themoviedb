package org.github.kilarukirankumar.repository;

import org.github.kilarukirankumar.model.movies.MoviesBo;
import rx.Observable;

public interface MoviesRepository {
    Observable<MoviesBo> getMovies(final String query, final int pageNo);
}
