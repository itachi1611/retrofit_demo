package com.fox.demo;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import com.fox.demo.adapters.UserAdapter;
import com.fox.demo.base.BaseActivity;
import com.fox.demo.models.Datum;
import com.fox.demo.models.User;
import com.fox.demo.utlis.EndlessRecyclerViewScrollListener;
import com.fox.demo.utlis.RecyclerViewTouchListener;
import com.fox.demo.web_service.ApiUtli;
import com.fox.demo.web_service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private SwipeRefreshLayout srlUser;
    private RecyclerView rvUser;

    private UserAdapter adapter;
    private LinearLayoutManager layoutManager;

    private List<Datum> users;
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //Init RecycleView
        initRecyclerView();

        //Handle swipe action to refresh data
        onSwipeRefreshData();

        //Handle Load more event
        onScrollToLoadMore();
    }

    private void onScrollToLoadMore() {
        rvUser.setOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                page++;
                onFetchUser(page++);
            }
        });
    }

    private void initRecyclerView() {
        rvUser.setItemAnimator(new DefaultItemAnimator());
        rvUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvUser.setHasFixedSize(true);
        rvUser.setLayoutManager(layoutManager);
    }

    private void onSwipeRefreshData() {
        srlUser.setOnRefreshListener(() -> {
            page = 1;
            onFetchUser(page);
            new Handler().postDelayed(() -> {
                srlUser.setRefreshing(false);
            }, 300);
        });
    }

    private void initView() {
        srlUser = findViewById(R.id.srlUser);
        rvUser = findViewById(R.id.rvUser);
        users = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onFetchUser(page);
    }

    private void onFetchUser(int s) {
        onShowLoading();
        UserService call = ApiUtli.getUserService();
        call.getAllUser(String.valueOf(s)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null) {
                    users.addAll(response.body().getData());
                }
                onLoadDataRecycleView();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onHideLoading();
                t.printStackTrace();
            }
        });
    }

    private void onLoadDataRecycleView() {
        onHideLoading();
        if(adapter != null ) {
            adapter = null;
        }
        if(adapter == null) {
            adapter = new UserAdapter(MainActivity.this, users);
            //rvImageGrid.getRecycledViewPool().clear();
            rvUser.setAdapter(adapter);
        }
        //rvUser.setOnTouchListener(new RecyclerViewTouchListener());

    }


}