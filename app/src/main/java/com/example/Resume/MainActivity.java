package com.example.Resume;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Comment> commentList;

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
    public void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().add(R.id.frameTest, new GithubFragment()).commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Orientation
        Snackbar.make(findViewById(R.id.versionBox), "Orientation: "+ (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? "Landscape" : "Portrait"), Snackbar.LENGTH_SHORT).show();

        //Version
        Toast.makeText(this, ""+(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? "Thank you for being up to date!" : "Please update your android version to 8.0 or higher!"), Toast.LENGTH_SHORT).show();
        TextView ver = findViewById(R.id.versionBox);
        ver.setText( "API LEVEL: "+Build.VERSION.SDK_INT );

        //Animator
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(ver, "textColor", Color.WHITE, (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Color.GREEN : Color.RED) );
        colorAnim.setDuration(10000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();

        //Comments
        loadData();
        TextView comName = findViewById(R.id.txtCommentor);
        TextView comText = findViewById(R.id.txtComment);
        Comment latest = null;
        if (commentList.size()>0){
            latest = commentList.get(commentList.size()-1);
        }
        comName.setText(latest != null && ""+latest.getAuthor().trim() != "" ? "Commenter: "+latest.getAuthor() : "Anonymous");
        comText.setText(latest != null && ""+latest.getComment().trim() != "" ? latest.getComment() : "No comment to show :C");

        //Fragments
        getSupportFragmentManager().beginTransaction().add(R.id.frameTest, new GithubFragment()).commit();

    }

    public void combtnClick(View view) {
        Intent myIntent = new Intent(this, Comments.class);
        startActivity(myIntent);
    }

    public void intentClick(View view) {
        Intent myIntent = new Intent(this, IntentExamples.class);
        startActivity(myIntent);
    }

    public void viewCommentsClick(View view) {
        Intent myIntent = new Intent(this, ViewComments.class);
        startActivity(myIntent);
    }

    public void portfolioClick(View view) {
        Intent myIntent = new Intent(this, Portfolio_Activity.class);
        startActivity(myIntent);
    }

}