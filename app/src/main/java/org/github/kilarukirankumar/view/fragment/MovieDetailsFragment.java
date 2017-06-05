package org.github.kilarukirankumar.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.github.kilarukirankumar.R;
import org.github.kilarukirankumar.di.component.MovieComponent;
import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.presenter.MovieDetailsPresenter;
import org.github.kilarukirankumar.util.Constants;
import org.github.kilarukirankumar.util.ImageUtils;
import org.github.kilarukirankumar.view.MovieDetailsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {

    private static final String PARAM_MOVIE = "org.github.kilarukirankumar.param_movie";

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    @Bind(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @Bind(R.id.iv_poster)
    ImageView ivPoster;
    @Bind(R.id.tv_release_date)
    TextView tvReleaseDate;
    @Bind(R.id.tv_overview)
    TextView tvOverview;
    @Bind(R.id.tv_vote_count)
    TextView tvVoteCount;
    @Bind(R.id.tv_vote_avg)
    TextView tvVoteAvg;
    @Bind(R.id.tv_popularity)
    TextView tvPopularity;

    @Bind(R.id.tv_release_date_label)
    TextView labelReleaseDate;
    @Bind(R.id.tv_vote_count_label)
    TextView labelVoteCount;
    @Bind(R.id.tv_popularity_label)
    TextView labelPopularity;
    @Bind(R.id.tv_vote_avg_label)
    TextView labelVoteAvg;

    @Bind(R.id.tv_empty_view_msg)
    TextView labelEmptyMsg;

    MovieModel mMovieModel;

    public MovieDetailsFragment(){
        setRetainInstance(true);
    }

    public static MovieDetailsFragment forMovie(MovieModel movieModel) {
        final MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(PARAM_MOVIE, movieModel);
        movieDetailsFragment.setArguments(arguments);
        return movieDetailsFragment;
    }

    //Fragment life cycle events


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MovieComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, fragmentView);
        populateMovieDetails();
        return fragmentView;
    }

    private void populateMovieDetails() {
        final Bundle arguments = getArguments();
        if(arguments == null) {
            showErrorMessage(getString(R.string.frag_arg_err));
            return;
        }
        if(this.mMovieModel == null)
            this.mMovieModel = arguments.getParcelable(PARAM_MOVIE);
        loadMovieDetails(this.mMovieModel);
    }

    public void loadMovieDetails(MovieModel movieModel){
        this.mMovieModel = movieModel;
        if(movieModel != null){
            setContentVisibility(true);
            tvMovieTitle.setText(movieModel.getTitle());
            tvOverview.setText(movieModel.getOverview());
            tvReleaseDate.setText(movieModel.getReleaseDate());
            tvVoteCount.setText(Integer.toString(movieModel.getVoteCount()));
            tvVoteAvg.setText(Double.toString(movieModel.getVoteAverage()));
            tvPopularity.setText(Double.toString(movieModel.getPopularity()));
            ImageUtils.setImage(getContext(), ivPoster, Constants.IMAGE_DB_BASE_URL + movieModel.getPosterPath());
        }
        else
            showEmptyView();
    }

    //View methods
    @Override
    public void showEmptyView() {
        setContentVisibility(false);
    }

    private void setContentVisibility(boolean show){
        tvMovieTitle.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        ivPoster.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvOverview.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvReleaseDate.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvVoteCount.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvVoteAvg.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvPopularity.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        labelReleaseDate.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        labelVoteCount.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        labelPopularity.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        labelVoteAvg.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        labelEmptyMsg.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        Snackbar.make(tvMovieTitle, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieDetailsPresenter.destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        movieDetailsPresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        movieDetailsPresenter.resume();
    }

}
