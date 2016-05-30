package com.taras.secondapplication.utils;

import android.content.Context;
import android.text.format.DateFormat;

import com.taras.secondapplication.R;

import java.util.Date;

public class DateUtils {

    public static final long MILLISECONDS_IN_SECOND = 1000L;
    public static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * MILLISECONDS_IN_SECOND;

    public static String parseLongToDayAgo(Context context, long date) {
        long millisDiff = System.currentTimeMillis() - date * MILLISECONDS_IN_SECOND;;
        int daysDiff = (int) (millisDiff / MILLISECONDS_IN_DAY);
        return context.getString(
                R.string.how_many_days_ago,
                daysDiff,
                context.getResources().getQuantityString(R.plurals.days_plurals, daysDiff)
        );
    }

    public static String parseLongToDate(Context context, long date) {
        long milliseconds = date * MILLISECONDS_IN_SECOND;
        return DateFormat.getMediumDateFormat(context).format(new Date(milliseconds));
    }
}
