package org.smartregister.chw.malaria.provider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class MalariaRegisterProviderTest {
    @Mock
    private MalariaRegisterProvider malariaRegisterProvider;

    @Mock
    private MalariaRegisterProvider.RegisterViewHolder registerViewHolder;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateClients() {
        assertNull(malariaRegisterProvider.updateClients(null, null, null, null));
    }

    @Test
    public void newFormLauncher() {
        assertNull(malariaRegisterProvider.newFormLauncher(null, null, null));
    }

    @Test
    public void assertRegisterViewHolderNotNull() {
        assertNotNull(registerViewHolder);
    }
}