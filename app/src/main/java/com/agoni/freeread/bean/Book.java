package com.agoni.freeread.bean;

/**
 * Created by Agoni on 2017/2/18.
 */

public class Book {
    public String _id;
    public String author;
    public String cover;
    public String shortIntro;
    public String title;
    public String site;
    public String cat;
    public String banned;
    public String latelyFollower;
    public String latelyFollowerBase;
    public String minRetentionRatio;
    public String retentionRatio;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }

    public String getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(String latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public String getLatelyFollowerBase() {
        return latelyFollowerBase;
    }

    public void setLatelyFollowerBase(String latelyFollowerBase) {
        this.latelyFollowerBase = latelyFollowerBase;
    }

    public String getMinRetentionRatio() {
        return minRetentionRatio;
    }

    public void setMinRetentionRatio(String minRetentionRatio) {
        this.minRetentionRatio = minRetentionRatio;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }
}
