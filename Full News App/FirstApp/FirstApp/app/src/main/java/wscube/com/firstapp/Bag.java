package wscube.com.firstapp;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by wscube on 30/12/16.
 */
public class Bag {
    String title;
    String date;
    String des;
    Bitmap bmpImgNews;
    public Bitmap getBmpImgNews() {
        return bmpImgNews;
    }

    public void setBmpImgNews(Bitmap bmpImgNews) {
        this.bmpImgNews = bmpImgNews;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


}
