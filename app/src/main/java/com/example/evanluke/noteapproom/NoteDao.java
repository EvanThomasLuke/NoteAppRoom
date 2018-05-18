package com.example.evanluke.noteapproom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by evanluke on 5/17/18.
 */


@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();


    @Query("SELECT * FROM note_table WHERE id = :id")
    LiveData<Note> getNote(int id);


    //TODO add order by word asc, add order
    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();
}
