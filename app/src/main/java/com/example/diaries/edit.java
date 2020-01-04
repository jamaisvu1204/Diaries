package com.example.diaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class edit extends AppCompatActivity {

    private EditText title,content;
    private Button submit,search;
    private DatabaseHelper databaseHelper;
    private ListView mlist;
    private List<Data> mData = null;
    private MyAdapter mAdapter = null;
    private Context mContext = null;
    //数据库名称
    private static final String DATABASE_NAME="diaries.db";
    //数据库版本号
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="diaries";
    private SQLiteDatabase db;
    private StringBuilder sb;
    static String titlestring=null;
    static String contentstring=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit);
        super.onCreate(savedInstanceState);
        title = (EditText)findViewById(R.id.edittitle);
        content = (EditText) findViewById(R.id.editcontent);
        submit = (Button)findViewById(R.id.submit);
        search = (Button) findViewById(R.id.search);
        mContext = edit.this;
        bindViews();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titlestring=title.getText().toString();
                contentstring=content.getText().toString();
                mData = new LinkedList<Data>();
                sb=new StringBuilder();
                if(titlestring.equals(""))
                    Toast.makeText(edit.this,"标题不能为空",Toast.LENGTH_SHORT).show();
                else {
                    databaseHelper=new DatabaseHelper(edit.this,DATABASE_NAME,null,DATABASE_VERSION);
                    db =  databaseHelper.getReadableDatabase();
                    db.execSQL("insert into diaries(title,content) values(?,?)",new String[]{titlestring,contentstring});
                    Toast.makeText(edit.this, "提交成功！", Toast.LENGTH_LONG).show();
                    Intent b=new Intent(edit.this,MainActivity.class);
                    startActivity(b);
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titlestring=title.getText().toString();
                contentstring=content.getText().toString();
                Intent i = new Intent(edit.this,pre.class);
                startActivity(i);

            }
        });
    }
    private void bindViews(){
        LayoutInflater factory = LayoutInflater.from(edit.this);
        View layout = factory.inflate(R.layout.activity_main, null);
        mlist = (ListView) layout.findViewById(R.id.list);
    }

}
