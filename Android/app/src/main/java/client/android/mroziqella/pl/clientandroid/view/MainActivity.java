package client.android.mroziqella.pl.clientandroid.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import client.android.mroziqella.pl.clientandroid.R;
import client.android.mroziqella.pl.clientandroid.another.AppConfig;
import client.android.mroziqella.pl.clientandroid.service.RestLoginGET;


// REST implementacja = http://damianchodorek.com/2015/08/09/kurs-android-async-task-web-service-rest-11/

public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private static EditText roomName;
    private EditText roomPassowrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roomName = (EditText) findViewById(R.id.roomName);
        roomPassowrd= (EditText) findViewById(R.id.passwordRoom);


    }
    public void connect(View view){
        RestLoginGET restLoginGET = new RestLoginGET(this);
        restLoginGET.execute(AppConfig.URL_SERVER+"/rest/login/"+roomName.getText()+"?password="+roomPassowrd.getText());
    }

    public static EditText getRoomName() {
        return roomName;
    }
}


