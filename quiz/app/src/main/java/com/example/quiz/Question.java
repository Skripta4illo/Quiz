package com.example.quiz;

public class Question {
    int _id;
    int _quiz_id;
    String _question_name;
    int _question_right;

    public Question() {
    }

    public Question(int id, int quiz_id, String question_name, int _question_right) {
        this._id = id;
        this._question_name = question_name;
        this._quiz_id = quiz_id;
        this._question_right = _question_right;
    }

    public Question(int quiz_id, String question_name, int _question_right){
        this._question_name = question_name;
        this._quiz_id = quiz_id;
        this._question_right = _question_right;
    }


    public int getIDa(){
        return this._id;
    }

    public void setIDa(int id){
        this._id = id;
    }

    public String getQuestionName(){
        return this._question_name;
    }

    public void setQuestionName(String question_name){
        this._question_name = question_name;
    }

    public int getQuizID(){
        return this._quiz_id;
    }

    public void setQuizID(int quiz_id){
        this._quiz_id = quiz_id;
    }

    public int getQuestionRight(){
        return this._question_right;
    }

    public void setQuestionRight(int question_right){
        this._question_right = question_right;
    }

}