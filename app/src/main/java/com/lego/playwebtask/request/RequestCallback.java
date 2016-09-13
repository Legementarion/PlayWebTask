package com.lego.playwebtask.request;

import com.lego.playwebtask.model.Item;

import java.util.List;

/**
 * @author Lego on 14.09.2016.
 */

public interface RequestCallback {
   public void requestCallback(List<Item> items);
}
