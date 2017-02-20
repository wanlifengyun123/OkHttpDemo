package com.yajun.yunxin.helper;

import android.content.Context;

import com.yajun.yunxin.global.Constant;
import com.yajun.yunxin.model.UserModel;
import com.yajun.yunxin.util.PreferenceUtil;

import com.yajun.yunxin.App;

/**
 * Created by yajun on 2016/9/21.
 *
 */
public class UserHelper {

    static Context mContext;

    static {
        mContext = App.getInstance();
    }

    public static String getUserAccount() {
        return PreferenceUtil.getString(mContext,Constant.UserExtra.ACCOUNT);
    }

    public static String getUserToken() {
        return PreferenceUtil.getString(mContext,Constant.UserExtra.TOKEN);
    }

    public static String getUserAppKey() {
        return PreferenceUtil.getString(mContext, Constant.UserExtra.APP_KEY);
    }

    public static String getUserNick() {
        return PreferenceUtil.getString(mContext, Constant.UserExtra.NICK_NAME);
    }

    public static String getUserPic() {
        return PreferenceUtil.getString(mContext, Constant.UserExtra.USER_PIC);
    }



    public static void setAccount(String account) {
        PreferenceUtil.putString(mContext, Constant.UserExtra.ACCOUNT,account);
    }

    public static void setUserToken(String token) {
        PreferenceUtil.putString(mContext, Constant.UserExtra.TOKEN,token);
    }

    public static void setUserAppKey(String appKey) {
        PreferenceUtil.putString(mContext, Constant.UserExtra.APP_KEY,appKey);
    }

    public static void setUserNick(String nickName){
        PreferenceUtil.putString(mContext,Constant.UserExtra.NICK_NAME,nickName);
    }

    public static void setUserPic(String userPic){
        PreferenceUtil.putString(mContext,Constant.UserExtra.USER_PIC,userPic);
    }

    public static void saveUserModel(UserModel userModel){
        setAccount(userModel.getEmail());
        setUserToken(userModel.getToken());
        setUserAppKey(userModel.getDist_id());
        setUserNick(userModel.getNickname());
        setUserPic(userModel.getPic());
    }


}
