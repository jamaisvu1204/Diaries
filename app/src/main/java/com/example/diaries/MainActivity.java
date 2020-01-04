package com.example.diaries;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter = null;
    private Context mContext = null;
    private ListView mlist;
    private DatabaseHelper databaseHelper;
    //数据库名称
    private static final String DATABASE_NAME="diaries.db";
    //数据库版本号
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="diaries";
    private SQLiteDatabase db;
    private StringBuilder sb;
    private ImageButton add;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(MainActivity.this,DATABASE_NAME,null,DATABASE_VERSION);
        init();
    }
    public void init(){
        add = (ImageButton)findViewById(R.id.add);
        list = (ListView)findViewById(R.id.list);
        List<Data> mData = new LinkedList<>();
        db =  databaseHelper.getReadableDatabase();
        //查询数据库中的数据
        Cursor cursor = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        if(cursor.moveToFirst()){
            Data data;
            while (!cursor.isAfterLast()){
                //实例化Data对象
                data = new Data();
                //把数据库中的一个表中的数据赋值给data
                data.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID))));
                data.settitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE)));
                data.setContent(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT)));
                //将data对象存入list对象数组中
                mData.add(data);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        //设置list组件adapter
        final MyAdapter myBaseAdapter = new MyAdapter(mData,MainActivity.this,R.layout.list_item);
        list.setAdapter(myBaseAdapter);

        //按钮点击事件
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, edit.class);
                startActivity(intent);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,show.class);
                Data values = (Data) list.getItemAtPosition(position);
                intent.putExtra(DatabaseHelper.TITLE,values.gettitle().trim());
                intent.putExtra(DatabaseHelper.CONTENT,values.getContent().trim());
                intent.putExtra(DatabaseHelper.ID,values.getId().toString().trim());
                startActivity(intent);
            }
        });

    }

}


