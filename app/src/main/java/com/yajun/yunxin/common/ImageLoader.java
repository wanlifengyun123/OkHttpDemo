package com.yajun.yunxin.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yajun on 2016/9/23.
 *
 */
public class ImageLoader {

    /**
     * .load(String string)	string可以为一个文件路径、uri或者url
     * .load(Uri uri)	uri类型
     * .load(File file)	文件
     * .load(Integer resourceId)	资源Id,R.drawable.xxx或者R.mipmap.xxx
     *
     * .placeholder(R.mipmap.ic_launcher) //设置占位图
     * .error(R.mipmap.future_studio_launcher) //设置错误图片
     * .crossFade() //设置淡入淡出效果，默认300ms，可以传参
     * .override(600, 200)
     * .CenterCrop：等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。
     * .FitCenter：等比例缩放图片，宽或者是高等于ImageView的宽或者是高。
     * .asGif() 加载GIF和视频文件
     * .skipMemoryCache( true )开启内存缓存
     * .diskCacheStrategy( DiskCacheStrategy.NONE )设置图片不加入到磁盘缓存
     * .DiskCacheStrategy.NONE :不缓存图片
     * .DiskCacheStrategy.SOURCE :缓存图片源文件
     * .DiskCacheStrategy.RESULT:缓存修改过的图片
     * .DiskCacheStrategy.ALL:缓存所有的图片，默认
     * .priority( Priority.HIGH )加载优先级
     * @param url
     * @param imageView
     */
    public static void display(Context context,String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    public static void displayCircle(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    public static void displayRound(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .transform(new GlideRoundTransform(context))
                .into(imageView);
    }
}
