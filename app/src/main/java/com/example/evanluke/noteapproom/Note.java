package com.example.evanluke.noteapproom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by evanluke on 5/17/18.
 */


@Entity(tableName = "note_table")
public class Note {

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "body")
    private String mBody;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    public Note(@NonNull String mTitle, @NonNull String mBody) {
        this.mTitle = mTitle;
        this.mBody = mBody;
    }

    public Note(@NonNull int id, @NonNull String mTitle, @NonNull String mBody) {
        this.mTitle = mTitle;
        this.id = id;
        this.mBody = mBody;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@NonNull String mTitle) {
        this.mTitle = mTitle;
    }

    @NonNull String getBody() {
        return mBody;
    }

    public void setBody(@NonNull String mBody) {
        this.mBody = mBody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

