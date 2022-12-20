package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQuestionHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "questionManager.sqlite";
    private static final String TABLE_QUESTIONS = "questions";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "question_name";
    private static final String KEY_Q_ID = "quiz_id";
    private static final String KEY_Q_R = "question_right";

    public DatabaseQuestionHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Q_ID + " INTEGER," + KEY_NAME + " TEXT,"
                + KEY_Q_R + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new question
    void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Q_ID, question.getQuizID()); // Quiz ID
        values.put(KEY_NAME, question.getQuestionName()); // Question Name
        values.put(KEY_Q_R, question.getQuestionRight()); // Is answer is right

        // Inserting Row
        db.insert(TABLE_QUESTIONS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single question
    Question getQuestion(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { KEY_ID, KEY_Q_ID,
                        KEY_NAME, KEY_Q_R }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return question
        return question;
    }

    // code to get all question in a list view
    public List<Question> getAllQuestion() {
        List<Question> questionList = new ArrayList<Question>();
        // Select All Question
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setIDa(Integer.parseInt(cursor.getString(0)));
                question.setQuizID(Integer.parseInt(cursor.getString(1)));
                question.setQuestionName(cursor.getString(2));
                question.setQuestionRight(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionList;
    }


    // all questions from quiz
    public List<Question> getAllQuestionInQuiz(long quizId) {
        List<Question> questionList = new ArrayList<Question>();
        // Select All Question
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_Q_ID +
                " = " + quizId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setIDa(Integer.parseInt(cursor.getString(0)));
                question.setQuizID(Integer.parseInt(cursor.getString(1)));
                question.setQuestionName(cursor.getString(2));
                question.setQuestionRight(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        // return question list
        return questionList;
    }

    // code to update the single question
    public int updateQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Q_ID, question.getQuizID());
        values.put(KEY_NAME, question.getQuestionName());
        values.put(KEY_Q_R, question.getQuestionRight());

        // updating row
        return db.update(TABLE_QUESTIONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(question.getIDa()) });
    }

    // Deleting single question
    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(question.getIDa()) });
        db.close();
    }

    // Getting question Count
    public long getQuestionCount() {

        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_QUESTIONS);
        db.close();
        return count;
    }

}