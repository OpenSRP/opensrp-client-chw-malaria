package org.smartregister.activity;

import android.graphics.Color;
import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.smartregister.chw.malaria.activity.BaseMalariaProfileActivity;

public class BaseMalariaProfileActivityTest {
    @Mock
    protected BaseMalariaProfileActivity baseMalariaProfileActivity;

    @Mock
    protected View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseMalariaProfileActivity);
    }

    @Test
    public void setProfileViewWithData() {
        baseMalariaProfileActivity.setProfileViewWithData();
        Mockito.verify(view, Mockito.never()).setVisibility(View.VISIBLE);
    }

    @Test
    public void setDueColor() {
        baseMalariaProfileActivity.setDueColor();
        Mockito.verify(view, Mockito.never()).setBackgroundColor(Color.BLUE);
    }

    @Test
    public void setOverDueColor() {
        baseMalariaProfileActivity.setOverDueColor();
        Mockito.verify(view, Mockito.never()).setBackgroundColor(Color.RED);
    }

    @Test
    public void formatTime() {
        BaseMalariaProfileActivity activity = new BaseMalariaProfileActivity();
        try {
            Assert.assertEquals("25 Oct 2019", Whitebox.invokeMethod(activity, "formatTime","25-10-2019"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
