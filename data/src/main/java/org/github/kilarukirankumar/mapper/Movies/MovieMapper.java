package org.github.kilarukirankumar.mapper.Movies;

import org.github.kilarukirankumar.dto.movies.MovieDto;
import org.github.kilarukirankumar.mapper.Mapper;
import org.github.kilarukirankumar.model.movies.MovieBo;

import javax.inject.Inject;

public class MovieMapper implements Mapper<MovieBo, MovieDto> {
    @Inject
    public MovieMapper() {
    }

    @Override
    public MovieDto modelToData(MovieBo model) {
        MovieDto dto = new MovieDto();
        dto.setId(model.getId());
        dto.setAdult(model.getAdult());
        dto.setBackdropPath(model.getBackdropPath());
        dto.setGenreIds(model.getGenreIds());
        dto.setOriginalLanguage(model.getOriginalLanguage());
        dto.setOriginalTitle(model.getOriginalTitle());
        dto.setOverview(model.getOverview());
        dto.setPopularity(model.getPopularity());
        dto.setPosterPath(model.getPosterPath());
        dto.setReleaseDate(model.getReleaseDate());
        dto.setTitle(model.getTitle());
        dto.setVideo(model.getVideo());
        dto.setVoteCount(model.getVoteCount());
        dto.setVoteAverage(model.getVoteAverage());
        return dto;
    }

    @Override
    public MovieBo dataToModel(MovieDto data) {
        MovieBo bo = new MovieBo();
        bo.setId(bo.getId());
        bo.setAdult(data.isAdult());
        bo.setBackdropPath(data.getBackdropPath());
        bo.setGenreIds(data.getGenreIds());
        bo.setOriginalLanguage(data.getOriginalLanguage());
        bo.setOriginalTitle(data.getOriginalTitle());
        bo.setOverview(data.getOverview());
        bo.setPopularity(data.getPopularity());
        bo.setPosterPath(data.getPosterPath());
        bo.setReleaseDate(data.getReleaseDate());
        bo.setTitle(data.getTitle());
        bo.setVideo(data.isVideo());
        bo.setVoteCount(data.getVoteCount());
        bo.setVoteAverage(data.getVoteAverage());
        return bo;
    }
}
