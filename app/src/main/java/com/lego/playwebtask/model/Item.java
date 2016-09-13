package com.lego.playwebtask.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * @author Lego on 13.09.2016.
 */

@Root(name = "item", strict = false)
public class Item implements Serializable {
    @Element(name = "pubDate")
    private String mPubDate;

    @Element(name = "title")
    private String mTitle;

    @Element(name = "link")
    private String mLink;

    @Element(name = "author")
    private String mAuthor;

    @Element(name = "description")
    private String mDescription;

    public Item() {
    }

    public Item(String mDescription, String mlink, String mTitle, String mPubDate) {
        this.setmDescription(mDescription);
        this.setmLink(mlink);
        this.setmTitle(mTitle);
        this.setmPubDate(mPubDate);
    }


    public String getmPubDate() {
        return mPubDate;
    }

    public void setmPubDate(String mPubDate) {
        this.mPubDate = mPubDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
