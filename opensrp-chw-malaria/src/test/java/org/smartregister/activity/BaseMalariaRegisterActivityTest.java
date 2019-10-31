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

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseMalariaRegisterActivity);
    }

    @Test
    public void testFormConfig() {
        Assert.assertNull(baseMalariaRegisterActivity.getFormConfig());
    }

    @Test
    public void checkFamilyForm() {
        Assert.assertNotNull(baseMalariaRegisterActivity.getFamilyFormActivity());
    }

    @Test
    public void checkIdentifier() {
        Assert.assertNotNull(baseMalariaRegisterActivity.getViewIdentifiers());
    }

}
