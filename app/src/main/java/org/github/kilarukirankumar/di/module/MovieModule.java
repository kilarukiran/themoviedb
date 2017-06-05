package org.github.kilarukirankumar.di.module;

import android.support.v4.app.FragmentManager;

import org.github.kilarukirankumar.di.ActivityScope;
import org.github.kilarukirankumar.executor.PostExecutionThread;
import org.github.kilarukirankumar.executor.ThreadExecutor;
import org.github.kilarukirankumar.interactor.Interactor;
import org.github.kilarukirankumar.interactor.Movies.GetMoviesUserCase;
import org.github.kilarukirankumar.repository.MoviesRepository;
import org.github.kilarukirankumar.repository.MoviesRepositoryImpl;
import org.github.kilarukirankumar.repository.datasource.movies.MoviesDataSource;
import org.github.kilarukirankumar.repository.datasource.movies.MoviesDataSourceImpl;
import org.github.kilarukirankumar.view.activity.BaseActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(includes = ActivityModule.class)
public class MovieModule {

    @Provides
    @ActivityScope
    FragmentManager fragmentManager(BaseActivity baseActivity) {
        return baseActivity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    MoviesDataSource moviesDataSource(MoviesDataSourceImpl moviesDataSource) {
        return moviesDataSource;
    }

    @Provides
    @ActivityScope
    MoviesRepository moviesRepository(MoviesRepositoryImpl moviesRepository){
        return moviesRepository;
    }

    @Provides
    @ActivityScope
    @Named("getMoviesUseCase")
    Interactor getMoviesUseCase(ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread,
                                MoviesRepository moviesRepository) {
        return new GetMoviesUserCase(threadExecutor,
                postExecutionThread,
                moviesRepository);
    }
}
