package org.smartregister.activity;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.smartregister.chw.malaria.activity.BaseMalariaRegisterActivity;

public class BaseMalariaRegisterActivityTest {
    @Mock
    private BaseMalariaRegisterActivity baseMalariaRegisterActivity = new BaseMalariaRegisterActivity();

    @Test
    public void testGetLocationID() {
        Assert.assertEquals(BaseMalariaRegisterActivity.class,
                baseMalariaRegisterActivity.getFamilyFormActivity());
    }
}
