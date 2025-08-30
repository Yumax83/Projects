package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, Contacts.DATABASE_NAME, null, Contacts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contacts.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contacts.TABLE_NAME);
        onCreate(db);
    }

    public long insertContact(String image, String name, String phone, String email, String note, String addedTime, String updatedTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Contacts.C_IMAGE, image);
        contentValues.put(Contacts.C_NAME, name);
        contentValues.put(Contacts.C_PHONE, phone);
        contentValues.put(Contacts.C_EMAIL, email);
        contentValues.put(Contacts.C_NOTE, note);
        contentValues.put(Contacts.C_ADDED_TIME, addedTime);
        contentValues.put(Contacts.C_UPDATED_TIME, updatedTime);

        long id = db.insert(Contacts.TABLE_NAME, null, contentValues);

        db.close();
        return id;
    }

    public ArrayList<ModelContact> getAllData(String orderBy) {
        ArrayList<ModelContact> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Contacts.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ModelContact modelContact = new ModelContact(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Contacts.C_ID)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_NAME)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_IMAGE)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_PHONE)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_EMAIL)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_NOTE)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_ADDED_TIME)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_UPDATED_TIME))
                );
                arrayList.add(modelContact);
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public void updateContact(String id, String image, String name, String phone, String email, String note, String addedTime, String updatedTime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Contacts.C_IMAGE, image);
        contentValues.put(Contacts.C_NAME, name);
        contentValues.put(Contacts.C_PHONE, phone);
        contentValues.put(Contacts.C_EMAIL, email);
        contentValues.put(Contacts.C_NOTE, note);
        contentValues.put(Contacts.C_ADDED_TIME, addedTime);
        contentValues.put(Contacts.C_UPDATED_TIME, updatedTime);

        db.update(Contacts.TABLE_NAME, contentValues, Contacts.C_ID + " = ?", new String[]{id});

        db.close();
    }

    public void deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase(); // Открываем базу
        db.delete(Contacts.TABLE_NAME, Contacts.C_ID + " = ?", new String[]{id});
        db.close();

    }

    public void deleteAllContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Contacts.TABLE_NAME);
        db.close();


    }

    public ArrayList<ModelContact> getSearchContact(String query) {
        ArrayList<ModelContact> contactList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String queryToSearch = "SELECT * FROM " + Contacts.TABLE_NAME + " WHERE " + Contacts.C_NAME + " LIKE '%" + query + "%'";

        Cursor cursor = db.rawQuery(queryToSearch, null);

        if (cursor.moveToFirst()) {
            do {
                ModelContact modelContact = new ModelContact(
                        "" + cursor.getInt(cursor.getColumnIndexOrThrow(Contacts.C_ID)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_NAME)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_IMAGE)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_PHONE)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_EMAIL)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_NOTE)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_ADDED_TIME)),
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(Contacts.C_UPDATED_TIME))
                );
                contactList.add(modelContact);
            } while (cursor.moveToNext());
        }
        db.close();
        return contactList;
    }

}

//phoneText.setOnClickListener(new View.OnClickListener() {
//
//    @Override
//
//    public void onClick(View v) {
//
//        String phoneNumber = phoneText.getText().toString();
//
//        Intent intent = new Intent(Intent.ACTION_DIAL); //Открывает приложение звонков с уже подставленным номером.
//
//        intent.setData(Uri.parse("tel:" + phoneNumber));
//
//        startActivity(intent);
