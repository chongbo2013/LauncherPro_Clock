package com.example.administrator.flylauncherpro_clockview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: ferris 自定义时钟 处理UI
 */
public class ClockViewBase extends View {
    protected String LoadingString; // 加载中描述
    protected int TimeTextColor = Color.RED; //时间数字的颜色
    protected float TimeTextSize = 0; //时间数字的字体大小
    protected float TimeTextWidth;
    protected float TimeTextHeight;
    protected TextPaint mTimePaint;


    protected int DateTextColor = Color.RED; //时间数字的颜色
    protected float DateTextSize = 0; //时间数字的字体大小
    protected float DateTextWidth;
    protected float DateTextHeight;
    protected TextPaint mDatePaint;
    //日期和时间的pading
    protected float padTextSize;
    protected Drawable ClockDrawable;//时钟的整个背景颜色
    protected int ShadowColor=Color.BLACK;
    protected String dateString="";
    protected String timeString="";


    public ClockViewBase(Context context) {
        super(context);
        init(null, 0);
    }

    public ClockViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ClockViewBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ClockView, defStyle, 0);
        //时钟加载提醒
        LoadingString = a.getString(
                R.styleable.ClockView_LoadingString);

        TimeTextColor = a.getColor(
                R.styleable.ClockView_TimeTextColor,
                TimeTextColor);


        TimeTextSize = a.getDimension(
                R.styleable.ClockView_TimeTextSize,
                TimeTextSize);


        DateTextColor = a.getColor(
                R.styleable.ClockView_DateTextColor,
                DateTextColor);

        DateTextSize = a.getDimension(
                R.styleable.ClockView_DateTextSize,
                DateTextSize);
        padTextSize = a.getDimension(
                R.styleable.ClockView_padTextSize,
                padTextSize);
        ShadowColor = a.getColor(
                R.styleable.ClockView_ShadowColor,
                ShadowColor);
        if (a.hasValue(R.styleable.ClockView_ClockDrawable)) {
            ClockDrawable = a.getDrawable(
                    R.styleable.ClockView_ClockDrawable);
            ClockDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTimePaint = new TextPaint();
        mTimePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTimePaint.setTextAlign(Paint.Align.LEFT);

        mDatePaint = new TextPaint();
        mDatePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mDatePaint.setTextAlign(Paint.Align.LEFT);


    }


    public void timeChage(){

    }
    //初始化时间字体大小
    protected void invalidateTextPaintAndMeasurementsTime(String mTime) {
        timeString=mTime;
        mTimePaint.setTextSize(TimeTextSize);
        mTimePaint.setColor(TimeTextColor);
        mTimePaint.setFakeBoldText(true);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        mTimePaint.setShadowLayer(5, 3, 3, ShadowColor);
        TimeTextWidth = mTimePaint.measureText(timeString);
        Paint.FontMetrics fontMetrics = mTimePaint.getFontMetrics();
        TimeTextHeight = fontMetrics.bottom;
    }

    //初始化日期字体的大小
    protected void invalidateTextPaintAndMeasurementsDate(String mDate) {
        dateString=mDate;
        mDatePaint.setTextSize(DateTextSize);
        mDatePaint.setColor(DateTextColor);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        mDatePaint.setShadowLayer(5, 3, 3, ShadowColor);
        DateTextWidth = mDatePaint.measureText(dateString);
        Paint.FontMetrics fontMetrics = mDatePaint.getFontMetrics();
        DateTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        int centeYLine = paddingTop + contentHeight / 2;//中心线

        // Draw the time.
        canvas.drawText(timeString,
                paddingLeft + (contentWidth - TimeTextWidth) / 2,
                centeYLine-padTextSize/2-TimeTextHeight,
                mTimePaint);

        //Draw the date
        canvas.drawText(dateString,
                paddingLeft + (contentWidth - DateTextWidth) / 2,
                centeYLine+padTextSize/2,
                mDatePaint);

        // Draw the clock drawable on top of the text.
        if (ClockDrawable != null) {
            ClockDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            ClockDrawable.draw(canvas);
        }
    }


    public int getTimeTextColor() {
        return TimeTextColor;
    }

    public void setTimeTextColor(int exampleColor) {
        TimeTextColor = exampleColor;
        timeChage();
    }

    public float getTimeDimension() {
        return TimeTextSize;
    }

    public void setTimeDimension(float exampleDimension) {
        TimeTextSize = exampleDimension;
        timeChage();
    }

    public int getDateTextColor() {
        return DateTextColor;
    }

    public void setDateTextColor(int exampleColor) {
        DateTextColor = exampleColor;
        timeChage();
    }

    public float getDateDimension() {
        return DateTextSize;
    }

    public void setDateDimension(float exampleDimension) {
        DateTextSize = exampleDimension;
        timeChage();
    }


    public Drawable getClockDrawable() {
        return ClockDrawable;
    }

    public void setClockDrawable(Drawable exampleDrawable) {
        ClockDrawable = exampleDrawable;
    }
}
