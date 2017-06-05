package org.github.kilarukirankumar.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.github.kilarukirankumar.R;
import org.github.kilarukirankumar.di.HasComponent;
import org.github.kilarukirankumar.di.component.DaggerMovieComponent;
import org.github.kilarukirankumar.di.component.MovieComponent;
import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.view.fragment.MovieDetailsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MovieDetailsActivity extends BaseActivity implements HasComponent<MovieComponent> {

    private static final String INTENT_EXTRA_PARAM_MOVIE = "org.github.kilarukirankumar.INTENT_PARAM_MOVIE";
    private static final String INSTANCE_STATE_PARAM_MOVIE = "org.github.kilarukirankumar.STATE_PARAM_MOVIE";


    @Bind(R.id.toolbar)
    @Nullable
    Toolbar mToolbar;
    @Bind(R.id.tv_toolbar_title)
    @Nullable
    TextView mTitle;

    public static Intent getCallingIntent(Context context, MovieModel movieModel) {
        Intent callingIntent = new Intent(context, MovieDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movieModel);
        return callingIntent;
    }

    private MovieModel movieModel;
    MovieComponent mMovieComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        initializeUI();
        injectComponent();
        initializeActivity(savedInstanceState);
    }

    private void initializeUI() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            mTitle.setText(R.string.movie_details_title);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void injectComponent(){
        mMovieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.movieModel = getIntent().getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, new MovieDetailsFragment().forMovie(movieModel)).commit();
        } else {
            this.movieModel = savedInstanceState.getParcelable(INSTANCE_STATE_PARAM_MOVIE);
        }
    }

    @Override
    public MovieComponent getComponent() {
        return mMovieComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
