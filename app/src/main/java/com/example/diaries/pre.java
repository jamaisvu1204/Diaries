package com.example.diaries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.diaries.edit.contentstring;
import static com.example.diaries.edit.titlestring;

public class pre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        TextView pret = (TextView) findViewById(R.id.Title);
        TextView prec = (TextView) findViewById(R.id.Content);
        pret.setText(titlestring);
        prec.setText(contentstring);

    }
}
