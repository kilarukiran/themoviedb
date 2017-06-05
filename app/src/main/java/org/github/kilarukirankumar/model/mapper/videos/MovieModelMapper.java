package org.github.kilarukirankumar.model.mapper.videos;

import org.github.kilarukirankumar.model.movies.MovieBo;
import org.github.kilarukirankumar.model.videos.MovieModel;



public class MovieModelMapper implements MovieMapper {
    private MovieModelMapper() {
    }

    public static MovieModel boToModel(MovieBo bo) {
        MovieModel model = new MovieModel();
        model.setId(bo.getId());
        model.setAdult(bo.getAdult());
        model.setBackdropPath(bo.getBackdropPath());
        model.setGenreIds(bo.getGenreIds());
        model.setOriginalLanguage(bo.getOriginalLanguage());
        model.setOriginalTitle(bo.getOriginalTitle());
        model.setOverview(bo.getOverview());
        model.setPopularity(bo.getPopularity());
        model.setPosterPath(bo.getPosterPath());
        model.setReleaseDate(bo.getReleaseDate());
        model.setTitle(bo.getTitle());
        model.setVideo(bo.getVideo());
        model.setVoteCount(bo.getVoteCount());
        model.setVoteAverage(bo.getVoteAverage());
        return model;
    }
}
