package client.android.mroziqella.pl.clientandroid.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import client.android.mroziqella.pl.clientandroid.R;
import client.android.mroziqella.pl.clientandroid.view.ChangeMode;
import client.android.mroziqella.pl.clientandroid.view.DisplayImageActivity;
import client.android.mroziqella.pl.clientandroid.view.MainActivity;

/**
 * Created by Kamil on 12/05/2016.
 */
public class RestLoginGET extends WebServiceHandlerGET {

   private Activity activity;
    private ProgressDialog dialog;

    public RestLoginGET(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Czekaj...");
        dialog.show();
    }
    @Override
    protected void onPostExecute(String result) {
        // chowamy okno dialogowe
        dialog.dismiss();
        try {
            if(result.equals("false")){
                throw new Exception();
            }
            Intent intent = new Intent(activity, ChangeMode.class);
            activity.startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(), "Błąd logowania sprawdź wprowadzone dane", Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }
    }
}
