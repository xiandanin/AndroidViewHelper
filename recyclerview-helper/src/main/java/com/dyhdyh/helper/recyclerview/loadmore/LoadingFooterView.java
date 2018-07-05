package com.dyhdyh.helper.recyclerview.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyhdyh.helper.recyclerview.R;

/**
 * @author dengyuhan
 *         created 2018/7/4 14:14
 */
public class LoadingFooterView extends RelativeLayout implements LoadMoreView {
    private ProgressBar mProgressBar;
    private TextView mLabelTextView;
    private TextView mSymbolView;

    public LoadingFooterView(Context context) {
        this(context, null);
    }

    public LoadingFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.recyclerview_helper_layout_loadmore, this);
        mProgressBar = findViewById(R.id.pb_loadmore);
        mLabelTextView = findViewById(R.id.tv_loadmore_label);
        mSymbolView = findViewById(R.id.tv_loadmore_symbol);
        buildViewStyle(mProgressBar, mLabelTextView, mSymbolView);
    }

    protected void buildViewStyle(ProgressBar progress, TextView label, TextView symbol) {

    }

    @Override
    public void setLoadMoreState(int state) {
        if (LoadMoreView.GONE == state) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
            if (LoadMoreView.LOADING == state) {
                mProgressBar.setVisibility(View.VISIBLE);
                mSymbolView.setVisibility(View.GONE);
                mLabelTextView.setText(R.string.label_loading_footer_loading);
            } else if (LoadMoreView.THE_END == state) {
                mProgressBar.setVisibility(View.GONE);
                mSymbolView.setVisibility(View.GONE);
                mLabelTextView.setText(R.string.label_loading_footer_end);
            } else if (LoadMoreView.ERROR == state) {
                mProgressBar.setVisibility(View.GONE);
                mSymbolView.setVisibility(View.VISIBLE);
                mLabelTextView.setText(R.string.label_loading_footer_error);
            }
        }
    }

    @Override
    public View getView() {
        return this;
    }
}
