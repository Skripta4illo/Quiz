package com.example.quiz;

public class User {
    long _id;
    String _user_name;
    String _user_email;
    String _user_password;
    boolean _user_verification;

    public User() {
    }

    public User(int id, String _user_name, String _user_email, String _user_password, boolean _user_verification) {
        this._id = id;
        this._user_name = _user_name;
        this._user_email = _user_email;
        this._user_password = _user_password;
        this._user_verification = _user_verification;
    }

    public User(String _user_name, String _user_email, String _user_password, boolean _user_verification){
        this._user_name = _user_name;
        this._user_email = _user_email;
        this._user_verification = _user_verification;
        this._user_password = _user_password;
    }


    public long getIDu(){
        return this._id;
    }

    public void setIDu(long id){
        this._id = id;
    }

    public String getUserName(){
        return this._user_name;
    }

    public void setUserName(String _user_name){
        this._user_name = _user_name;
    }

    public String getUserEmail(){
        return this._user_email;
    }

    public void setUserEmail(String _user_email){
        this._user_email = _user_email;
    }

    public boolean getUserVerification(){
        return this._user_verification;
    }

    public void setUserVerification(boolean _user_verification){
        this._user_verification = _user_verification;
    }

    public String getUserPassword(){
        return this._user_password;
    }

    public void setUserPassword(String _user_password){
        this._user_password = _user_password;
    }
}
