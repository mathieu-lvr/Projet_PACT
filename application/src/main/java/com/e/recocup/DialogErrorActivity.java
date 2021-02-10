package com.e.recocup;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class DialogErrorActivity extends Activity {

    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_error);

        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(okListener);
    }

    private View.OnClickListener okListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CreateAccountActivity.reset();
            finish();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ok.callOnClick();
            return true;
        }
        return false;
    };
}
