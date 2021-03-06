package com.taras.secondapplication.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.models.Files;

import io.realm.RealmList;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    private RealmList<Files> mData;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v;
        }
    }

    public ImageRecyclerAdapter(RealmList<Files> myDataset) {
        mData = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Context context = holder.imageView.getContext();

        Picasso.with(context)
                .load(Const.URL_IMAGE+Uri.parse(mData.get(position).getFilename()))
                .fit()
                .centerCrop()
                .into(holder.imageView);

        final String message = mData.get(position).getId();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return mData.size();
    }
}

