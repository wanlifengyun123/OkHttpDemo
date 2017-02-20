package com.yajun.yunxin.model;

import java.io.Serializable;

/**
 * Created by yajun on 2016/9/7.
 *
 */
public class CourseModel extends BaseModel implements Serializable {

    /**
     * id : 715
     * name : iOS基础之UIImageView知识拓展(上)
     * pic : http://img.mukewang.com/57cd13f40001e1a406000338.jpg
     * purpose : 掌握iOS图像处理之UIImage展示
     * short_description : 掌握iOS图像处理之UIImage展示
     * duration : 5520
     * numbers : 818
     * islearned : 0
     * companyid : 0
     * lasttime : 0
     * chapter : 1
     * media : 1
     */

    private String id;
    private String name;
    private String pic;
    private String purpose;
    private String short_description;
    private String duration;
    private String numbers;
    private int islearned;
    private String companyid;
    private int lasttime;
    private int chapter;
    private int media;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public int getIslearned() {
        return islearned;
    }

    public void setIslearned(int islearned) {
        this.islearned = islearned;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public int getLasttime() {
        return lasttime;
    }

    public void setLasttime(int lasttime) {
        this.lasttime = lasttime;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }
}
