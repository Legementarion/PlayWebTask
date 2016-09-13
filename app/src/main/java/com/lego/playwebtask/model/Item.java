package com.lego.playwebtask.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * @author Lego on 13.09.2016.
 */

@Root(name = "item", strict = false)
public class Item implements Serializable {
    @Element(name = "pubDate")
    private String mpubDate;
    @Element(name = "title")
    private String mtitle;
    @Element(name = "link")
    private String mlink;
    @Element(name = "description")
    private String mdescription;

    public Item() {
    }

    public Item(String mdescription, String mlink, String mtitle, String mpubDate) {
        this.mdescription = mdescription;
        this.mlink = mlink;
        this.mtitle = mtitle;
        this.mpubDate = mpubDate;
    }

    public String getMpubDate() {
        return mpubDate;
    }

    public void setMpubDate(String mpubDate) {
        this.mpubDate = mpubDate;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMlink() {
        return mlink;
    }

    public void setMlink(String mlink) {
        this.mlink = mlink;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }
}
