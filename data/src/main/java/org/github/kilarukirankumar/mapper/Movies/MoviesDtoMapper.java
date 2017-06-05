package org.github.kilarukirankumar.mapper.Movies;

import org.github.kilarukirankumar.dto.movies.MoviesDto;
import org.github.kilarukirankumar.model.movies.MoviesBo;

import javax.inject.Inject;

public class MoviesDtoMapper {
    @Inject
    MoviesMapper mMoviesMapper;

    @Inject
    public MoviesDtoMapper() {

    }

    public MoviesBo moviesDtoToBo(MoviesDto moviesDto) {
        return mMoviesMapper.dataToModel(moviesDto);
    }

}
