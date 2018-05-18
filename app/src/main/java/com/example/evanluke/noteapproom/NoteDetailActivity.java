package com.example.evanluke.noteapproom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by evanluke on 5/18/18.
 */

public class NoteDetailActivity extends AppCompatActivity {

    private NoteViewModel mNoteViewModel;
    private int id;
    EditText mEditText;
    EditText mEditTextBody;
    Button mSaveButton;
    TextView mTitleText;
    TextView mBodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        mEditText = findViewById(R.id.editText);
        mEditTextBody = findViewById(R.id.editBodyText);
        mSaveButton = findViewById(R.id.saveBtn);
        mTitleText = findViewById(R.id.txtViewTitle);
        mBodyText = findViewById(R.id.txtViewBody);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("ITEM_DETAIL_KEY");
        } else {
            //error
            finish();
        }

        // COMPLETED (12) Observe the LiveData object in the ViewModel. Use it also when removing the observer
        mNoteViewModel.getNote(id).observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                mNoteViewModel.getNote(id).removeObserver(this);
                String title = note.getTitle();
                mTitleText.setText(title);
                mEditText.setText(title);
                String body = note.getBody();
                //mBodyText.append(body);
                mEditTextBody.setText(body);
            }
        });

    }

    public void onSaveButtonClicked() {
        String title = mEditText.getText().toString();
        String body = mEditTextBody.getText().toString();
        if(title != null && body != null) {
            final Note note = new Note(title, body);
            note.setId(id);
            mNoteViewModel.update(note);
            finish();
        } else {
            Toast.makeText(NoteDetailActivity.this, "Count must be 0 or greater.", Toast.LENGTH_LONG);
        }
    }

}