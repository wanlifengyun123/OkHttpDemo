package com.yajun.yunxin.helper;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.yajun.yunxin.util.LogUtil;
import com.yajun.yunxin.util.ToastUtil;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by yajun on 2016/9/27.
 *
 * 常量
 * static final int VIDEO_LAYOUT_ORIGIN* 缩放参数，原始画面大小。* 常量值：0
 * static final int VIDEO_LAYOUT_SCALE* 缩放参数，画面全屏。* 常量值：1
 * static final int VIDEO_LAYOUT_STRETCH* 缩放参数，画面拉伸。* 常量值：2
 * static final int VIDEO_LAYOUT_ZOOM* 缩放参数，画面裁剪。* 常量值：3
 * void setVideoLayout(int layout, float aspectRatio)* 设置视频的缩放参数* 参数* layout 缩放参数* aspectRation 宽高比，为0将自动检测。
 * void setVideoPath(String path)* 设置视频路径。
 * void setVideoURI(Uri uri)* 设置视频URI。（可以是网络视频地址）
 * void setMediaController(MediaController controller)* 设置媒体控制器。* 参数* controller 媒体控制器，注意是io.vov.vitamio.widget.MediaController。
 * void setOnPreparedListener(OnPreparedListener l)* 注册一个回调函数，在视频预处理完成后调用。在视频预处理完成后被调用。此时视频的宽度、高度、宽高比信息已经获取到，此时可调用seekTo让视频从指定位置开始播放。
 * void setOnCompletionListener(OnCompletionListener l)* 注册一个回调函数，视频播放完成后调用。
 *  void setVideoLayout(int layout, float aspectRatio)* 获取扫描视频的Uri。* 参数* layout 缩放参数* aspectRation 宽高比，为0将自动检测。
 * boolean isValid()* Surface是否有效。 参见Surface的isValid方法。
 * void setVideoPath(String path)* 设置视频路径。
 * void setVideoURI(Uri uri)* 设置视频URI。（可以是网络视频地址）
 * void stopPlayback()* 停止视频播放，并释放资源。
 * void setMediaController(MediaController controller)* 设置媒体控制器。* 参数* controller 媒体控制器，注意是io.vov.vitamio.widget.MediaController。
 * void setOnPreparedListener(OnPreparedListener l)* 注册一个回调函数，在视频预处理完成后调用。在视频预处理完成后被调用。此时视频的宽度、高度、宽高比信息已经获取到，此时可调用seekTo让视频从指定位置开始播放。
 * void setOnCompletionListener(OnCompletionListener l)* 注册一个回调函数，视频播放完成后调用。
 * void setOnErrorListener(OnErrorListener l)* 注册一个回调函数，在异步操作调用过程中发生错误时调用。例如视频打开失败。
 * void setOnBufferingUpdateListener(OnBufferingUpdateListener l)* 注册一个回调函数，在网络视频流缓冲变化时调用。
 * void setOnSeekCompleteListener(OnSeekCompleteListener l)* 注册一个回调函数，在seek操作完成后调用。
 * void setOnSubtitleUpdateListener(OnSubtitleUpdateListener l)* 注册一个回调函数，在字幕需要显示时调用。
 * void setOnInfoListener(OnInfoListener l)* 注册一个回调函数，在有警告或错误信息时调用。例如：开始缓冲、缓冲结束、下载速度变化。
 * boolean onTouchEvent(MotionEvent ev)* 处理显示/隐藏MediaController。
 * void start()* 开始播放。
 * void pause()* 暂停播放。
 * void suspend()* 挂起（暂时没有实现功能）
 * void resume()* 恢复播放。
 * long getDuration()* 获取视频播放时长。
 * long getCurrentPosition()* 获取当前播放位置。
 * void seekTo(long msec)* 设置播放位置。* 参数* msec 位置
 * boolean isPlaying()* 是否正在播放。
 * int getBufferPercentage()* 获取缓冲百分比。
 * void setVolume(float leftVolume, float rightVolume)* 设置音量。* 参数* leftVolume 左声道* rightVolume 右声道
 * int getVideoWidth()* 获取视频宽度。
 * int getVideoHeight()* 获取视频高度。
 * float getVideoAspectRatio()* 设置视频宽高比例。没有视频或者宽高不正确返回0。
 * void setVideoQuality(int quality)* 设置视频质量。* 参数* quality 参见MediaPlayer的常量：VIDEOQUALITY_LOW（流畅）、VIDEOQUALITY_MEDIUM（普通）、VIDEOQUALITY_HIGH（高质）。
 * void setBufferSize(int bufSize)* 设置视频缓冲大小（默认1024KB）单位Byte。
 * boolean isBuffering()* 检测是否缓冲完毕。
 * void setMetaEncoding(String encoding)* 设置元数据编码。例如：UTF-8
 * String getMetaEncoding()* 获取元数据编码。
 * HashMap getAudioTrackMap(String encoding)* 获取视频中嵌入的音轨。例如：English
 * int getAudioTrack()* 设置播放音轨编号。
 * void setAudioTrack(int audioIndex)* 设置音轨编号，必须使用getAudioTrackMap的返回值。
 * void setSubShown(boolean shown)* 设置是否显示字幕。* 参数* shown true表示显示字幕
 * void setSubEncoding(String encoding)* 设置字幕编码。* 参数* encoding 字幕编码。如果为null将自动检测。
 * int getSubLocation()* 获取字幕位置类型。0为内嵌字幕，1为外挂字幕。
 * void setSubPath(String subPath)* 设置外挂字幕路径。必须是本地文件路径。
 * String getSubPath()* 获取外挂字幕路径。
 * void setSubTrack(int trackId)* 设置字幕编号。必须是getSubTrackMap的返回值。
 * int getSubTrack()* 获取字幕编号。
 * HashMap getSubTrackMap(String encoding)* 获取视频内嵌字幕集合。* 参数* encoding 格式化字符串编码。如果为null将自动检测。* 返回值* 返回字幕名称和字幕编号组成的Map。
 * boolean canPause()* 是否可暂停。（暂时没有实现功能）
 * boolean canSeekBackward()* （暂时没有实现功能）
 * boolean canSeekForward()* （暂时没有实现功能）
 * 受保护方法
 * protected boolean isInPlaybackState()* 是否处于正在播放的状态。
 *
 */
public class VitamioHelper {

    private Context mContext;
    private VideoView mVideoView;

    private View mLoadingView;

    /** 播放位置 */
    private int currentPosition;
    /** 是否播放完成 */
    private boolean isPlayComplete = false;
    /** 是否播放出错 */
    private boolean isPlayError = false;
    /** 播放地址 */
    private Uri playUri = null;

    private int currentVideoLayout = VideoView.VIDEO_LAYOUT_SCALE;

    static VitamioHelper instance;

    public static VitamioHelper getInstance(Context context){
        if(instance == null){
            instance = new VitamioHelper(context);
        }
        return instance;
    }

    public VitamioHelper (Context context){
        this.mContext = context;
    }

    private void initVideoView(){
        Vitamio.isInitialized(mContext);
        // 设置加载V
//        mVideoView.setMediaBufferingIndicator();
        ////视频播放器的准备,此时播放器已经准备好了,此处可以设置一下播放速度,播放位置等等
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //此处设置播放速度为正常速度1
                mediaPlayer.setPlaybackSpeed(1.0f);
                onVideoPlay();
                if (currentPosition != 0
                        && currentPosition < mVideoView.getDuration()) {
                    // 若不调用 VideoView.getDuration() 就进行 seekTo() 那么 seekTo() 无效！
                    // vitamio5.0 的问题？
                    mVideoView.seekTo(currentPosition);
                }

            }
        });
        //当播放完成后,从头开始
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPlayComplete = true;
                mediaPlayer.seekTo(0);   //转到第一帧
                mediaPlayer.start();     //开始播放
            }
        });
        //在异步操作调用过程中发生错误时调用。例如视频打开失败。
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ToastUtil.show("视频打开失败");
                isPlayError = true;
                LogUtil.e(VitamioHelper.class,"VideoView on error! what:" + what + " extra:" + extra);
                return true;
            }
        });
        //在网络视频流缓冲变化时调用
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if (percent > 0 && percent < 100) {
//                    mLoadingInfo.setText(percent + "%");
                } else {
//                    mLoadingInfo.setText("");
                }
            }
        });
        //在seek操作完成后调用
        mVideoView.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                onVideoPlay();
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

    }

    /**
     * 根据播放状态改变 切换播放图标
     */
    private void onPlaySRCChange(boolean isPlaying) {
//        if (isPlaying) {
//            mVideoPlayPause.setImageResource(R.drawable.play_pause_icon);
//        } else {
//            mVideoPlayPause.setImageResource(R.drawable.play_start_icon);
//        }
    }

    /**
     * 暂停
     */
    private void onVideoPause() {
        mVideoView.pause();
        onPlaySRCChange(false);

    }

    /**
     * 播放
     */
    private void onVideoPlay() {
        mVideoView.start();
        onPlaySRCChange(true);

    }

    /**
     * 点击播放按钮
     */
    private void onClickPlayButton() {
       
        if (isPlayComplete) {
            currentPosition = 0;
            mVideoView.setVideoURI(playUri);
            isPlayComplete = false;
        } else {
            if (mVideoView.isValid()) {
                if (mVideoView.isPlaying()) {
                    onVideoPause();
                } else {
                    onVideoPlay();
                }
            }
        }
    }

    /**
     * 切换视频布局
     */
    private void toggleVideoLayout() {
        currentVideoLayout++;
        if (currentVideoLayout > VideoView.VIDEO_LAYOUT_ZOOM) {
            currentVideoLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
        }
        mVideoView.setVideoLayout(currentVideoLayout, 0);
    }


}
