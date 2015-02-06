package com.example.feedback;

/**
 * Created by trmehta on 1/23/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "feedbackManager1";

    // Contacts table name
    private static final String TABLE_FEEDBACK = "feedback1";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIl = "email";
    private static final String KEY_DES = "description";
    private static final String KEY_CAT = "category";
    private static final String KEY_IMG = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIl + " TEXT," + KEY_DES + " TEXT," + KEY_IMG + " BLOB,"
                + KEY_CAT + " TEXT" + ")";
        db.execSQL(CREATE_FEEDBACK_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new feedback
    void addFeedbackObject(FeedbackObject contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIl, contact.getUsername());
        values.put(KEY_DES, contact.getDescription());
        values.put(KEY_CAT, contact.getCategory());
        values.put(KEY_IMG, contact.getImage());

        // Inserting Row
        db.insert(TABLE_FEEDBACK, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    FeedbackObject getFeedback(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FEEDBACK, new String[] { KEY_ID,
                        KEY_EMAIl, KEY_DES, KEY_CAT, KEY_IMG }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        FeedbackObject feedback = new FeedbackObject(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        // return contact
        return feedback;
    }

    // Getting All Contacts
    public List<FeedbackObject> getAllFeedbacks() {
        List<FeedbackObject> contactList = new ArrayList<FeedbackObject>();
        // Select All Query
        String selectQuery = "SELECT  id, email, description, category FROM " + TABLE_FEEDBACK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //byte[] b = cursor.getBlob(4);
                FeedbackObject contact = new FeedbackObject();
                //contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setUsername(cursor.getString(1));
                contact.setDescription(cursor.getString(2));
                contact.setCategory(cursor.getString(3));
                //contact.setImage(b);
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
   /* public int updateContact(FeedbackObject contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }*/

}