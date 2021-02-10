package com.e.recocup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.e.structures.IUser;
import com.e.structures.UserSecuredInfo;

public class CreateAccountActivity extends AppCompatActivity {

    public static IUser iUser;

    private EditText id;
    private EditText num;
    private EditText email;
    private static EditText mdp;
    private static EditText confmdp;

    private Button create;

    private String id_txt;
    private String num_txt;
    private String email_txt;
    private String mdp_txt;
    private String confmdp_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        id = (EditText) findViewById(R.id.id_edit);
        num = (EditText) findViewById(R.id.num_edit);
        email = (EditText) findViewById(R.id.email_edit);
        mdp = (EditText) findViewById(R.id.mdp_edit);
        confmdp = (EditText) findViewById(R.id.confmdp_edit);

        create = (Button) findViewById(R.id.create);

        id.addTextChangedListener(idWatcher);
        num.addTextChangedListener(numWatcher);
        email.addTextChangedListener(emailWatcher);
        mdp.addTextChangedListener(mdpWatcher);
        confmdp.addTextChangedListener(confmdpWatcher);

        create.setOnClickListener(createListener);
    }

    private TextWatcher idWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            id_txt = s.toString();
        }
    };

    private TextWatcher numWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            num_txt = s.toString();
        }
    };

    private TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            email_txt = s.toString();
        }
    };

    private TextWatcher mdpWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mdp_txt = s.toString();
        }
    };

    private TextWatcher confmdpWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            confmdp_txt = s.toString();
        }
    };

    private View.OnClickListener createListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!confmdp_txt.equals(mdp_txt)) {
                Intent intent = new Intent(CreateAccountActivity.this, DialogErrorActivity.class);
                startActivity(intent);
            }

            UserSecuredInfo securedInfo = new UserSecuredInfo(id_txt, email_txt, mdp_txt);

            try {
                ConnexionActivity.iServerInterface.createAccount(securedInfo);
            } catch (Exception e) {
                //Gérer les erreurs
                e.printStackTrace();
            }
        }
    };

    public static void reset() {
        mdp.getText().clear();
        confmdp.getText().clear();
    }
}
