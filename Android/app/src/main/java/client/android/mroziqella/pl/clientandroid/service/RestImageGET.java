package client.android.mroziqella.pl.clientandroid.service;


import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Base64;

import android.widget.ImageView;

import client.android.mroziqella.pl.clientandroid.another.AppConfig;
import client.android.mroziqella.pl.clientandroid.view.MainActivity;


/**
 * Created by Kamil on 12/05/2016.
 */
public class RestImageGET extends WebServiceHandlerGET {
    private Activity activity;
    private Bitmap decodedByte;
    private ImageView imageView;

    public RestImageGET(ImageView imageView,Activity activity) {
        this.activity=activity;
        this.imageView = imageView;
    }


    @Override
    protected void onPostExecute(String result) {
        try {
            byte[] decodedString = Base64.decode(result, Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
            new RestImageSizeGET(imageView,activity).execute(AppConfig.URL_SERVER+"/rest/sizeImage/"+ MainActivity.getRoomName().getText());
        } catch (java.lang.IllegalArgumentException e) {
        }


    }


}
