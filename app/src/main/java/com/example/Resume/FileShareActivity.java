package com.example.Resume;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileShareActivity extends AppCompatActivity {
    private File mImagesDir;
    File[] mImageFiles;
    String[] mImageFileNames;
    Intent mResultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_share);

        mResultIntent = new Intent("com.example.myapp.ACTION_RETURN_FILE");

        mImagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        mImageFiles = mImagesDir.listFiles();

        setResult(Activity.RESULT_CANCELED, null);

        int i = 0;
        mImageFileNames = new String[mImageFiles.length];
        try {
            for (File f : mImageFiles){
                mImageFileNames[i] = f.getAbsolutePath();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView mFileListView = findViewById(R.id.mFileListView);

        final List<String> fileList = new ArrayList<>(Arrays.asList(mImageFileNames));
        final ArrayAdapter<String>  arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,fileList);
        mFileListView.setAdapter(arrayAdapter);

        mFileListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                File requestFile = new File(mImageFileNames[position]);
                Uri fileUri = null;
                try{
                    fileUri = FileProvider.getUriForFile(FileShareActivity.this, "com.example.ihopeihaveeverythingdownloaded", requestFile);
                } catch (Exception e){
                    e.printStackTrace();
                }

                if(fileUri!=null){
                    mResultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    mResultIntent.setDataAndType(fileUri, getContentResolver().getType(fileUri));
                    FileShareActivity.this.setResult(Activity.RESULT_OK, mResultIntent);
                }else{
                    mResultIntent.setDataAndType(null,"");
                    FileShareActivity.this.setResult(RESULT_CANCELED, mResultIntent);
                }

            }
        });


     }

     public void onDoneClick(View v){
        finish();
     }

}