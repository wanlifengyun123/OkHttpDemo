package com.yajun.yunxin.model;

import java.io.Serializable;

/**
 * Created by yajun on 2016/9/7.
 *
 */
public class UserModel extends BaseModel implements Serializable {


    /**
     * uid : 3911288
     * uname : wyjcool123@163.com
     * phone :
     * sid : fee8089c325077a98a2cd3b121415311
     * token : 5ec225db022608c55408ffa3538c1aa7
     * nickname : 万里风云3911288
     * email : wyjcool123@163.com
     * sex : 1
     * aboutme : null
     * blog : null
     * country_id : 0
     * prov_id : 0
     * city_id : 0
     * dist_id : 0
     * apsid : VlODA4OWMzMjUwNzdhOThhMmNkM2IxMjE0MTUzMTEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMzkxMTI4OAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB3eWpjb29sMTIzQDE2My5jb20AAAAAAAAAAAAAAAAAADVlYzIyNWRiMDIyNjA4YzU1NDA4ZmZhMzUzOGMxYWE3MmXRVzJl0Vc=Zm
     * companyid : 0
     * city :
     * pic : http://img.mukewang.com/57c690540001181301000100-100-100.jpg
     * messnum : 55
     */

    private int uid;
    private String uname;
    private String phone;
    private String sid;
    private String token;
    private String nickname;
    private String email;
    private String sex;
    private String aboutme;
    private String blog;
    private String country_id;
    private String prov_id;
    private String city_id;
    private String dist_id;
    private String apsid;
    private String companyid;
    private String city;
    private String pic;
    private int messnum;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getProv_id() {
        return prov_id;
    }

    public void setProv_id(String prov_id) {
        this.prov_id = prov_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDist_id() {
        return dist_id;
    }

    public void setDist_id(String dist_id) {
        this.dist_id = dist_id;
    }

    public String getApsid() {
        return apsid;
    }

    public void setApsid(String apsid) {
        this.apsid = apsid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getMessnum() {
        return messnum;
    }

    public void setMessnum(int messnum) {
        this.messnum = messnum;
    }
}
