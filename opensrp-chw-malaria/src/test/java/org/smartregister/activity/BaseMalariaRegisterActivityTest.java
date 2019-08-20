package org.smartregister.activity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.smartregister.chw.malaria.activity.BaseMalariaRegisterActivity;
import org.smartregister.chw.malaria.util.Constants;

import static org.mockito.Mockito.validateMockitoUsage;

public class BaseMalariaRegisterActivityTest {

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Mock
    private BaseMalariaRegisterActivity baseMalariaRegisterActivity = new BaseMalariaRegisterActivity();

    @Test
    public void testGetRegistrationForm() {
        Assert.assertEquals(Constants.FORMS.MALARIA_REGISTRATION,
                baseMalariaRegisterActivity.getRegistrationForm());
    }

    @Test
    public void testGetLocationID() {
        Assert.assertEquals(BaseMalariaRegisterActivity.class,
                baseMalariaRegisterActivity.getFamilyFormActivity());
    }

    @Test
    public void testGetFormRegistrationEvent() {
        Assert.assertEquals(Constants.EVENT_TYPE.MALARIA_CONFIRMATION,
                baseMalariaRegisterActivity.getFormRegistrationEvent());
    }

    @Test
    public void testGetFormEditRegistrationEvent() {
        Assert.assertEquals(Constants.EVENT_TYPE.MALARIA_CONFIRMATION,
                baseMalariaRegisterActivity.getFormEditRegistrationEvent());
    }

}
