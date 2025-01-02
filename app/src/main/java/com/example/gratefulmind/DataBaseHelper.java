package com.example.gratefulmind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gratefulMind.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla thanks
        db.execSQL("CREATE TABLE thanks (id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, gratitude1 TEXT, gratitude2 TEXT, gratitude3 TEXT, lesson TEXT, feeling TEXT, reason TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS thanks");
        onCreate(db);
    }

    public Cursor getGratitudeByDate(String date) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT date, gratitude1, gratitude2, gratitude3, lesson, feeling, reason FROM thanks WHERE date = ?", new String[]{date});
    }

    public Cursor getGratitudesByDate() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT date FROM thanks ORDER BY date ASC", null);
    }

    // Método para agregar un agradecimiento a la bbdd
    public void addGratitude(String date, String gratitude1, String gratitude2, String gratitude3, String lesson, String feeling, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creaamos un objeto ContentValue
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("gratitude1", gratitude1);
        values.put("gratitude2", gratitude2);
        values.put("gratitude3", gratitude3);
        values.put("lesson", lesson);
        values.put("feeling", feeling);
        values.put("reason", reason);

        // Insertar los valores en la bbdd
        db.insert("thanks", null, values);
        db.close();
    }
    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
