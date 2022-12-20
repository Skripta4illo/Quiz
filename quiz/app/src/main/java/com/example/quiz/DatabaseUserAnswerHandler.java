package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUserAnswerHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userAnswerManager.sqlite";
    private static final String TABLE_USERS_ANSWER = "userAnswers";
    private static final String KEY_ID = "id";
    private static final String KEY_U_ID = "user_id";
    private static final String KEY_QUIZ_ID = "quiz_id";
    private static final String KEY_QUES_ID = "question_id";
    private static final String KEY_U_A = "user_answer";

    public DatabaseUserAnswerHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS_ANSWER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_U_ID + " INTEGER,"
                + KEY_QUIZ_ID + " INTEGER," + KEY_QUES_ID + " INTEGER," + KEY_U_A + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS_ANSWER);

        // Create tables again
        onCreate(db);
    }

    // code to add the new user answer
    void addUserAnswer(UserAnswer userAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_U_ID, userAnswer.getUserId()); // User Id
        values.put(KEY_QUIZ_ID, userAnswer.getQuizId()); //Quiz Id
        values.put(KEY_QUES_ID, userAnswer.getQuestionId()); // Question ID
        values.put(KEY_U_A,userAnswer.getUserAnswer()); //Answer

        // Inserting Row
        db.insert(TABLE_USERS_ANSWER, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single user answer
    UserAnswer getUserAnswer(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS_ANSWER, new String[] { KEY_ID, KEY_U_ID,
                        KEY_QUIZ_ID, KEY_QUES_ID, KEY_U_A }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserAnswer userAnswer = new UserAnswer(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
        // return user Answer
        return userAnswer;
    }

    // code to get all user answers in a list view
    public List<UserAnswer> getAllUserAnswer() {
        List<UserAnswer> userAnswerList = new ArrayList<UserAnswer>();
        // Select All User Answers
        String selectQuery = "SELECT  * FROM " + TABLE_USERS_ANSWER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setIDua(Integer.parseInt(cursor.getString(0)));
                userAnswer.setUserId(Integer.parseInt(cursor.getString(1)));
                userAnswer.setQuizId(Integer.parseInt(cursor.getString(2)));
                userAnswer.setQuestionId(Integer.parseInt(cursor.getString(3)));
                userAnswer.setUserAnswer(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                userAnswerList.add(userAnswer);
            } while (cursor.moveToNext());
        }

        // return user answers list
        return userAnswerList;
    }

    // code to update the single user answer
    public int updateUserAnswer(UserAnswer userAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_U_ID, userAnswer.getUserId());
        values.put(KEY_QUIZ_ID, userAnswer.getQuizId());
        values.put(KEY_QUES_ID, userAnswer.getQuestionId());
        values.put(KEY_U_A, userAnswer.getUserAnswer());

        // updating row
        return db.update(TABLE_USERS_ANSWER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(userAnswer.getIDua()) });
    }

    // Deleting single user answer
    public void deleteUserAnswer(UserAnswer userAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS_ANSWER, KEY_ID + " = ?",
                new String[] { String.valueOf(userAnswer.getIDua()) });
        db.close();
    }

    // Getting user Count
    public long getUserAnswerCount() {

        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_USERS_ANSWER);
        db.close();
        return count;
    }

}