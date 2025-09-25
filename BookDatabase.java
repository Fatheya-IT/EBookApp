package com.example.ebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LibraryDB.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "Books";
    private static final String COL_ID = "id";
    private static final String COL_WRITER = "writer";
    private static final String COL_TITLE = "title";
    private static final String COL_GENRE = "genre";

    public BookDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_WRITER + " TEXT, " +
                COL_TITLE + " TEXT, " +
                COL_GENRE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertBook(String writer, String title, String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_WRITER, writer);
        values.put(COL_TITLE, title);
        values.put(COL_GENRE, genre);

        long result = db.insert(TABLE_NAME, null, values);
        db.close(); // Close database connection
        return result != -1;
    }

    // In BookDatabase.java
    public Cursor getAllBooks() {  // Renamed from getBookTitles to getAllBooks
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}







