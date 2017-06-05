package org.github.kilarukirankumar.mapper.Movies;

import org.github.kilarukirankumar.dto.movies.MovieDto;
import org.github.kilarukirankumar.dto.movies.MoviesDto;
import org.github.kilarukirankumar.mapper.Mapper;
import org.github.kilarukirankumar.model.movies.MovieBo;
import org.github.kilarukirankumar.model.movies.MoviesBo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MoviesMapper implements Mapper<MoviesBo, MoviesDto> {

    @Inject
    MovieMapper mMovieMapper;

    @Inject
    public MoviesMapper() {
    }

    @Override
    public MoviesDto modelToData(MoviesBo model) {
        MoviesDto dto = new MoviesDto();
        dto.setPage(model.getPage());
        List<MovieDto> movieDtoList = new ArrayList<>();
        final List<MovieBo> movieBoList = model.getMovies();
        if(movieBoList != null) {
            for (MovieBo movieBo : movieBoList) {
                movieDtoList.add(mMovieMapper.modelToData(movieBo));
            }
        }
        dto.setMovies(movieDtoList);
        dto.setTotalPages(model.getTotalPages());
        dto.setTotalResults(model.getTotalResults());
        return dto;
    }

    @Override
    public MoviesBo dataToModel(MoviesDto data) {
        MoviesBo bo = new MoviesBo();
        bo.setPage(data.getPage());
        final List<MovieDto> movieDtoList = data.getMovies();
        List<MovieBo> movieBoList = new ArrayList<>();
        if(null != movieDtoList) {
            for (MovieDto movieDto : movieDtoList) {
                movieBoList.add(mMovieMapper.dataToModel(movieDto));
            }
        }
        bo.setMovies(movieBoList);
        bo.setTotalPages(data.getTotalPages());
        bo.setTotalResults(data.getTotalResults());
        return bo;
    }
}
