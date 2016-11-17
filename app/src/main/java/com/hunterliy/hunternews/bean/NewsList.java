package com.hunterliy.hunternews.bean;

import java.util.List;

public class NewsList {

    private String msg;
    private int code;
    private List<NewsListBean> newslist;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNewslist(List<NewsListBean> newslist) {
        this.newslist = newslist;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public List<NewsListBean> getNewslist() {
        return newslist;
    }

    public static NewsList objectFromData(String s){
        return new com.google.gson.Gson().fromJson(s,NewsList.class);
    }


    public static class NewsListBean {

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

        public static NewsListBean objectFromData(String s){
            return new com.google.gson.Gson().fromJson(s,NewsListBean.class);
        }
    }
}
