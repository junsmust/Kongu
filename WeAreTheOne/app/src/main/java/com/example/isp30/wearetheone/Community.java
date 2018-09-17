package com.example.isp30.wearetheone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isp30.wearetheone.HelpAll.HelpAdapter;
import com.example.isp30.wearetheone.HelpAll.HelpItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Community extends Fragment {
    private FirebaseDatabase mDatabase,keyDatabase;
    private DatabaseReference mReference;
    private DatabaseReference keyReference;
    private ChildEventListener mChild;
    private UserInfo userInfo;
    private String name;
    private   String title;
    private String time;
    private UserInfo userInfos = new UserInfo();
    private BoardAdapter boardAdapter;
    private ListView boarderlist;
    private List<BoardItems> boardItems;
    private FloatingActionButton floatingActionButton;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public Community() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_community,container,false);

        boarderlist = (ListView)v.findViewById(R.id.border_list);

        boardItems = new ArrayList<BoardItems>();
        initDatabase();


        floatingActionButton = (FloatingActionButton)v.findViewById(R.id.board_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Board.class);
                startActivity(intent);
            }
        });
        mReference = mDatabase.getReference("board");

        // 변경값을 확인할 child 이름
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // boardAdapter.clear();


                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    title = messageData.getValue().toString();
                    name = userInfos.getUserInfo();
                    time = mReference.getKey();
                    boardItems.add(new BoardItems(title,name,time));
                    boardAdapter.getCount();

                }
                boardAdapter.notifyDataSetChanged();
                boarderlist.setSelection(boardAdapter.getCount() - 1); // child 내에 있는 데이터만큼 반복합니다.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        boardAdapter = new BoardAdapter(getContext(),boardItems);
        boarderlist.setAdapter(boardAdapter);

        return v;
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}


