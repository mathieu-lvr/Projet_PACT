package com.e.recocup;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.network.HTTPError;
import com.e.network.NetworkInterface;
import com.google.zxing.WriterException;

import java.io.IOException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class DepotActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView QRC;
    private String qrc_text;
    private NetworkInterface iServerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Dépôt");
        setContentView(R.layout.activity_depot);

        QRC = (ImageView) findViewById(R.id.QRC);

        iServerInterface = (NetworkInterface) ConnexionActivity.iServerInterface;
        try {
            qrc_text = iServerInterface.getQRCode(iServerInterface.getCurrentUserId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HTTPError httpError) {
            httpError.printStackTrace();
        }

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        QRGEncoder qrgEncoder = new QRGEncoder(qrc_text, null, QRGContents.Type.TEXT, smallerDimension);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            QRC.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v("TAG", e.toString());
        }
    }
}
