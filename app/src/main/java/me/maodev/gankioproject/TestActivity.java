package me.maodev.gankioproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class TestActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        GlideImageLoader.LoadImage( iv,"http://img.gank.io/ceb8a9c3-aa52-4e11-8610-44ca7ce5f753");

    }




}
