package com.e.recocup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.network.HTTPError;
import com.e.network.NetworkInterface;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView credits;
    private Button retrait;
    private Button depot;
    private Button deconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        credits = (TextView) findViewById(R.id.nbCredits);
        retrait = (Button) findViewById(R.id.buttonRetrait);
        depot = (Button) findViewById(R.id.buttonDepot);
        deconnection = (Button) findViewById(R.id.deconnection);

        retrait.setOnClickListener(retraitListener);
        depot.setOnClickListener(depotListener);
        deconnection.setOnClickListener(deconnectionListener);

        try {
            credits.setText(Integer.toString(ConnexionActivity.iServerInterface.getCredits(NetworkInterface.getCurrentUserId())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HTTPError httpError) {
            httpError.printStackTrace();
        }
    }

    // Important ?
    @Override
    public void onResume() {
        super.onResume();
        try {
            credits.setText(Integer.toString(ConnexionActivity.iServerInterface.getCredits(NetworkInterface.getCurrentUserId())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HTTPError httpError) {
            httpError.printStackTrace();
        }
    }


    private View.OnClickListener retraitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, RetraitActivity.class);
            startActivity(intent,  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        }
    };

    private View.OnClickListener depotListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DepotActivity.class);
            startActivity(intent,  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        }
    };

    private View.OnClickListener deconnectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ConnexionActivity.class);
            startActivity(intent);
        }
    };
}
