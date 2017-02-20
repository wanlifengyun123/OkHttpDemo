package com.yajun.yunxin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yajun on 2016/9/7.
 *
 */
public class ChapterModel implements Serializable {

    public int getType() {
        if (this.media != null)
            return 0;
        return 1;
    }

    /**
     *  获取Item内容
     *
     * @param pPosition
     * @return
     */
    public Object getItem(int pPosition) {
        // Category排在第一位
        if (pPosition == 0) {
            return chapter;
        } else {
            return media.get(pPosition - 1);
        }
    }

    /**
     * 当前类别Item总数。Category也需要占用一个Item
     * @return
     */
    public int getItemCount() {
        return media.size() + 1;
    }


    /**
     * id : 3438
     * name : 课程准备
     * cid : 709
     * seqid : 1
     */

    private ChapterBean chapter;
    /**
     * mid : 12587
     * pic : http://img.mukewang.com/57bae08b000148e207200480-228-128.jpg
     * chapter_id : 3438
     * seqid : 1
     * url : http://v1.mukewang.com/26786f3f-35c1-4dc7-b54f-e54168d697b3/L.mp4
     * name : 案例演示
     * type : 1
     * status : 0
     */

    private List<MediaBean> media;

    public ChapterBean getChapter() {
        return chapter;
    }

    public void setChapter(ChapterBean chapter) {
        this.chapter = chapter;
    }

    public List<MediaBean> getMedia() {
        return media;
    }

    public void setMedia(List<MediaBean> media) {
        this.media = media;
    }

    public static class ChapterBean implements Serializable{
        private String id;
        private String name;
        private String cid;
        private String seqid;

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

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getSeqid() {
            return seqid;
        }

        public void setSeqid(String seqid) {
            this.seqid = seqid;
        }
    }

    public static class MediaBean implements Serializable{
        private String mid;
        private String pic;
        private String chapter_id;
        private String seqid;
        private String url;
        private String name;
        private String type;
        private int status;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getChapter_id() {
            return chapter_id;
        }

        public void setChapter_id(String chapter_id) {
            this.chapter_id = chapter_id;
        }

        public String getSeqid() {
            return seqid;
        }

        public void setSeqid(String seqid) {
            this.seqid = seqid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
