package org.github.kilarukirankumar.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.github.kilarukirankumar.R;
import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.util.Constants;
import org.github.kilarukirankumar.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieModel> mList;
    private OnItemClickListener onItemClickListener;
    private int mSelectedPos;

    public interface OnItemClickListener {
        void onMovieItemClicked(MovieModel movieModel);
    }

    @Inject
    public MovieAdapter() {
        mList = new ArrayList<>();
        mSelectedPos = -1;
    }

    public void setList(@NonNull List<MovieModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_cell_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieModel movieModel = mList.get(position);
        holder.tvName.setText(movieModel.getTitle());
        ImageUtils.setImage(holder.itemView.getContext(),
                holder.ivPhoto,
                Constants.IMAGE_DB_BASE_URL + movieModel.getPosterPath());
        if(position != mSelectedPos)
            holder.cvRootLayout.setBackgroundResource(android.R.color.white);
        else
            holder.cvRootLayout.setBackgroundResource(android.R.color.holo_orange_light);
        holder.bind();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clearList() {
        mList.clear();
        mSelectedPos = -1;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_movie_cell_root_layout)
        CardView cvRootLayout;

        @Bind(R.id.iv_poster)
        ImageView ivPhoto;

        @Bind(R.id.tv_movie_title)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(){
            cvRootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(mSelectedPos != -1 || pos != mSelectedPos) { //already an item selected
                        int oldPos = mSelectedPos;
                        mSelectedPos = pos;
                        notifyItemChanged(oldPos);
                    }
                    cvRootLayout.setBackgroundResource(android.R.color.holo_orange_light);
                    onItemClickListener.onMovieItemClicked(mList.get(pos));
                }
            });
        }
    }
}
