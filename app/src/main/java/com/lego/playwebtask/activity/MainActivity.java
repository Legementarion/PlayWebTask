package com.lego.playwebtask.activity;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.lego.playwebtask.R;
import com.lego.playwebtask.fragments.ItemListFragment;
import com.lego.playwebtask.request.RetrofitRequest;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout mContainer;
    private Unbinder mUnbinder;

    private FragmentManager mFragmentManager;
    private ItemListFragment mListFragment;
    private boolean mDoubleBackToExitPressedOnce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        mListFragment = new ItemListFragment();
        mFragmentManager = getSupportFragmentManager();
        switchContent(mListFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        if (!mListFragment.isResumed()) {
            mFragmentManager.beginTransaction().replace(R.id.container, mListFragment).commit();
        } else {
            if (mDoubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.mDoubleBackToExitPressedOnce = true;
            Snackbar.make(mContainer, R.string.doubleClick_backBtn, Snackbar.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDoubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public void switchContent(Fragment fragment){
        mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }
}
