package com.example.evanluke.noteapproom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by evanluke on 5/17/18.
 */

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;

    private LiveData<List<Note>> mAllNotes;

    private int id;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }



    LiveData<List<Note>> getAllNotes() { return mAllNotes; }

    public void insert(Note note) { mRepository.insert(note); }

    public void update(Note note) {mRepository.update(note); }

    public LiveData<Note> getNote(int id) {return mRepository.getNote(id); }
}