package com.e.recocup;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.network.IServerInterface;
import com.e.network.NetworkInterface;
import com.e.structures.Credentials;
import com.e.structures.IAuthentificationData;

public class ConnexionActivity extends AppCompatActivity {

    public static IServerInterface iServerInterface;

    private EditText login;
    private EditText password;
    private Button se_connecter;
    private Button new_account;
    private String identifiant;
    private String mdp;

    private IAuthentificationData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        iServerInterface = new NetworkInterface("http://192.168.0.48:8080/api/v1/user");


        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        se_connecter = (Button) findViewById(R.id.se_connecter);
        new_account = (Button) findViewById(R.id.create_new_account);

        login.addTextChangedListener(loginWatcher);
        password.addTextChangedListener(passwordWatcher);
        se_connecter.setOnClickListener(se_connecterListener);
        new_account.setOnClickListener(new_accountListener);

        String new_account_txt = getResources().getString(R.string.create_new_account);
        new_account.setHint(Html.fromHtml(new_account_txt));
    }

    private TextWatcher loginWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            identifiant = s.toString();
        }
    };

    private TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            mdp = s.toString();
        }
    };

    private View.OnClickListener se_connecterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            // Connexion
            data = new Credentials(identifiant, mdp);

            try {
                iServerInterface.connect(data);

                String test = "Identifiant :\n" + identifiant + "\n Mot de passe :\n" + mdp;
                Toast toast = Toast.makeText(ConnexionActivity.this, test, Toast.LENGTH_SHORT);
                toast.show();
                //

                Intent intent = new Intent(ConnexionActivity.this, MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    };

    private View.OnClickListener new_accountListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ConnexionActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        }
    };
}
