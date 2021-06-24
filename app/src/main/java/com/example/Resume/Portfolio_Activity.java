package com.example.Resume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Portfolio_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio);
        getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, new GithubFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, new HomeFragment()).commit();
    }

    public void gitClick(View view){
        goToUrl ( "https://github.com/Fenrisulvur");
    }

    public void onBackPressed() {
        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);

    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}