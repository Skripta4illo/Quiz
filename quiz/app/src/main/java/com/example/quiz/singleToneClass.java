package com.example.quiz;

public class singleToneClass {
    //String s;
    int cou;
    private static final singleToneClass ourInstance = new singleToneClass();
    public static singleToneClass getInstance() {
        return ourInstance;
    }
    private singleToneClass() {
    }
    public void setData(int cou) {
        this.cou = cou;
    }
    public int getData() {
        return cou;
    }
}
