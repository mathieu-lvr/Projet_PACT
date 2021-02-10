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
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
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
        String credits = ((TextView) activity.findViewById(R.id.credits)).getText().toString();
        assertThat(credits, equalTo("Crédits :"));
        String nbCredits = ((TextView) activity.findViewById(R.id.nbCredits)).getText().toString();
        assertThat(nbCredits, equalTo("0"));
        String text1 = ((TextView) activity.findViewById(R.id.text1)).getText().toString();
        assertThat(text1, equalTo("Choix :"));
        String buttonRetrait = ((Button) activity.findViewById(R.id.buttonRetrait)).getText().toString();
        assertThat(buttonRetrait, equalTo("Retrait"));
        String buttonDepot = ((Button) activity.findViewById(R.id.buttonDepot)).getText().toString();
        assertThat(buttonDepot, equalTo("Dépôt"));
        String deconnection = ((Button) activity.findViewById(R.id.deconnection)).getText().toString();
        assertThat(deconnection, equalTo("Déconnection"));
    }

    @Test
    public void buttonClickShouldStartNewActivity() throws Exception
    {
        Button buttonRetrait = (Button) activity.findViewById( R.id.buttonRetrait );
        buttonRetrait.performClick();
        Intent intentToRetrait = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(RetraitActivity.class.getCanonicalName(), intentToRetrait.getComponent().getClassName());

        Button buttonDepot = (Button) activity.findViewById( R.id.buttonDepot );
        buttonDepot.performClick();
        Intent intentToDepot = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(DepotActivity.class.getCanonicalName(), intentToDepot.getComponent().getClassName());

        Button buttonDeconnection = (Button) activity.findViewById( R.id.deconnection );
        buttonDeconnection.performClick();
        Intent intentToDeco = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(ConnexionActivity.class.getCanonicalName(), intentToDeco.getComponent().getClassName());
    }
}
