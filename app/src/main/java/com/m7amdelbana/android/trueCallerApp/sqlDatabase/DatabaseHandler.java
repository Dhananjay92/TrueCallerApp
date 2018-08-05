package com.m7amdelbana.android.trueCallerApp.sqlDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.m7amdelbana.android.trueCallerApp.models.Contact;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactsDB";
    private static final String TABLE_NAME = "contacts";

    private static final String COLUMN_ID = "contactID";
    private static final String COLUMN_NAME = "contactName";
    private static final String COLUMN_COMPANY = "contactCompany";
    private static final String COLUMN_PHONE = "contactPhoneNumber";
    private static final String COLUMN_NOTES = "contactNotes";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_COMPANY + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_NOTES + " TEXT" + " )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase contactsDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_COMPANY, contact.getCompany());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_NOTES, contact.getNotes());

        contactsDB.insert(TABLE_NAME, null, values);
        contactsDB.close();
    }

    public Contact getContactByID(int contactID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NAME, COLUMN_COMPANY, COLUMN_PHONE, COLUMN_NOTES},
                COLUMN_ID + "=?", new String[]{String.valueOf(contactID)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(contactID, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        db.close();
        return contact;
    }

    public String getNameByPhone(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();

        String contactName = "0";

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NAME},
                COLUMN_PHONE + "=?", new String[]{String.valueOf(phone)},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst() ) {
            contactName = cursor.getString(0);
            db.close();
        }

        return contactName;
    }

    public Contact getContactByPhone(String contactPhone) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_COMPANY, COLUMN_NOTES},
                COLUMN_PHONE + "=?", new String[]{String.valueOf(contactPhone)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2), contactPhone, cursor.getString(3));
        db.close();
        return contact;
    }

    public boolean updateItem(Contact contact) {

        SQLiteDatabase contactsDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_COMPANY, contact.getCompany());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_NOTES, contact.getNotes());

        String whereClause = COLUMN_ID + " == ?";

        String[] whereArgs = new String[]{contact.getID() + ""};

        try {
            contactsDB.update(TABLE_NAME, values, whereClause, whereArgs);
            contactsDB.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> itemsList = new ArrayList<Contact>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact oneItem = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                itemsList.add(oneItem);
            }
            while (cursor.moveToNext());
        }

        db.close();
        return itemsList;
    }

    public int getContactsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        db.close();
        return cursor.getCount();
    }

    public void deleteContactByID(int noticeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(noticeID)});
        db.close();
    }
}
