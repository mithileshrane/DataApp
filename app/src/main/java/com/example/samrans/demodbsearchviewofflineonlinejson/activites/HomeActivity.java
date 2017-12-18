package com.example.samrans.demodbsearchviewofflineonlinejson.activites;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samrans.demodbsearchviewofflineonlinejson.AppFile;
import com.example.samrans.demodbsearchviewofflineonlinejson.R;
import com.example.samrans.demodbsearchviewofflineonlinejson.adapter.DataAdapter;
import com.example.samrans.demodbsearchviewofflineonlinejson.interfaces.ApiEndPoint;
import com.example.samrans.demodbsearchviewofflineonlinejson.interfaces.OnLoadMoreListener;
import com.example.samrans.demodbsearchviewofflineonlinejson.models.Movies;
import com.example.samrans.demodbsearchviewofflineonlinejson.models.Results;
import com.example.samrans.demodbsearchviewofflineonlinejson.utils.ConnectivityReceiver;
import com.example.samrans.demodbsearchviewofflineonlinejson.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    ConnectivityReceiver connectivityReceiver;
    Retrofit retrofitClient;
    Call<Movies> moviesCall;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_welcomeImage)
    ImageView ivWelcomeImage;
    @BindView(R.id.tv_welcometoapp)
    TextView tvWelcometoapp;
    @BindView(R.id.rel_error_view)
    RelativeLayout relErrorView;
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    @BindView(R.id.progessBarLoader)
    ProgressBar progessBarLoader;
    private Context mContext;

    DataAdapter dataAdapter;
    private ArrayList<Results> moviesList;
    private boolean flagFromPagination;
    Movies movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
        getSupportActionBar().setTitle(R.string.welcometext);
        connectivityReceiver = new ConnectivityReceiver();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityReceiver, intentFilter);
        AppFile.getInstance().setConnectivityListener(this);
        showErrorView();

        dataAdapter = new DataAdapter(mContext, moviesList, recylerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setItemAnimator(new DefaultItemAnimator());
        recylerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        recylerview.setAdapter(dataAdapter);

        dataAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (moviesList.size() <= Integer.parseInt(movies.getTotal_pages())) {
                    moviesList.add(null);
                    dataAdapter.notifyItemInserted(moviesList.size() - 1);
                    flagFromPagination = true;
                    getData();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            completedOrdersArrayList.remove(completedOrdersArrayList.size() - 1);
//                            orderAdapter.notifyItemRemoved(completedOrdersArrayList.size());
//                            orderAdapter.setLoaded();
//
//                            //Generating more data
////                            int index = contacts.size();
////                            int end = index + 10;
////                            for (int i = index; i < end; i++) {
////                                Contact contact = new Contact();
////                                contact.setPhone(phoneNumberGenerating());
////                                contact.setEmail("DevExchanges" + i + "@gmail.com");
////                                contacts.add(contact);
////                            }
////                            contactAdapter.notifyDataSetChanged();
////                            contactAdapter.setLoaded();
//
//
//                        }
//                    }, 5000);
                } else {
                    Toast.makeText(mContext, R.string.reached_to_end, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                showLoadingView();
                getData();
            }
        });
    }


    private void getData() {
        retrofitClient = new RetrofitClient(mContext).getBlankRetrofit();
        moviesCall = retrofitClient.create(ApiEndPoint.class)
                .getCategoryMenu(getString(R.string.api_key));
        moviesCall.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                movies = response.body();
                if (movies != null) {
                    if (!flagFromPagination) {
                        moviesList.addAll(new ArrayList<Results>(Arrays.asList(movies.getResults())));
                        dataAdapter.notifyDataSetChanged();
                        if (moviesList.size() == 0) showErrorView();
                        else showLoadedView();
                    } else {
                        flagFromPagination = false;
                        moviesList.remove(moviesList.size() - 1);
                        dataAdapter.notifyItemRemoved(moviesList.size());
                        moviesList.addAll(new ArrayList<Results>(Arrays.asList(movies.getResults())));
                        dataAdapter.notifyDataSetChanged();
                        dataAdapter.setLoaded();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                if (!flagFromPagination) {
                    if (moviesList.size() == 0) showErrorView();
                    else showLoadedView();
                } else {
                    flagFromPagination = false;
                    moviesList.remove(moviesList.size() - 1);
                    dataAdapter.notifyItemRemoved(moviesList.size());
                    dataAdapter.setLoaded();
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    private void showErrorView() {
        relErrorView.setVisibility(View.VISIBLE);
        recylerview.setVisibility(View.GONE);
        progessBarLoader.setVisibility(View.GONE);
    }

    private void showLoadingView() {
        relErrorView.setVisibility(View.GONE);
        recylerview.setVisibility(View.GONE);
        progessBarLoader.setVisibility(View.VISIBLE);
    }

    private void showLoadedView() {
        relErrorView.setVisibility(View.VISIBLE);
        recylerview.setVisibility(View.GONE);
        progessBarLoader.setVisibility(View.GONE);
    }
}
