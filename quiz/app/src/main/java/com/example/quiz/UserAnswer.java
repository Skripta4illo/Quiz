package com.example.quiz;

public class UserAnswer {
    long _id;
    long _user_id;
    long _quiz_id; //maybe it will overload
    long _question_id;
    int _user_answer;

    public UserAnswer() {
    }

    public UserAnswer(long id, long _user_id, long _quiz_id, long _question_id, int _user_answer) {
        this._id = id;
        this._user_id = _user_id;
        this._quiz_id = _quiz_id;
        this._question_id = _question_id;
        this._user_answer = _user_answer;
    }

    public UserAnswer(long _user_id, long _quiz_id, long _question_id, int _user_answer) {
        this._user_id = _user_id;
        this._quiz_id = _quiz_id;
        this._question_id = _question_id;
        this._user_answer = _user_answer;
    }


    public long getIDua(){
        return this._id;
    }

    public void setIDua(long id){
        this._id = id;
    }

    public long getUserId(){
        return this._user_id;
    }

    public void setUserId(long _user_id){
        this._user_id = _user_id;
    }

    public long getQuizId(){
        return this._quiz_id;
    }

    public void setQuizId(long _quiz_id){
        this._quiz_id = _quiz_id;
    }

    public long getQuestionId(){
        return this._question_id;
    }

    public void setQuestionId(long _question_id){
        this._question_id = _question_id;
    }

    public int getUserAnswer(){
        return this._user_answer;
    }

    public void setUserAnswer(int _user_answer){
        this._user_answer = _user_answer;
    }
}
