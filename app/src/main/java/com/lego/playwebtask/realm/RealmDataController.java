package com.lego.playwebtask.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.lego.playwebtask.realm.database.NewsData;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * @author Lego on 19.06.2016.
 */

public class RealmDataController {
    private static RealmDataController instance;
    private final Realm realm;


    private RealmDataController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmDataController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmDataController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmDataController with(Activity activity) {
        if (instance == null) {
            instance = new RealmDataController(activity.getApplication());
        }
        return instance;
    }

    public static RealmDataController with(Application application) {
        if (instance == null) {
            instance = new RealmDataController(application);
        }
        return instance;
    }

    private RealmDataController() {
        super();
        realm = Realm.getDefaultInstance();
    }

    public static RealmDataController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }


    public RealmResults<NewsData> queryedNews() {
        return realm.where(NewsData.class)
                .findAll();
    }
    public void clearMessage() {
        realm.where(NewsData.class).findAll().deleteAllFromRealm();
    }

}
