package com.example.diaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class show extends AppCompatActivity {

    private TextView title;
    private TextView content;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
    }

    public void init() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        Intent intent = this.getIntent();
        if (intent != null) {
            data = new Data();

            data.settitle(intent.getStringExtra(DatabaseHelper.TITLE));
            data.setContent(intent.getStringExtra(DatabaseHelper.CONTENT));
            data.setId(Integer.valueOf(intent.getStringExtra(DatabaseHelper.ID)));

            title.setText(data.gettitle());
            content.setText(data.getContent());
        }
    }
}
