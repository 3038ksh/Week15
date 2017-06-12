package com.example.kimsoohyeong.week15;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    EditText et1, et2;
    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    MyManageDB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        listView = (ListView) findViewById(R.id.etData);
        listView.setAdapter(adapter);

        db = new MyManageDB(getApplicationContext());
    }

    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            String name = et1.getText().toString();
            String nick = et2.getText().toString();

            String sql = "insert into students values(null, '" + name + "', '" + nick + "')";
            db.execINSERTStudent(sql);
        } else {
            String sql = "select * from students order by id";
            showData(db.execSELECTStudent(sql));
        }
    }

    private void showData(Cursor recordSet) {
        try {
            recordSet.moveToFirst();
            if (!recordSet.moveToNext()) return;

            data.clear();
            while (recordSet.moveToNext()) {
                data.add(recordSet.getInt(0) + "/" + recordSet.getString(1) + "/" + recordSet.getString(2));
            }

            recordSet.close();
            toastShow("데이터가 조회되었습니다.");

            adapter.notifyDataSetChanged();
        } catch (SQLiteException e) {
            toastShow("[ERROR]" + e.getMessage());
        }
    }

    private void toastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
