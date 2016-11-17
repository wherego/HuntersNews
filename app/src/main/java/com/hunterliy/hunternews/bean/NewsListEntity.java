package com.hunterliy.hunternews.bean;

public class NewsListEntity {

    private String picUrl;
    private String ctime;
    private String description;
    private String title;
    private String url;

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getCtime() {
        return ctime;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

}
