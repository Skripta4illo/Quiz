package com.example.quiz;

public class Quiz {
    long _id;
    String _quiz_name;
    int _ans_count;
    String _quiz_type;

    public Quiz() {
    }

    public Quiz(long id, String quiz_name, int _ans_count, String _quiz_type) {
        this._id = id;
        this._quiz_name = quiz_name;
        this._ans_count = _ans_count;
        this._quiz_type = _quiz_type;
    }

    public Quiz(String quiz_name, int _ans_count, String _quiz_type){
        this._quiz_name = quiz_name;
        this._ans_count = _ans_count;
        this._quiz_type = _quiz_type;
    }


    public long getID(){
        return this._id;
    }

    public void setID(long id){
        this._id = id;
    }

    public String getQuizName(){
        return this._quiz_name;
    }

    public void setQuizName(String quiz_name){
        this._quiz_name = quiz_name;
    }

    public int getAnsCount(){
        return this._ans_count;
    }

    public void setAnsCount(int ans_count){
        this._ans_count = ans_count;
    }

    public String getQuizType(){
        return this._quiz_type;
    }

    public void setQuizType(String quiz_type){
        this._quiz_type = quiz_type;
    }

}
