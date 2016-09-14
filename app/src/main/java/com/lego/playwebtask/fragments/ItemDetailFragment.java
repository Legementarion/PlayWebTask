package com.lego.playwebtask.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lego.playwebtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ItemDetailFragment extends Fragment {
    public static final String ARG_ITEM_LINK = "item_link";

    @BindView(R.id.webView)
    WebView mWebView;

    private ProgressDialog mProgress;
    private Unbinder mUnbinder;

    public static ItemDetailFragment newInstance(String link) {
        ItemDetailFragment myFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_LINK, link);
        myFragment.setArguments(args);
        return myFragment;
    }

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_item_detail, container, false);
        mUnbinder = ButterKnife.bind(this,rootView);
        mWebView.setWebViewClient(new MyWebViewClient());   //forces links to be opened from webView
        mProgress = ProgressDialog.show(getContext(), "Loading", "Please wait for a moment...");
        if (getArguments().containsKey(ARG_ITEM_LINK)) {
            mWebView.loadUrl(getArguments().getString(ARG_ITEM_LINK));
        }
        return rootView;
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(mProgress.isShowing()) {
                mProgress.dismiss();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
