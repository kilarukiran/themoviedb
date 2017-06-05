package org.github.kilarukirankumar.interactor.Movies;

import org.github.kilarukirankumar.executor.PostExecutionThread;
import org.github.kilarukirankumar.executor.ThreadExecutor;
import org.github.kilarukirankumar.interactor.Interactor;
import org.github.kilarukirankumar.model.movies.MoviesBo;
import org.github.kilarukirankumar.repository.MoviesRepository;

import rx.Observable;
import rx.Subscriber;

public class GetMoviesUserCase extends Interactor {
    private final MoviesRepository mMoviesRepository;
    private int mPageNo;
    private String mQuery;


    public GetMoviesUserCase(final ThreadExecutor threadExecutor,
                            final PostExecutionThread postExecutionThread,
                            final MoviesRepository moviesRepository) {
        super(threadExecutor, postExecutionThread);
        this.mMoviesRepository = moviesRepository;
    }

    @Override
    protected Observable buildObservableForUseCase() {
        return this.mMoviesRepository.getMovies(mQuery, mPageNo);
    }

    public void getMovies(final String query, int pageNo,
                          Subscriber<MoviesBo> moviesBoSubscriber) {
        mQuery = query;
        mPageNo = pageNo;
        super.execute(moviesBoSubscriber);
    }
}
