package com.e.recocup;

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
public class DepotActivityTest {

    private DepotActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(DepotActivity.class)
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
        String genererQRC = ((TextView) activity.findViewById(R.id.genererQRC)).getText().toString();
        assertThat(genererQRC, equalTo("QR-code :"));
    }
}
