package com.yajun.yunxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yajun.yunxin.R;
import com.yajun.yunxin.model.ChapterModel;
import com.yajun.yunxin.view.PinnedSectionListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yajun on 2016/9/27.
 *
 */
public class ChapterListAdapter  extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    private static final int TYPE_CHAPTER_ITEM = 0;
    private static final int TYPE_ITEM = 1;

    private Context mContext;
    private List<ChapterModel> chapterModels = new ArrayList<>();

    private String mGroupseqid = "";

    public List<ChapterModel> getChapterModels() {
        return chapterModels;
    }

    public ChapterListAdapter (Context context ){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != chapterModels) {
            //  所有分类中item的总和是ListVIew  Item的总个数
            for (ChapterModel chapterModel : chapterModels) {
                count += chapterModel.getItemCount();
            }
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        // 异常情况处理
        if (null == chapterModels || position <  0|| position > getCount()) {
            return null;
        }

        // 同一分类内，第一个元素的索引值
        int categroyFirstIndex = 0;
        for (ChapterModel chapterModel : chapterModels) {
            int size = chapterModel.getItemCount();
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            // item在当前分类内
            if (categoryIndex < size) {
                return  chapterModel.getItem( categoryIndex );
            }

            // 索引移动到当前分类结尾，即下一个分类第一个元素索引
            categroyFirstIndex += size;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if (null == chapterModels || position <  0|| position > getCount()) {
            return TYPE_ITEM;
        }

        int categroyFirstIndex = 0;

        for (ChapterModel chapterModel : chapterModels) {
            int size = chapterModel.getItemCount();
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            if (categoryIndex == 0) {
                return TYPE_CHAPTER_ITEM;
            }
            categroyFirstIndex += size;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_CHAPTER_ITEM:
                if (null == convertView) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chapter_group_head,parent,false);
                }
                TextView tvHeader = (TextView) convertView.findViewById(R.id.mChapterHeader);
                ChapterModel.ChapterBean chapterBean = (ChapterModel.ChapterBean) getItem(position);
                tvHeader.setText(chapterBean.getName());
                mGroupseqid = chapterBean.getSeqid();
                break;
            case TYPE_ITEM:
                ViewHolder viewHolder = null;
                if (null == convertView) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chapter_child,parent,false);
                    viewHolder = new ViewHolder();
                    viewHolder.tvName = (TextView) convertView.findViewById(R.id.mChapterChildName);
                    viewHolder.tvTime = (TextView) convertView.findViewById(R.id.mChapterChildTime);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                ChapterModel.MediaBean mediaBean = (ChapterModel.MediaBean) getItem(position);
                viewHolder.tvName.setText(mGroupseqid + "-" + mediaBean.getSeqid() + "" + mediaBean.getName());
                viewHolder.tvTime.setText( mediaBean.getChapter_id());
                break;
        }
        return convertView;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == TYPE_CHAPTER_ITEM;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) == TYPE_CHAPTER_ITEM;
    }

    class ViewHolder {
        TextView tvName;
        TextView tvTime;
    }
}
