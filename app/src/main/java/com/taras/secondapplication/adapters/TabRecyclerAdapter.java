package com.taras.secondapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.activities.item.ItemActivity;
import com.taras.secondapplication.models.CardModel;
import com.taras.secondapplication.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabRecyclerAdapter extends RecyclerView.Adapter<TabRecyclerAdapter.ViewHolder> {

    private List<CardModel> mCardModels;

    public TabRecyclerAdapter(List<CardModel> taskList) {
        mCardModels = taskList;
    }

    @Override
    public TabRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvLike.setText(mCardModels.get(position).getLikes_counter());
        holder.tvTitle.setText(mCardModels.get(position).getTitle());
        holder.tvStreet.setText(getAddress(position, holder.tvStreet.getContext()));
        holder.tvDate.setText(DateUtils.parseLongToDate(holder.tvDate.getContext(), mCardModels.get(position).getCreated_date()));
        holder.tvDay.setText(DateUtils.parseLongToDayAgo(holder.tvDay.getContext(),mCardModels.get(position).getCreated_date()));

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemIntent = new Intent(holder.cvItem.getContext(), ItemActivity.class);
                itemIntent.putExtra(Const.IntentConstant.ID, (int) mCardModels.get(position).getId());
                holder.cvItem.getContext().startActivity(itemIntent);
            }
        });
    }

    private String getAddress(int position, Context context) {
        if (mCardModels.get(position).getUser().getAddress().getStreet() != null) {
            return mCardModels.get(position).getStreet();
        } else {
            return context.getString(R.string.tab_no_addresses);
        }
    }

    @Override
    public int getItemCount() {
        return mCardModels.size();
    }

    /**
     * Class to hold recycleView items.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_filename)
        CardView cvItem;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_street)
        TextView tvStreet;
        @BindView(R.id.tv_like)
        TextView tvLike;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_day)
        TextView tvDay;

        private ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}

