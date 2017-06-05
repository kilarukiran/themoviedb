package org.github.kilarukirankumar.repository.datasource.movies;

import android.content.Context;

import org.github.kilarukirankumar.mapper.Movies.MoviesDtoMapper;
import org.github.kilarukirankumar.model.movies.MoviesBo;
import org.github.kilarukirankumar.network.ApiConstants;
import org.github.kilarukirankumar.network.MoviesDBApi;
import org.github.kilarukirankumar.repository.datasource.AbstractCloudDataSource;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

public class MoviesDataSourceImpl extends AbstractCloudDataSource implements MoviesDataSource {
    private final MoviesDBApi mApi;
    private final MoviesDtoMapper mDtoMapper;

    @Inject
    public MoviesDataSourceImpl(Context context, MoviesDtoMapper dtoMapper) {
        super(context);
        mDtoMapper = dtoMapper;
        mApi = buildRetrofit().create(MoviesDBApi.class);
    }

    @Override
    public Observable<MoviesBo> getMovies(String query, int pageNo) {
        return mApi.getMovies(ApiConstants.API_KEY,
                query,
                pageNo)
                .map(mDtoMapper::moviesDtoToBo);
    }

    @Override
    protected Map<String, String> getHeaders() {
        return null;
    }
}
