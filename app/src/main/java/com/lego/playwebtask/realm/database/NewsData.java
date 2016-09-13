package com.lego.playwebtask.realm.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Lego on 20.06.2016.
 */

public class NewsData extends RealmObject {

    @PrimaryKey
    private String link;
    private String title;
    private String date;
    private String author;
    private String url;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
