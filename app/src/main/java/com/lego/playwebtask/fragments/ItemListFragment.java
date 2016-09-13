package com.lego.playwebtask.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lego.playwebtask.R;
import com.lego.playwebtask.adapter.RecyclerViewAdapter;
import com.lego.playwebtask.internet.RetrofitRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemListFragment extends Fragment{

    @BindView(R.id.recycler_list)
    RecyclerView mRecycler;

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
        if (hasConnection(getContext())){
            mRetrofitRequest = RetrofitRequest.getInstance();
            mRetrofitRequest.getData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        assert mRecycler != null;
//        setupRecyclerView(mRecycler);

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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new RecyclerViewAdapter(DummyContent.ITEMS));
        recyclerView.setAdapter(new RecyclerViewAdapter());
    }
}
