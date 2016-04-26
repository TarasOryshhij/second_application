package com.taras.secondapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.activity.ItemActivity;
import com.taras.secondapplication.models.CardModel;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<CardModel> mCardModelArrayList = new ArrayList<>(); //[Comment] Use abstraction instead of realization
    private String mStatus;

    public ListAdapter(Context context, ArrayList<CardModel> cardModelArrayList, String status) {
        mContext = context;
        mCardModelArrayList = cardModelArrayList;
        mStatus = status;
    }

    static class ViewHolder {
        private CardView cv;
        private TextView tvTitle;
        private TextView tvStreet;
        private TextView tvLike;
        private TextView tvDate;
        private TextView tvDay;

        private ViewHolder(View v) {
            cv = (CardView) v.findViewById(R.id.cv);
            tvTitle = (TextView) v.findViewById(R.id.tv_title);
            tvStreet = (TextView) v.findViewById(R.id.tv_street);
            tvLike = (TextView) v.findViewById(R.id.tv_like);
            tvDate = (TextView) v.findViewById(R.id.tv_date);
            tvDay = (TextView) v.findViewById(R.id.tv_day);
        }
    }

    @Override
    public CardModel getItem(int i) {
        return mCardModelArrayList.get(i);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(getItem(position).getTitle());
        viewHolder.tvStreet.setText(getItem(position).getStreet());
        viewHolder.tvLike.setText(getItem(position).getCountLike() + "");
        viewHolder.tvDate.setText(getItem(position).getDate());
        viewHolder.tvDay.setText(getItem(position).getDay() + viewHolder.tvDay.getContext().getString(R.string.rec_day));

        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(viewHolder.cv.getContext(), ItemActivity.class);
                myIntent.putExtra(Const.IntentConstant.TITLE, mCardModelArrayList.get(position).getTitle());
                myIntent.putExtra(Const.IntentConstant.DATE, mCardModelArrayList.get(position).getDate());
                myIntent.putExtra(Const.IntentConstant.STATUS, mStatus);
                viewHolder.cv.getContext().startActivity(myIntent);
            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return mCardModelArrayList.size();
    }


}
