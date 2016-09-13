package com.lego.playwebtask.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;


@Root(strict = false)
public class DataContent implements Serializable {
    @ElementList(inline = true, name="item")
    @Path("channel")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public DataContent() {
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
