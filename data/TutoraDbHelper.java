package com.example.tutora.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;

public class TutoraDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tutora.db";
    private static final int DB_VERSION = 1;

    public TutoraDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
        db.enableWriteAheadLogging();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password_hash TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "email TEXT UNIQUE NOT NULL, " +
                "is_student INTEGER DEFAULT 0, " +
                "is_tutor INTEGER DEFAULT 0, " +
                "is_admin INTEGER DEFAULT 0, " +
                "agreement_accepted INTEGER DEFAULT 0, " +
                "bio TEXT, " +
                "experience TEXT, " +
                "specialization TEXT, " +
                "grade_level TEXT, " +
                "subjects_text TEXT, " +
                "teaching_style TEXT, " +
                "profile_image TEXT)");


        db.execSQL("CREATE TABLE subjects (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE NOT NULL, " +
                "description TEXT)");

        db.execSQL("CREATE TABLE tutor_subjects (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tutor_id INTEGER NOT NULL, " +
                "subject_id INTEGER NOT NULL, " +
                "hourly_rate REAL DEFAULT 0, " +
                "FOREIGN KEY(tutor_id) REFERENCES users(id), " +
                "FOREIGN KEY(subject_id) REFERENCES subjects(id))");

        db.execSQL("CREATE TABLE slots (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tutor_id INTEGER NOT NULL, " +
                "date TEXT NOT NULL, " +
                "time_start TEXT NOT NULL, " +
                "time_end TEXT NOT NULL, " +
                "is_available INTEGER DEFAULT 1, " +
                "FOREIGN KEY(tutor_id) REFERENCES users(id))");

        db.execSQL("CREATE TABLE sessions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_id INTEGER NOT NULL, " +
                "slot_id INTEGER NOT NULL UNIQUE, " +
                "status TEXT DEFAULT 'booked', " +
                "rating INTEGER DEFAULT 0, " +
                "tutor_hours_earned REAL DEFAULT 0, " +
                "payment_amount REAL DEFAULT 0, " +
                "payment_status TEXT DEFAULT 'pending', " +
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(student_id) REFERENCES users(id), " +
                "FOREIGN KEY(slot_id) REFERENCES slots(id))");

        db.execSQL("CREATE TABLE messages (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sender_id INTEGER NOT NULL, " +
                "receiver_id INTEGER NOT NULL, " +
                "message TEXT NOT NULL, " +
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(sender_id) REFERENCES users(id), " +
                "FOREIGN KEY(receiver_id) REFERENCES users(id))");


        seedSubjects(db);
        seedAdmin(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sessions");
        db.execSQL("DROP TABLE IF EXISTS slots");
        db.execSQL("DROP TABLE IF EXISTS tutor_subjects");
        db.execSQL("DROP TABLE IF EXISTS subjects");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    private void seedSubjects(SQLiteDatabase db) {
        insertSubject(db, "Mathematics", "Math academic support");
        insertSubject(db, "Science", "Science academic support");
        insertSubject(db, "Programming", "Programming academic support");
        insertSubject(db, "English", "English academic support");
        insertSubject(db, "Filipino", "Filipino academic support");
    }

    private void insertSubject(SQLiteDatabase db, String name, String description) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        db.insert("subjects", null, values);
    }

    private void seedAdmin(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("username", "admin");
        values.put("password_hash", hashPassword("admin123"));
        values.put("name", "Admin");
        values.put("email", "admin@tutora.com");
        values.put("is_admin", 1);
        db.insert("users", null, values);
    }

    public boolean bookSlot(int studentId, int slotId) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues slotValues = new ContentValues();
            slotValues.put("is_available", 0);

            int updated = db.update(
                    "slots",
                    slotValues,
                    "id = ? AND is_available = 1",
                    new String[]{String.valueOf(slotId)}
            );

            if (updated == 0) {
                return false;
            }

            ContentValues sessionValues = new ContentValues();
            sessionValues.put("student_id", studentId);
            sessionValues.put("slot_id", slotId);
            sessionValues.put("status", "booked");

            long inserted = db.insert("sessions", null, sessionValues);

            if (inserted == -1) {
                return false;
            }

            db.setTransactionSuccessful();
            return true;
        } finally {
            db.endTransaction();
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(password.getBytes());

            StringBuilder builder = new StringBuilder();
            for (byte b : result) {
                builder.append(String.format("%02x", b));
            }

            return builder.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
