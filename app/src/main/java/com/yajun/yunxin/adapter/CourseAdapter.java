package com.yajun.yunxin.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yajun.yunxin.R;
import com.yajun.yunxin.common.ImageLoader;
import com.yajun.yunxin.model.CourseModel;

import butterknife.Bind;

/**
 * Created by yajun on 2016/9/8.
 *
 */
public class CourseAdapter extends BaseModelAdapter<CourseModel> {


    public CourseAdapter(Context paramContext) {
        super(paramContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_course;
    }

    @Override
    public void display(int position, BaseViewHolder viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        CourseModel item = getItem(position);
        holder.mCourseName.setText(item.getName());
        holder.mCourNumber.setText(item.getNumbers());
        holder.mCourseUpdate.setText("持续时间:" + item.getDuration());
        ImageLoader.display(context,item.getPic(),holder.mCourseIcon);
    }

    @Override
    public BaseViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder{
        @Bind(R.id.mCourseIcon)
        ImageView mCourseIcon;
        @Bind(R.id.mCourseName)
        TextView mCourseName;
        @Bind(R.id.mCourNumber)
        TextView mCourNumber;
        @Bind(R.id.mCourseUpdate)
        TextView mCourseUpdate;

        public ViewHolder(View view) {
            super(view);
        }
    }


}
