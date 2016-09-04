package client.android.mroziqella.pl.clientandroid.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import client.android.mroziqella.pl.clientandroid.R;

public class ChangeMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mode);
    }
    public void controlPanel(View view){
        Intent intent = new Intent(this, DisplayImageActivity.class);
        this.startActivity(intent);
    }
    public void viewPanel(View view){
        Intent intent = new Intent(this, ZoomInZoomOut.class);
        this.startActivity(intent);
    }
}
