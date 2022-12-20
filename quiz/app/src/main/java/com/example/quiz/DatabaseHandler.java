package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizManager.sqlite";
    private static final String TABLE_QUIZES = "quizes";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "quiz_name";
    private static final String KEY_A_C = "ans_count";
    private static final String KEY_Q_T = "quiz_type";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QUIZES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_A_C + " INTEGER,"
                + KEY_Q_T + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new quiz
    void addQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, quiz.getQuizName()); // Quiz Name
        values.put(KEY_A_C, quiz.getAnsCount()); // Answers Count
        values.put(KEY_Q_T, quiz.getQuizType()); // Quiz Type

        // Inserting Row
        db.insert(TABLE_QUIZES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single quiz
    Quiz getQuiz(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUIZES, new String[] { KEY_ID,
                        KEY_NAME, KEY_A_C, KEY_Q_T }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Quiz quiz = new Quiz(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getInt(2), cursor.getString(3));
        // return quiz
        return quiz;
    }

    // code to get all quizes in a list view
    public List<Quiz> getAllQuizes() {
        List<Quiz> quizList = new ArrayList<Quiz>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUIZES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Quiz quiz = new Quiz();
                quiz.setID(Integer.parseInt(cursor.getString(0)));
                quiz.setQuizName(cursor.getString(1));
                quiz.setAnsCount(cursor.getInt(2));
                quiz.setQuizType(cursor.getString(3));
                // Adding contact to list
                quizList.add(quiz);
            } while (cursor.moveToNext());
        }

        // return quiz list
        return quizList;
    }

    // code to update the single quiz
    public int updateQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, quiz.getQuizName());
        values.put(KEY_A_C, quiz.getAnsCount());
        values.put(KEY_Q_T, quiz.getQuizType());

        // updating row
        return db.update(TABLE_QUIZES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(quiz.getID()) });
    }

    // Deleting single quiz
    public void deleteQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUIZES, KEY_ID + " = ?",
                new String[] { String.valueOf(quiz.getID()) });
        db.close();
    }

    // Getting quiz Count
    public long getQuizCount() {

        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_QUIZES);
        db.close();
        return count;
    }

}