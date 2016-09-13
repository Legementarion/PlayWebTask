package com.lego.playwebtask.activity;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.lego.playwebtask.R;
import com.lego.playwebtask.fragments.ItemListFragment;
import com.lego.playwebtask.internet.RetrofitRequest;
import com.lego.playwebtask.realm.RealmDataController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout mContainer;
    private Unbinder mUnbinder;

    private Realm mRealm;
    private RetrofitRequest mRequestRetrofit;
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
        mFragmentManager.beginTransaction().replace(R.id.container, mListFragment).commit();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        try {
            Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                mRealm = Realm.getInstance(realmConfiguration);
            } catch (Exception ex){
                throw ex;
                //No Realm file to remove.
            }
        }
        mRealm = Realm.getInstance(realmConfiguration);
        RealmDataController.with(this);
        mRequestRetrofit = RetrofitRequest.getInstance(mRealm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
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
