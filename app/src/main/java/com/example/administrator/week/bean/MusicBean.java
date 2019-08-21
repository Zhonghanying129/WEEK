package com.example.administrator.week.bean;

/**
 * Created by Administrator on 2019/4/13 0013.
 */

public class MusicBean {
    String songname;
    String singgerName;
    String songPicUrl;
    String songUrl;

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSongPicUrl() {
        return songPicUrl;
    }

    public void setSongPicUrl(String songPicUrl) {
        this.songPicUrl = songPicUrl;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSinggerName() {
        return singgerName;
    }

    public void setSinggerName(String singgerName) {
        this.singgerName = singgerName;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "songname='" + songname + '\'' +
                ", singgerName='" + singgerName + '\'' +
                ", songPicUrl='" + songPicUrl + '\'' +
                ", songUrl='" + songUrl + '\'' +
                '}';
    }
}
