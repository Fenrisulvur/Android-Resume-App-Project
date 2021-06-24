package com.example.Resume;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Comments extends AppCompatActivity {
    ArrayList<Comment> commentList;

    private void saveData(){
        SharedPreferences sharedPrefs = getSharedPreferences("CommentData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(commentList);
        editor.putString("Comments", json);
        editor.commit();

    }

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
    public String toString() {
        return "MainActivity{}";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        loadData();

        getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, new HomeFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, new GithubFragment()).commit();
    }

    public void onBackPressed() {
        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);
    }



    public void submitClick(View view) {
        EditText nbox = findViewById(R.id.tboxName);
        EditText tbox = findViewById(R.id.tboxComment);
        Intent newIntent = new Intent(this, MainActivity.class);

        commentList.add( new Comment(""+nbox.getText(),""+tbox.getText()) );
        saveData();
        startActivity(newIntent);
    }
}