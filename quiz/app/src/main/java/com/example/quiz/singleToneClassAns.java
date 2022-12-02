package com.example.quiz;

public class singleToneClassAns {
    String ans;
    //int cou;
    private static final singleToneClassAns ourInstance = new singleToneClassAns();
    public static singleToneClassAns getInstance() {
        return ourInstance;
    }
    private singleToneClassAns() {
    }
    public void setAns(String ans) {
        this.ans = ans;
    }
    public String getAns() {
        return ans;
    }
}
