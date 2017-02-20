package com.yajun.yunxin.net.iml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yajun.yunxin.model.ChapterModel;
import com.yajun.yunxin.model.CourseModel;
import com.yajun.yunxin.model.UserModel;
import com.yajun.yunxin.net.OkHttpUtil;
import com.yajun.yunxin.net.RequestParams;
import com.yajun.yunxin.net.RequestResult;
import com.yajun.yunxin.net.event.BaseBusEvent;
import com.yajun.yunxin.net.event.ChapterEvent;
import com.yajun.yunxin.net.event.CourseEvent;
import com.yajun.yunxin.net.event.LoginEvent;
import com.yajun.yunxin.net.event.RegisterEvent;

import java.util.List;

/**
 * Created by yajun on 2016/9/22.
 *
 */
public class UserService {

    public static final String BASE_API = "http://www.imooc.com/api";

    public static final String LOGIN = BASE_API + "/userloginbyemail";
    public static final String REGISTER = BASE_API + "/";
    public static final String COURSE = BASE_API + "/courselist";
    public static final String CHAPTER_LIST = BASE_API + "/getcpinfo";

    public static void login(String email,String password){
        RequestParams params = new RequestParams();
        params.put("email","wyjcool123@163.com");
        params.put("password","wyj123456");
        OkHttpUtil.getInstance().doGet(LOGIN,params,
                new OkHttpUtil.RCallBack<UserModel>(new LoginEvent()){
                    @Override
                    public RequestResult<UserModel> pareResponse(String jsonData) {
                        return new Gson().fromJson(jsonData,new TypeToken<RequestResult<UserModel>>(){}.getType());
                    }
                });

    }

    public static void register(String account,String nickName,String password){
        RequestParams params = new RequestParams();
        params.put("account",account);
        params.put("nickName",nickName);
        params.put("password",password);
        OkHttpUtil.getInstance().doGet(REGISTER,params,
                new OkHttpUtil.RCallBack<UserModel>(new RegisterEvent()){
                    @Override
                    public RequestResult<UserModel> pareResponse(String jsonData) {
                        return new Gson().fromJson(jsonData,new TypeToken<RequestResult<UserModel>>(){}.getType());
                    }
                });
    }

    public static void getAllCourse(int page){
        RequestParams params = new RequestParams();
        params.put("uid","3911288");
        params.put("page",page);
        OkHttpUtil.getInstance().doGet(COURSE,params,
                new OkHttpUtil.RCallBack<List<CourseModel>>(new CourseEvent()){
                    @Override
                    public RequestResult<List<CourseModel>> pareResponse(String jsonData) {
                        return new Gson().fromJson(jsonData,new TypeToken<RequestResult<List<CourseModel>>>(){}.getType());
                    }
                });
    }

    public static void getChapterByCourseId(String courseId){
        RequestParams params = new RequestParams();
        params.put("uid","3911288");
        params.put("cid",courseId);
        OkHttpUtil.getInstance().doGet(CHAPTER_LIST,params,
                new OkHttpUtil.RCallBack<List<ChapterModel>>(new ChapterEvent()){
                    @Override
                    public RequestResult<List<ChapterModel>> pareResponse(String jsonData) {
                        return new Gson().fromJson(jsonData,new TypeToken<RequestResult<List<ChapterModel>>>(){}.getType());
                    }
                });
    }


}
