package com.lego.playwebtask.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lego.playwebtask.R;
import com.lego.playwebtask.fragments.ItemDetailFragment;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

//    private final List<DummyContent.DummyItem> mValues;

//    public RecyclerViewAdapter(List<DummyContent.DummyItem> items) {
//        mValues = items;
//    }
    public RecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mTitle.setText(mValues.get(position).id);
//        holder.mDate.setText(mValues.get(position).content);
//        holder.mAuthor.setText(mValues.get(position).content);
//        holder.mImage.setImageResource(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Settings.mTwoPane) {
//                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                    ItemDetailFragment fragment = new ItemDetailFragment();
//                    fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.container, fragment)
//                                .commit();
//                } else {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, ItemDetailActivity.class);
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                    context.startActivity(intent);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;//mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mTitle;
        final TextView mDate;
        final TextView mAuthor;
        final ImageView mImage;
//        DummyContent.DummyItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.textTitle);
            mDate = (TextView) view.findViewById(R.id.textDate);
            mAuthor = (TextView) view.findViewById(R.id.textAuthor);
            mImage = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
