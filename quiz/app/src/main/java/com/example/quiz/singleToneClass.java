package com.example.quiz;

public class singleToneClass {
    //String s;
    long cou;
    long uid;
    private static final singleToneClass ourInstance = new singleToneClass();
    public static singleToneClass getInstance() {
        return ourInstance;
    }
    private singleToneClass() {
    }
    public void setData(long cou) {
        this.cou = cou;
    }
    public long getData() {
        return cou;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public long getUid() {
        return uid;
    }
}
