package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by LinLin on 2015/12/28.
 */
public class LoadingLayout extends FrameLayout
{
    TextView errorTextView;
    TextView emptyTextView;
    ProgressWheel progressBar;
    FrameLayout frameLayout;

    State mState = State.Loading;

    public LoadingLayout(Context context)
    {
        super(context);
        init(context);
    }

    public LoadingLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context)
    {
        FrameLayout.LayoutParams params = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        errorTextView = new TextView(context);
        emptyTextView = new TextView(context);
        frameLayout = new FrameLayout(context);
        progressBar = new ProgressWheel(context);

        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48f, getResources().getDisplayMetrics());
        int barw = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, getResources().getDisplayMetrics());
        progressBar.setBarColor(Color.BLUE);
        progressBar.spin();
        progressBar.setCircleRadius(size);
        progressBar.setBarWidth(barw);

        errorTextView.setGravity(Gravity.CENTER);
        emptyTextView.setGravity(Gravity.CENTER);

        errorTextView.setVisibility(GONE);
        emptyTextView.setVisibility(GONE);
        frameLayout.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);

        addView(errorTextView, params);
        addView(emptyTextView, params);
        addView(frameLayout, params);
        addView(progressBar, params);
    }

    public TextView getErrorTextView()
    {
        return errorTextView;
    }

    public TextView getEmptyTextView()
    {
        return emptyTextView;
    }

    public ProgressWheel getProgressBar()
    {
        return progressBar;
    }

    public FrameLayout getFrameLayout()
    {
        return frameLayout;
    }

    public void setState(State state)
    {
        this.mState = state;
        switch (state)
        {
            case Loading:
                errorTextView.setVisibility(GONE);
                emptyTextView.setVisibility(GONE);
                frameLayout.setVisibility(GONE);
                progressBar.setVisibility(VISIBLE);
                break;
            case Error:
                errorTextView.setVisibility(VISIBLE);
                emptyTextView.setVisibility(GONE);
                frameLayout.setVisibility(GONE);
                progressBar.setVisibility(GONE);
                break;
            case Empty:
                errorTextView.setVisibility(GONE);
                emptyTextView.setVisibility(VISIBLE);
                frameLayout.setVisibility(GONE);
                progressBar.setVisibility(GONE);
                break;
            case Completed:
                errorTextView.setVisibility(GONE);
                emptyTextView.setVisibility(GONE);
                frameLayout.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                break;
        }
    }

    public State getState()
    {
        return mState;
    }

    public static enum State
    {
        Loading, Error, Empty, Completed
    }
}
