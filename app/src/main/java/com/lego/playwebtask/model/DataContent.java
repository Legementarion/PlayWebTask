package com.lego.playwebtask.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "breakfast_menu")
public class DataContent {
    @ElementList(inline = true)
    List<News> newsList;
}

@Root(name="food")
class News{
    @Element(name = "title")
    String title;

    @Element(name = "pubDate")
    String date;

    @Element(name = "author")
    String author;

    @Element(name = "url")
    String url;
}
