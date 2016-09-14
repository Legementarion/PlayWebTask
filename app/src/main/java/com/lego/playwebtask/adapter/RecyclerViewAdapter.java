package com.lego.playwebtask.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lego.playwebtask.R;
import com.lego.playwebtask.activity.MainActivity;
import com.lego.playwebtask.fragments.ItemDetailFragment;
import com.lego.playwebtask.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private FragmentManager mFragmentManager;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Item> items, FragmentManager fragmentManager) {
        mValues = items;
        mFragmentManager = fragmentManager;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getmTitle());
        holder.mDate.setText(mValues.get(position).getmPubDate());
        holder.mAuthor.setText(mValues.get(position).getmAuthor());
        Picasso.with(mContext).load(getImage(mValues.get(position).getmDescription())).into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = ItemDetailFragment.newInstance(holder.mItem.getmLink());
                switchFragment(newFragment);
            }

            private void switchFragment(Fragment newFragment) {
                if (mContext == null)
                    return;
                if (mContext instanceof MainActivity) {
                    MainActivity feeds = (MainActivity) mContext;
                    feeds.switchContent(newFragment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /** get url from description */
    private String getImage(String s) {
        Pattern pattern = Pattern.compile("(https?:\\/\\/.*\\.(?:png|jpg|jpeg))");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        @BindView(R.id.textTitle) TextView mTitle;
        @BindView(R.id.textDate) TextView mDate;
        @BindView(R.id.textAuthor) TextView mAuthor;
        @BindView(R.id.imageView) ImageView mImage;
        Item mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this,view);
        }
    }
}
