package com.example.Resume;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewComments extends AppCompatActivity {

    ArrayList<Comment> commentList;
    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private void loadData(){
        SharedPreferences sharedPrefs = getSharedPreferences("CommentData", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Comments",null);
        Type type = new TypeToken<ArrayList<Comment>>() {}.getType();
        commentList = gson.fromJson(json, type);

        if (commentList == null){
            commentList = new ArrayList<>();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comments);
        loadData();
        buildRecyclerView();
        getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, new HomeFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, new GithubFragment()).commit();
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CommentAdapter(commentList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}