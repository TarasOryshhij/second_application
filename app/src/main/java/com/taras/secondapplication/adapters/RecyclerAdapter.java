package com.taras.secondapplication.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.activity.ItemActivity;
import com.taras.secondapplication.models.CardModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<CardModel> mArrCardModel = new ArrayList<>(); //[Comment] Use abstraction instead of realization
    private String mStatus;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cv;
        private TextView tvTitle;
        private TextView tvStreet;
        private TextView tvLike;
        private TextView tvDate;
        private TextView tvDay;

        private ViewHolder(View v) {
            super(v);
            cv = (CardView) itemView.findViewById(R.id.cv);
            tvTitle = (TextView) v.findViewById(R.id.tv_title);
            tvStreet = (TextView) v.findViewById(R.id.tv_street);
            tvLike = (TextView) v.findViewById(R.id.tv_like);
            tvDate = (TextView) v.findViewById(R.id.tv_date);
            tvDay = (TextView) v.findViewById(R.id.tv_day);
        }
    }

    public RecyclerAdapter(ArrayList<CardModel> arrCardModel, String status) {
        mArrCardModel = arrCardModel;
        mStatus = status;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvTitle.setText(mArrCardModel.get(position).getTitle());
        holder.tvStreet.setText(mArrCardModel.get(position).getStreet());
        holder.tvLike.setText(mArrCardModel.get(position).getCountLike() + "");
        holder.tvDate.setText(mArrCardModel.get(position).getDate());
        holder.tvDay.setText(mArrCardModel.get(position).getDay() + "" + holder.tvDay.getContext().getString(R.string.rec_day)); //[Comment] use string.format

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(holder.cv.getContext(), ItemActivity.class);
                myIntent.putExtra(Const.IntentConstant.TITLE, mArrCardModel.get(position).getTitle());
                myIntent.putExtra(Const.IntentConstant.DATE, mArrCardModel.get(position).getDate());
                myIntent.putExtra(Const.IntentConstant.STATUS, mStatus);
                holder.cv.getContext().startActivity(myIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArrCardModel.size();
    }


}

