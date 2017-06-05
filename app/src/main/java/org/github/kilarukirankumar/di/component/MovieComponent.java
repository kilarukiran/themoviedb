package org.github.kilarukirankumar.di.component;

import org.github.kilarukirankumar.di.ActivityScope;
import org.github.kilarukirankumar.di.module.ActivityModule;
import org.github.kilarukirankumar.di.module.MovieModule;
import org.github.kilarukirankumar.view.activity.MovieDetailsActivity;
import org.github.kilarukirankumar.view.activity.MoviesActivity;
import org.github.kilarukirankumar.view.fragment.MovieDetailsFragment;
import org.github.kilarukirankumar.view.fragment.MovieListFragment;

import dagger.Component;

/**
 *
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, MovieModule.class})
public interface MovieComponent {
    void inject(MoviesActivity moviesActivity);
    void inject(MovieListFragment movieListFragment);
    void inject(MovieDetailsFragment movieDetailsFragment);
    void inject(MovieDetailsActivity movieDetailsActivity);
}
