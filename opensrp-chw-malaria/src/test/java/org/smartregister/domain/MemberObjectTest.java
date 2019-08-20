package org.smartregister.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.commonregistry.CommonPersonObjectClient;

import static org.mockito.Mockito.validateMockitoUsage;

public class MemberObjectTest {
    @Mock
    private CommonPersonObjectClient client = Mockito.mock(CommonPersonObjectClient.class);

    private MemberObject memberObject = new MemberObject(client);

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void testSetFirstName() {
        memberObject.setFirstName("Denis");
        Assert.assertEquals("Denis", memberObject.getFirstName());
    }

    @Test
    public void testIsClosed() {
        memberObject.setIsClosed(false);
        Assert.assertFalse(memberObject.getIsClosed());
    }
}

