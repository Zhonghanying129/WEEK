package com.example.administrator.week.bean;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class SystemInfoModel {
    private int type;

    private String title;

    private String key;

    private String value;

    private String buttonkey;

    private String buttonvalue;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getButtonkey() {
        return buttonkey;
    }

    public void setButtonkey(String buttonkey) {
        this.buttonkey = buttonkey;
    }

    public String getButtonvalue() {
        return buttonvalue;
    }

    public void setButtonvalue(String buttonvalue) {
        this.buttonvalue = buttonvalue;
    }

    @Override
    public String toString() {
        return "SystemInfoModel{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", buttonkey='" + buttonkey + '\'' +
                ", buttonvalue='" + buttonvalue + '\'' +
                '}';
    }
}
