package com.lego.playwebtask.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;


@Root(name = "rss", strict = false)
public class DataContent implements Serializable {
    @Element(name = "channel")
    private Channel mChannel;

    public Channel getmChannel() {
        return mChannel;
    }

    public DataContent() {
    }

    public DataContent(Channel mChannel) {
        this.mChannel = mChannel;
    }
}
