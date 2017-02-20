package com.yajun.yunxin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yajun.yunxin.activity.BaseActivity;

/**
 * Created by yajun on 2016/9/22.
 *
 */
public abstract class BaseFragment extends Fragment {

    private BaseActivity mActivity;

    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView != null) {
            View oldParent = (View) rootView.getParent();
            if(oldParent != container) {
                ((ViewGroup) oldParent).removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(getLayoutId(),container,false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);

    //获取布局文件ID
    protected abstract int getLayoutId();
}
