package com.example.isp30.wearetheone.TripAll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBRaiting extends SQLiteOpenHelper {
    public DBRaiting(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RAITING (_id INTEGER PRIMARY KEY AUTOINCREMENT,restaurant TEXT,rate FLOAT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(String restaurant, float rate){
        //읽고 쓰기가 가능하게 DB열기
        SQLiteDatabase db = getWritableDatabase();
        //DB에 입력한 값으로 행 추카
        db.execSQL("INSERT INTO RAITING VALUES(null, '" + restaurant + "', " + rate + ");");
        db.close();
    }
    public void update(String restaurant, float rate) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE RAITING SET rate=" + rate + " WHERE restaurant='" + restaurant + "';");
        db.close();
    }
    public String getResult(){
        //읽기가 가능하게 DB열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        //DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM RAITING",null);
        while (cursor.moveToNext()){
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getDouble(2)
                    + cursor.getString(3)
                    + "\n";
        }
        return result;
    }
}
