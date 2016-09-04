package client.android.mroziqella.pl.clientandroid.service;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import client.android.mroziqella.pl.clientandroid.view.MainActivity;


public class WebServiceHandlerGET extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();

            // pobranie danych do InputStream
            InputStream in = new BufferedInputStream(connection.getInputStream());

            // konwersja InputStream na String
            // wynik będzie przekazany do metody onPostExecute()
            return streamToString(in);

        } catch (Exception e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
            return null;
        }

    }

    /**
     * konwersja z InputStream do String
     **/

    public static String streamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

        } catch (IOException e) {
            Log.d(WebServiceHandlerGET.class.getSimpleName(), e.toString());
        }
        return stringBuilder.toString();
    }


}

