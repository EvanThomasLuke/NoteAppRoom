package com.example.evanluke.noteapproom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by evanluke on 5/16/18.
 */

public class NewNoteActivity extends AppCompatActivity {

    private EditText mEditTitleView;
    private EditText mEditBodyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        mEditTitleView = findViewById(R.id.titleEditText);
        mEditBodyView = findViewById(R.id.bodyEditText);


        final Button button = findViewById(R.id.saveBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditBodyView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = mEditTitleView.getText().toString();
                    String body = mEditBodyView.getText().toString();
                    //int number = Integer.parseInt(mEditAmountView.getText().toString());
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_TITLE", title);
                    extras.putString("EXTRA_BODY", body);
                    //extras.putInt("EXTRA_REPLY_INT", number);
                    replyIntent.putExtras(extras);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
