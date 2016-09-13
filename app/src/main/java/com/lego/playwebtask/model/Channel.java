package com.lego.playwebtask.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lego on 13.09.2016.
 */

@Root(name = "channel", strict = false)
public class Channel implements Serializable {
    @ElementList(inline = true, name="item")
    private List<Item> mItems;

    public List<Item> getItems() {
        return mItems;
    }

    public Channel() {
    }

    public Channel(List<Item> mItems) {
        this.mItems = mItems;
    }
}
