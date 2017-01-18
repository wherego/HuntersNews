package com.hunterliy.wangyi;

import java.util.List;

public class NewsResponse {
    private String msg;
    private int code;
    private List<News> newslist;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNewslist(List<News> newslist) {
        this.newslist = newslist;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public List<News> getNewslist() {
        return newslist;
    }
}
