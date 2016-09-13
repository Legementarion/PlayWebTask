package com.lego.playwebtask.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.lego.playwebtask.R;
import com.lego.playwebtask.adapter.RecyclerViewAdapter;
import com.lego.playwebtask.model.Item;
import com.lego.playwebtask.request.RequestCallback;
import com.lego.playwebtask.request.RetrofitRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemListFragment extends Fragment implements RequestCallback {

    @BindView(R.id.recycler_list)
    RecyclerView mRecycler;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;
    private RetrofitRequest mRetrofitRequest;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasConnection(getContext())) {
            mRetrofitRequest = RetrofitRequest.getInstance();
        } else {
            Toast.makeText(getContext(), R.string.check_connection, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(linearLayoutManager);
        setupRecyclerView();

        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private static boolean hasConnection(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void setupRecyclerView() {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setActivated(true);
        mRetrofitRequest.getData(this);
    }

    @Override
    public void requestCallback(List<Item> items) {
        if (mProgressBar!=null && mProgressBar.isActivated()) {
            mProgressBar.setVisibility(View.GONE);
            mProgressBar.setActivated(false);

        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),items, getChildFragmentManager());
        mRecycler.setAdapter(recyclerViewAdapter);
    }
}
