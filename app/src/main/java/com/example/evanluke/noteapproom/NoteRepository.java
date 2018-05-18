package com.example.evanluke.noteapproom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by evanluke on 5/17/18.
 */

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    LiveData<Note> getNote(int id) {
        return mNoteDao.getNote(id);
    }

    public void insert (Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }


    private static class insertAsyncTask extends android.os.AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncNoteDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncNoteDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncNoteDao.insert(params[0]);
            return null;
        }
    }

    public void update (Note note) {new updateAsyncTask(mNoteDao).execute(note); }

    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncNoteDao;

        updateAsyncTask(NoteDao dao) {
            mAsyncNoteDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncNoteDao.update(params[0]);
            return null;
        }
    }

}