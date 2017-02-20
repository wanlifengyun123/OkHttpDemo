package com.yajun.yunxin.activity;

import com.yajun.yunxin.R;
import com.yajun.yunxin.adapter.ChapterListAdapter;
import com.yajun.yunxin.dialog.DialogMaker;
import com.yajun.yunxin.model.ChapterModel;
import com.yajun.yunxin.model.CourseModel;
import com.yajun.yunxin.net.OkHttpUtil;
import com.yajun.yunxin.net.event.BaseBusEvent;
import com.yajun.yunxin.net.event.ChapterEvent;
import com.yajun.yunxin.net.iml.UserService;
import com.yajun.yunxin.view.PinnedSectionListView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by yajun on 2016/9/27.
 *
 */
public class VitamioPlayActivity extends BaseActivity {

    @Bind(R.id.mVideoView)
    VideoView mVideoView;
    @Bind(R.id.mSectionListView)
    PinnedSectionListView mSectionListView;

    ChapterListAdapter adapter;

    CourseModel courseModel;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vitamio;
    }

    @Override
    protected void initView() {
        adapter = new ChapterListAdapter(this);
        mSectionListView.setAdapter(adapter);

        DialogMaker.showProgressDialog(getSupportFragmentManager(),"");
        UserService.getChapterByCourseId(courseModel.getId());
    }

    @Subscribe
    public void getChapterList(ChapterEvent event){
        DialogMaker.dismissProgressDialog();
        List<ChapterModel> chapterModels = OkHttpUtil.getData(this,event);
        if(chapterModels != null){
            adapter.getChapterModels().clear();
            adapter.getChapterModels().addAll(chapterModels);
            adapter.notifyDataSetChanged();
        }
    }
}
