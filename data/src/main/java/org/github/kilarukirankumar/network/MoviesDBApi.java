package org.github.kilarukirankumar.network;

import org.github.kilarukirankumar.dto.movies.MoviesDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MoviesDBApi {
    @GET("search/movie")
    Observable<MoviesDto> getMovies(@Query("api_key") String apiKey,
                                    @Query("query") String query,
                                    @Query("page") int pageNo);
}
