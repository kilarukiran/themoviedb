package org.github.kilarukirankumar.view.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import org.github.kilarukirankumar.BuildConfig;
import org.github.kilarukirankumar.R;
import org.github.kilarukirankumar.di.HasComponent;
import org.github.kilarukirankumar.di.component.DaggerMovieComponent;
import org.github.kilarukirankumar.di.component.MovieComponent;
import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.util.NavigationUtils;
import org.github.kilarukirankumar.view.fragment.MovieDetailsFragment;
import org.github.kilarukirankumar.view.fragment.MovieListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviesActivity
        extends BaseActivity
        implements HasComponent<MovieComponent>, MovieListFragment.MovieListener
{

    private static final String DETAILS_FRAGMENT_TAG = "details_fragment";

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.cl_videos_root_layout)
    CoordinatorLayout clRootLayout;
    @Bind(R.id.movie_list_container)
    FrameLayout movieListContainer;
    @Nullable
    @Bind(R.id.movie_details_container)
    FrameLayout movieDetailsContainer;
    
    private MovieComponent mMovieComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        injectComponent();
        ButterKnife.bind(this);
        ButterKnife.setDebug(BuildConfig.DEBUG);
        if(movieListContainer != null) {
            if (savedInstanceState == null) {
                MovieListFragment fragment = new MovieListFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.movie_list_container,
                        fragment).commit();
            }
        }
        if(movieDetailsContainer != null){
            if (savedInstanceState == null) {
                MovieDetailsFragment fragment = MovieDetailsFragment.forMovie(null);
                getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container,
                        fragment, DETAILS_FRAGMENT_TAG).commit();
            }
        }
        initializeUI();
    }

    private void initializeUI() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
        }
    }

    private void injectComponent() {
        mMovieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        mMovieComponent.inject(this);
    }

    @Override
    public MovieComponent getComponent() {
        return mMovieComponent;
    }

    @Override
    public void onMovieClicked(MovieModel movieModel) {
        if(movieDetailsContainer == null) { //phone
            NavigationUtils.navigateToMovieDetails(this, movieModel);
        }
        else{ //tablet
            MovieDetailsFragment fragment = (MovieDetailsFragment)(getSupportFragmentManager()
                    .findFragmentByTag(DETAILS_FRAGMENT_TAG));
            if(fragment != null)
                fragment.loadMovieDetails(movieModel);
        }
    }

    @Override
    public void onSearchClicked() {
        if(movieDetailsContainer != null){
            MovieDetailsFragment fragment = (MovieDetailsFragment)(getSupportFragmentManager()
                    .findFragmentByTag(DETAILS_FRAGMENT_TAG));
            if(fragment != null)
                fragment.loadMovieDetails(null);
        }
    }
}
