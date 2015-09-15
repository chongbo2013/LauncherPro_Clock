package com.example.administrator.flylauncherpro_clockview;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;

import java.util.Calendar;

/**
 * TODO: ferris 处理时钟逻辑
 */
public class ClockView extends ClockViewBase {
    private String[] months ;
    private String[] weeks ;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){
       months = getContext().getResources().getStringArray(R.array.mounth);
       weeks = getContext().getResources().getStringArray(R.array.week);
        timeChage();
    }
    @Override
    public void timeChage() {
        invalidateTextPaintAndMeasurementsTime(getTime());
        invalidateTextPaintAndMeasurementsDate(getDate());
        postInvalidate();
    }

    private String getTime() {
        StringBuilder builder = new StringBuilder();
        // 时间
        boolean format24 = DateFormat.is24HourFormat(getContext());
        Calendar c = Calendar.getInstance();
        int hour12 = c.get(Calendar.HOUR);
        int hour24 = c.get(Calendar.HOUR_OF_DAY);

        if (format24) {
            builder.append(hour24);
        } else {
            hour12 = hour12 == 0 ? 12 : hour12;
            builder.append(hour12);
        }
        builder.append(":");
        int minute = c.get(Calendar.MINUTE);
        builder.append(minute);
        return builder.toString();
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.DAY_OF_WEEK); // 1-7
        int month = c.get(Calendar.MONTH) + 1;  // 1-12
        int day = c.get(Calendar.DAY_OF_MONTH); // 1-30
        return getDate(getMonth(month), getDay(day), getWeek(week));
    }

    private String getDate(String mMonth, String mDay, String mWeek) {
        StringBuilder builder = new StringBuilder();
        // 语言判断
        String week = weeks[0];
        if (week.equals("周")) {
            builder.append(mMonth)
                    .append(mDay)
                    .append(" ")
                    .append(mWeek);
        } else {
            builder.append(mWeek)
                    .append(" ")
                    .append(mMonth)
                    .append(".")
                    .append(mDay);
        }
        return builder.toString();
    }

    public String getMonth(int month) {
        if (month < 1 || month > 12) {
            return "";
        }

        if (months != null && months.length > month) {
            return months[month];
        }

        return "";
    }

    public String getWeek(int week) {
        if (week < 1 || week > 7) {
            return "";
        }

        if (weeks != null && weeks.length > week) {
            return weeks[week];
        }
        return "";
    }

    public String getDay(int day) {
        if (day < 1 || day > 31) {
            return "";
        }
        return String.valueOf(day);

    }
}
