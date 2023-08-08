package com.company.harmonytunes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final String MEDIA_PATH=Environment.getExternalStorageDirectory().getPath()+"/";
    private ArrayList<String> songList=new ArrayList<>();
    private MusicAdapter adapter;

    private static final int READ_MEDIA_AUDIO_PERMISSION_CODE = 101;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("Media Path",MEDIA_PATH);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_AUDIO},
                    READ_MEDIA_AUDIO_PERMISSION_CODE);
        } else {

            getAllAudioFiles();
        }

    }

    public void getAllAudioFiles(){

        if(MEDIA_PATH!=null){
            File mainFile=new File(MEDIA_PATH);
            File[] fileList=mainFile.listFiles();
            for(File file:fileList){
                Log.e("Media Path",file.toString());
                if(file.isDirectory()){
                    scanDirectory(file);
                }else{
                    String path=file.getAbsolutePath();
                    if(path.endsWith(".mp3")){
                        songList.add(path);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
        adapter=new MusicAdapter(songList,MainActivity.this);
        recyclerView.setAdapter(adapter);

    }

    public void scanDirectory(File directory){
        if(directory!=null){

            File[] fileList=directory.listFiles();
            if (fileList != null) {
                for(File file:fileList){
                    Log.e("Media Path",file.toString());
                    if(file.isDirectory()){
                        scanDirectory(file);
                    }else{
                        String path=file.getAbsolutePath();
                        if(path.endsWith(".mp3")){
                            songList.add(path);
                        }
                    }
                }
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_MEDIA_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                getAllAudioFiles();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}