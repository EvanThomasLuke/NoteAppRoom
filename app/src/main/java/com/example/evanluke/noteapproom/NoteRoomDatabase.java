package com.example.evanluke.noteapproom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by evanluke on 5/17/18.
 */

@Database(entities = {Note.class}, version =1)
public abstract class NoteRoomDatabase extends RoomDatabase {


    public abstract NoteDao noteDao();

    private static NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (NoteRoomDatabase.class){
                if (INSTANCE == null) {
                    //Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "inventory_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;

        PopulateDbAsync(NoteRoomDatabase db) {
            mDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Note note = new Note("Hello", "YO");
            mDao.insert(note);
            note = new Note("World", "YO");
            mDao.insert(note);
            return null;
        }
    }

}
