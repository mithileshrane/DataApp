package com.example.samrans.demodbsearchviewofflineonlinejson.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.samrans.demodbsearchviewofflineonlinejson.R;
import com.example.samrans.demodbsearchviewofflineonlinejson.interfaces.OnLoadMoreListener;
import com.example.samrans.demodbsearchviewofflineonlinejson.models.Results;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Samrans on 12-12-2017.
 */

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<Results> moviesList;
    private OnLoadMoreListener onLoadMoreListener;
    private int visibleThreshold;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    public DataAdapter(Context mContext, ArrayList<Results> moviesList, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.moviesList = moviesList;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        visibleThreshold = 5;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == VIEW_TYPE_ITEM) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item_movie, parent, false);

            return new DataBinderHolder(itemView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataBinderHolder) {
            DataBinderHolder dataBinderHolder = (DataBinderHolder) holder;

           dataBinderHolder.tv_date_movie.setText(moviesList.get(position).getRelease_date());
            dataBinderHolder.tv_name_movie.setText(moviesList.get(position).getTitle());
            dataBinderHolder.tv_type_movie.setText(Boolean.parseBoolean(moviesList.get(position).getAdult())? "(A)":"(U/A)");

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return moviesList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

        class DataBinderHolder extends RecyclerView.ViewHolder {
        ImageView iv_moviethumbnail, iv_play;
        TextView tv_date_movie, tv_type_movie, tv_name_movie;

        public DataBinderHolder(View itemView) {
            super(itemView);
            tv_date_movie = (TextView) itemView.findViewById(R.id.tv_date_movie);
            tv_type_movie = (TextView) itemView.findViewById(R.id.tv_type_movie);
            tv_name_movie = (TextView) itemView.findViewById(R.id.tv_name_movie);

            iv_moviethumbnail = (ImageView) itemView.findViewById(R.id.iv_moviethumbnail);
            iv_play = (ImageView) itemView.findViewById(R.id.iv_play);
        }
    }


    class LoadingViewHolder extends RecyclerView.ViewHolder {

        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }


}
