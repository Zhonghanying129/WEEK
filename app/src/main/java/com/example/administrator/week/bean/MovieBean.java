package com.example.administrator.week.bean;

/**
 * Created by Administrator on 2019/4/20 0020.
 */

public class MovieBean {
    String title;
    String averager;
    String actor;
    String type;
    String moviePicUrl;
    String Id;
    String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoviePicUrl() {
        return moviePicUrl;
    }

    public void setMoviePicUrl(String moviePicUrl) {
        this.moviePicUrl = moviePicUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAverager() {
        return averager;
    }

    public void setAverager(String averager) {
        this.averager = averager;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "title='" + title + '\'' +
                ", averager='" + averager + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}
