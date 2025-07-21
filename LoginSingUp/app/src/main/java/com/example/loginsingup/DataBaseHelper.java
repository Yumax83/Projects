package com.example.loginsingup;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;
import static android.provider.Telephony.Carriers.PASSWORD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_record.db";
    private static final String TABLE_NAME = "user_data";
    private static final String ID = "id";
    private static final String MAIL_TEXT = "mail TEXT";
    private static final String PASSWORD_TEXT = "password TEXT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, e" + MAIL_TEXT + ", " + PASSWORD_TEXT + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email=? ", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ? AND " + PASSWORD + " =? ",
        new String[]{email});
        return cursor.getCount() > 0;
    }
}
