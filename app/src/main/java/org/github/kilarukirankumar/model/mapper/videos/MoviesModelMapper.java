package org.github.kilarukirankumar.model.mapper.videos;

import org.github.kilarukirankumar.model.movies.MovieBo;
import org.github.kilarukirankumar.model.movies.MoviesBo;
import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.model.videos.MoviesModel;

import java.util.ArrayList;
import java.util.List;


public class MoviesModelMapper implements MovieMapper {
    private MoviesModelMapper(){

    }
    public static MoviesModel boToModel(MoviesBo bo) {
        MoviesModel model = new MoviesModel();
        model.setPage(bo.getPage());
        List<MovieModel> movieModelList = new ArrayList<>();
        final List<MovieBo> movieBoList = bo.getMovies();
        if(movieBoList != null) {
            for (MovieBo movieBo : movieBoList) {
                movieModelList.add(MovieModelMapper.boToModel(movieBo));
            }
        }
        model.setMovies(movieModelList);
        model.setTotalPages(bo.getTotalPages());
        model.setTotalResults(bo.getTotalResults());
        return model;
    }
}
