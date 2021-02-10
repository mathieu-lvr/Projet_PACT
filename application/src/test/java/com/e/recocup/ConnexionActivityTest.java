package com.e.recocup;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 24)
public class ConnexionActivityTest {

    private ConnexionActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ConnexionActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void checkNames() throws Exception {
        String connexion = ((TextView) activity.findViewById(R.id.connexion)).getText().toString();
        assertThat(connexion, equalTo("CONNEXION"));
        String id = ((TextView) activity.findViewById(R.id.identifiant)).getText().toString();
        assertThat(id, equalTo("Identifiant:"));
        String login = ((EditText) activity.findViewById(R.id.login)).getHint().toString();
        assertThat(login, equalTo("login"));
        String mdp = ((TextView) activity.findViewById(R.id.mdp)).getText().toString();
        assertThat(mdp, equalTo("Mot de passe:"));
        String password = ((EditText) activity.findViewById(R.id.password)).getHint().toString();
        assertThat(password, equalTo("password"));
        String se_connecter = ((Button) activity.findViewById(R.id.se_connecter)).getText().toString();
        assertThat(se_connecter, equalTo("Se connecter"));
    }

    @Test
    public void buttonClickShouldStartNewActivity() throws Exception
    {
        Button buttonToMain = (Button) activity.findViewById( R.id.se_connecter );
        buttonToMain.performClick();
        Intent intentToMain = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(MainActivity.class.getCanonicalName(), intentToMain.getComponent().getClassName());

        Button buttonToCreate = (Button) activity.findViewById( R.id.create_new_account );
        buttonToCreate.performClick();
        Intent intentToCreate = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(CreateAccountActivity.class.getCanonicalName(), intentToCreate.getComponent().getClassName());
    }
}
