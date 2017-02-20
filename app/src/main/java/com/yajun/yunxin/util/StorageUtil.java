package com.yajun.yunxin.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.yajun.yunxin.App;

import java.io.File;

/**
 * Created by yajun on 2016/9/22.
 *
 */
public class StorageUtil {

    /**
     * 外部存储根目录
     */
    private static String sdkStorageRoot = null;

    static Context mContext;
    static {
        mContext = App.getInstance();
        sdkStorageRoot = Environment.getExternalStorageDirectory() + "/" + mContext.getPackageName() + "/";
    }

    public static void init(){
        File dir = new File(sdkStorageRoot);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (StorageType storageType : StorageType.values()) {
            makeDirectory(sdkStorageRoot + storageType.getPath());
        }
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    private static boolean makeDirectory(String path) {
        File file = new File(path);
        boolean exist = file.exists();
        if (!exist) {
            exist = file.mkdirs();
        }
        return exist;
    }

    /**
     * 文件全名转绝对路径（写）
     *
     * @param fileName
     *            文件全名（文件名.扩展名）
     * @return 返回绝对路径信息
     */
    public String getWritePath(String fileName, StorageType fileType) {
        return pathForName(fileName, fileType, false, false);
    }

    private String pathForName(String fileName, StorageType type, boolean dir,
                               boolean check) {
        String directory = getDirectoryByDirType(type);
        StringBuilder path = new StringBuilder(directory);

        if (!dir) {
            path.append(fileName);
        }

        String pathString = path.toString();
        File file = new File(pathString);

        if (check) {
            if (file.exists()) {
                if ((dir && file.isDirectory())
                        || (!dir && !file.isDirectory())) {
                    return pathString;
                }
            }

            return "";
        } else {
            return pathString;
        }
    }

    /**
     * 返回指定类型的文件夹路径
     *
     * @param fileType
     * @return
     */
    public String getDirectoryByDirType(StorageType fileType) {
        return sdkStorageRoot + fileType.getPath();
    }

    public static String getSystemImagePath() {
        if (Build.VERSION.SDK_INT > 7) {
            String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            return picturePath + "/nim/";
        } else {
            String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            return picturePath + "/nim/";
        }
    }

    public static boolean isInvalidVideoFile(String filePath) {
        return filePath.toLowerCase().endsWith(".3gp")
                || filePath.toLowerCase().endsWith(".mp4");
    }




    enum StorageType {

        AUDIO("audio/"),
        DATA ("data/"),
        FILE ("file/"),
        LOG  ("log/"),
        TEMP ("temp/"),
        IMAGE("image/"),
        THUMB("thumb/"),
        VIDEO("video/");

        private String path;

        public String getPath() {
            return path;
        }

        private StorageType(String path) {
            this.path = path;
        }
    }
}
