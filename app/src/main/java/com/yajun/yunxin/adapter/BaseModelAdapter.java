package com.yajun.yunxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yajun on 2016/9/8.
 *
 */
public abstract class BaseModelAdapter<T> extends BaseAdapter {

    protected Context context;
    protected LayoutInflater inflater;
    private List<T> data = new ArrayList<>();

    private final Object mLock = new Object();

    public List<T> getData() {
        return data;
    }

    public BaseModelAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void remove(T object) {
        synchronized (mLock) {
            if (data != null) {
                data.remove(object);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null?0:data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = null;
        if (convertView == null ){
            convertView = inflater.inflate(getLayoutId(), null);
            baseViewHolder = getViewHolder(convertView);
            convertView.setTag(baseViewHolder);
        }else{
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }
        display(position, baseViewHolder);
        return convertView;
    }

    public abstract int getLayoutId();

    public abstract void display(int position, BaseViewHolder viewHolder);

    public abstract BaseViewHolder getViewHolder(View view);

    public abstract class BaseViewHolder{
        public BaseViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
