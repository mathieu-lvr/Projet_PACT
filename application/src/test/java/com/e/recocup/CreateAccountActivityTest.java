package com.e.recocup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 24)
public class CreateAccountActivityTest {

    private CreateAccountActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(CreateAccountActivity.class)
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
        String titre = ((TextView) activity.findViewById(R.id.titre)).getText().toString();
        assertThat(titre, equalTo("Create a new account"));

        String id_txt = ((TextView) activity.findViewById(R.id.id_txt)).getText().toString();
        assertThat(id_txt, equalTo("Identifiant"));
        String id_edit = ((EditText) activity.findViewById(R.id.id_edit)).getHint().toString();
        assertThat(id_edit, equalTo("identifiant"));

        String num_txt = ((TextView) activity.findViewById(R.id.num_txt)).getText().toString();
        assertThat(num_txt, equalTo("numéro de téléphone"));
        String num_edit = ((EditText) activity.findViewById(R.id.num_edit)).getHint().toString();
        assertThat(num_edit, equalTo("06..."));

        String email_txt = ((TextView) activity.findViewById(R.id.email_txt)).getText().toString();
        assertThat(email_txt, equalTo("adresse mail"));
        String email_edit = ((EditText) activity.findViewById(R.id.email_edit)).getHint().toString();
        assertThat(email_edit, equalTo("...@..."));

        String mdp_txt = ((TextView) activity.findViewById(R.id.mdp_txt)).getText().toString();
        assertThat(mdp_txt, equalTo("Mot de passe"));
        String mdp_edit = ((EditText) activity.findViewById(R.id.mdp_edit)).getHint().toString();
        assertThat(mdp_edit, equalTo("......."));

        String confmdp_txt = ((TextView) activity.findViewById(R.id.confmdp_txt)).getText().toString();
        assertThat(confmdp_txt, equalTo("Confirmation du mot de passe"));
        String confmdp_edit = ((EditText) activity.findViewById(R.id.confmdp_edit)).getHint().toString();
        assertThat(confmdp_edit, equalTo("......."));

        String create = ((Button) activity.findViewById(R.id.create)).getText().toString();
        assertThat(create, equalTo("Créer"));
    }

}

// Tester le bouton de retour ?