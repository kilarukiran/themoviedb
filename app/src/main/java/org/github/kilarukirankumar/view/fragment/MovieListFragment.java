package org.github.kilarukirankumar.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.github.kilarukirankumar.R;
import org.github.kilarukirankumar.di.component.MovieComponent;
import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.model.videos.MoviesModel;
import org.github.kilarukirankumar.presenter.MovieListPresenter;
import org.github.kilarukirankumar.view.MovieListView;
import org.github.kilarukirankumar.view.adapter.MovieAdapter;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MovieListFragment extends BaseFragment implements MovieListView, MovieAdapter.OnItemClickListener {

    @Inject
    MovieListPresenter movieListPresenter;
    @Inject
    MovieAdapter mMovieAdapter;

    @Bind(R.id.search_edit_text)
    EditText searchEditText;
    @Bind(R.id.text_no_results)
    TextView textNoResults;
    @Bind(R.id.rv_movie_list)
    RecyclerView mMovieList;
    @Nullable
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    private int mPageNo = 1;
    private int mTotalPages = 1;
    private int mTotalMoviesCount = 0;
    private LinearLayoutManager mLinearLayoutManager;
    private MovieListener movieListener;

    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE)
                return;
            int lastCompletelyVisibleItemPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (lastCompletelyVisibleItemPosition == mMovieAdapter.getItemCount() - 1
                    && mPageNo <= mTotalPages && mTotalMoviesCount > mMovieAdapter.getItemCount()) {
                findMovies();
            }
        }
    };

    public MovieListFragment(){
        setRetainInstance(true);
    }

    private void initializeRecyclerView() {
        mPageNo = 1;
        mMovieList.getItemAnimator().setChangeDuration(0L);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMovieList.setLayoutManager(mLinearLayoutManager);
        mMovieAdapter.setOnItemClickListener(this);
        mMovieList.setAdapter(mMovieAdapter);
    }

    private void registerListeners() {
        mMovieList.addOnScrollListener(mOnScrollListener);
    }

    private void initializePresenter() {
        movieListPresenter.setView(this);
    }

    protected void resetList() {
        mPageNo = 1;
        mMovieAdapter.clearList();
    }

    private void findMovies() {
        try {
            movieListPresenter.findMovies(searchEditText.getText().toString(),
                    mPageNo);
        }
        catch (UnsupportedEncodingException e){
        }
    }

    private void incrementPage(final MoviesModel moviesModel) {
        mTotalPages = moviesModel.getTotalPages();
        mTotalMoviesCount = moviesModel.getTotalResults();
        if (moviesModel.getPage() >= 1 && mPageNo <= moviesModel.getTotalPages()) {
            mPageNo++;
        }
    }

    @OnClick(R.id.btn_search)
    public void onSearchClicked(View view){
        movieListener.onSearchClicked();
        hideKeyboard();
        resetList();
        findMovies();
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)
                getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        final View currentFocus = getActivity().getCurrentFocus();
        if (null != currentFocus) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Start of fragment life cycle events
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if(activity instanceof MovieListener)
            movieListener = (MovieListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MovieComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.fragment_movielist, container, false);
        ButterKnife.bind(this, fragmentView);
        initializeRecyclerView();
        registerListeners();
        return fragmentView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializePresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.movieListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.movieListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.movieListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.movieListener = null;
    }
    /**
     * end of fragment life cycle events
     */


    /**
     * Start of View methods
     */
    @Override
    public void showMovies(MoviesModel moviesModel) {
        incrementPage(moviesModel);
        //mTotalPages = moviesModel.getTotalPages();
        final List<MovieModel> moviesModelList = moviesModel.getMovies();
        mMovieAdapter.setList(moviesModelList);
        if(mMovieAdapter.getItemCount() == 0){
            mMovieList.setVisibility(View.GONE);
            textNoResults.setVisibility(View.VISIBLE);
        }
        else{
            textNoResults.setVisibility(View.GONE);
            mMovieList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(mMovieList, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showQueryError() {
        searchEditText.setError(getString(R.string.query_error));
    }

    /**
     * End of View methods
     */

    @Override
    public void onMovieItemClicked(MovieModel movieModel){
        movieListener.onMovieClicked(movieModel);
    }


    public interface MovieListener {
        void onMovieClicked(MovieModel movieModel);
        void onSearchClicked();
    }
}
