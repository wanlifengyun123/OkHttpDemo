package com.yajun.yunxin.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.yajun.yunxin.R;
import com.yajun.yunxin.adapter.CourseAdapter;
import com.yajun.yunxin.model.CourseModel;
import com.yajun.yunxin.net.OkHttpUtil;
import com.yajun.yunxin.net.event.CourseEvent;
import com.yajun.yunxin.net.iml.UserService;
import com.yajun.yunxin.view.playview.SlidingPlayView;
import com.yajun.yunxin.view.pullview.PullToRefreshView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterLoadListener{

    private static final String EXTRA_APP_QUIT = "APP_QUIT";

    private static final int[] bannerRes = {
            R.drawable.compat_banner_one,
            R.drawable.compat_banner_three,
            R.drawable.compat_banner_four
    };

    @Bind(R.id.play_view)
    SlidingPlayView mSlidingPlayView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.mListView)
    ListView mListView;
    @Bind(R.id.mPullRefreshView)
    PullToRefreshView mPullRefreshView;

    int page;
    CourseAdapter adapter;
    int mLastItemPosition;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, false);
        initBanner();
        initListView();
    }

    private void initListView() {
        // 设置监听器
        mPullRefreshView.setOnHeaderRefreshListener(this);
        mPullRefreshView.setOnFooterLoadListener(this);

        adapter = new CourseAdapter(this);
        mListView.setAdapter(adapter);

        mPullRefreshView.headerRefreshing();
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == SCROLL_STATE_IDLE){
                    Glide.with(MainActivity.this).resumeRequests();
                }else {
                    Glide.with(MainActivity.this).pauseRequests();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void initToolBar(Toolbar toolbar, boolean isBack) {
        super.initToolBar(toolbar, isBack);
        toolbar.setTitle(R.string.app_name);
    }

    private void initBanner() {
        mSlidingPlayView.removeAllViews();
        mSlidingPlayView.setNavPageResources(R.drawable.play_display, R.drawable.play_hide);
        mSlidingPlayView.setNavHorizontalGravity(Gravity.RIGHT);
        ImageView imageView;
        for (int bannerRe : bannerRes) {
            imageView = new ImageView(this);
            imageView.setImageResource(bannerRe);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mSlidingPlayView.addView(imageView);
        }
        mSlidingPlayView.startPlay();
    }


    private void refreshData(){
        page = 0;
        mLastItemPosition = 0;
        UserService.getAllCourse(page);
    }

    private void loadMoreData(){
        page++;
        mLastItemPosition = adapter.getCount();
        UserService.getAllCourse(page);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCourseList(CourseEvent event){
        mPullRefreshView.onFooterLoadFinish();
        mPullRefreshView.onHeaderRefreshFinish();
        List<CourseModel> courseModels = OkHttpUtil.getData(this,event);
        if(courseModels != null){
            if(page == 0){
                adapter.getData().clear();
            }
            adapter.getData().addAll(courseModels);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFooterLoad(PullToRefreshView view) {
        loadMoreData();

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        refreshData();
    }

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    // 注销
    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }


}
