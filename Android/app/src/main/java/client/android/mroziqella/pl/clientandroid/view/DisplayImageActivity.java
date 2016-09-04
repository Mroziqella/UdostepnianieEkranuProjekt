package client.android.mroziqella.pl.clientandroid.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import client.android.mroziqella.pl.clientandroid.R;
import client.android.mroziqella.pl.clientandroid.another.AppConfig;
import client.android.mroziqella.pl.clientandroid.service.RestImageGET;
import client.android.mroziqella.pl.clientandroid.service.SendCoordPOST;

public class DisplayImageActivity extends Activity {
    private ImageView imageView;
    private Button button;
    private Handler handler = new Handler();
    private CheckBox sendClick;
    private final Activity activity = this;
    private Runnable timedTask = new Runnable() {
        @Override
        public void run() {
            new RestImageGET(imageView, activity).execute(AppConfig.URL_SERVER+"/image/picture/" + MainActivity.getRoomName().getText());
            handler.postDelayed(timedTask, 600);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.connect);
        sendClick=(CheckBox) findViewById(R.id.sendClick);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(sendClick.isChecked()) {
                    new SendCoordPOST((int) event.getX(), (int) event.getY())
                            .execute(AppConfig.URL_SERVER+"/rest/coord/" + MainActivity.getRoomName().getText());
                }

                return true;
            }
        });
    }

    public void getImage(View view) {
        handler.post(timedTask);
        button.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timedTask = null;

    }


}
