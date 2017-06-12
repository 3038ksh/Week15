package com.example.kimsoohyeong.week15;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database = null;
    EditText et, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.etdbname);
        et2 = (EditText) findViewById(R.id.etData);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btncreatedb) {
            if (et.getText().toString().equals("")) {
                toastShow("DB이름을 넣으세요");
                et.setFocusable(true);
                return;
            }
            database = openOrCreateDatabase(et.getText().toString(),
                    MODE_PRIVATE, null);
            if (database == null) {
                toastShow("DB 생성에 실패");
            } else {
                toastShow("DB 생성에 성공");
            }
        } else if (v.getId() == R.id.btndeletedb) {
            boolean delete = deleteDatabase(et.getText().toString());
            if (delete) {
                toastShow("DB 삭제 성공");
                database = null;
            } else {
                toastShow("DB 삭제 실패");
            }
        } else if (v.getId() == R.id.btncreateTable) {
            String sql = "create table if not exists students (" +
                    "id integer primary key autoincrement," +
                    "name text not null," +
                    "hakno text)";
            try {
                database.execSQL(sql);
                toastShow("students 테이블 생성");
            } catch (SQLiteException e) {
                toastShow("이미 students 테이블이 있습니다.");
            }
        } else if (v.getId() == R.id.btninsertdata) {
            String sql1 = "insert into students values(null, '홍길동', '20171111')";
            String sql2 = "insert into students values(null, '김순동', '20172222')";
            String sql3 = "insert into students values(null, '최길동', '20173333')";
            try {
                database.execSQL(sql1);
                database.execSQL(sql2);
                database.execSQL(sql3);
                toastShow("데이터가 3개 추가 되었습니다.");
            } catch (SQLiteException e) {
                toastShow("[ERROR]" + e.getMessage());
            }
        } else if (v.getId() == R.id.btnselectdata) {
            String sql1 = "select * from students order by id";

            try {
                Cursor recordSet = database.rawQuery(sql1, null);
                recordSet.moveToFirst();
                String str = "";
                do {
                    str += recordSet.getInt(0) + "/" + recordSet.getString(1) + "/" + recordSet.getString(2) + "\n";
                } while (recordSet.moveToNext());
                recordSet.close();
                et2.setText(str);
                toastShow("데이터가 조회되었습니다.");
            } catch (SQLiteException e) {
                toastShow("[ERROR]" + e.getMessage());
            }
        }
    }

    private void toastShow(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }
}
