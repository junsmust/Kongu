package com.example.isp30.wearetheone;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;


public class Board extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    private EditText title, message;
    private Button okay;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String board_title,time,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        Intent intent = new Intent(getIntent());

        title = (EditText)findViewById(R.id.etTitle);
        message = (EditText)findViewById(R.id.etMessage);
        okay = (Button)findViewById(R.id.done);

        long now = System.currentTimeMillis();

        Date date = new Date(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        time = simpleDateFormat.format(date);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board_title = title.getText().toString();
                UserInfo userInfo = new UserInfo();
                name = userInfo.getUserInfo();

                BoarderDTO boarderDTO = new BoarderDTO(board_title,name,time);
                databaseReference.child("board").push().setValue(boarderDTO);

                finish();
            }
        });
//        mReference = mDatabase.getReference("board"); // 변경값을 확인할 child 이름
       /* mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter.add(msg2);
                    // child 내에 있는 데이터만큼 반복합니다.
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);
            }*/

         /*   @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };
        mReference.addChildEventListener(mChild);
    }


   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }*/


    }

