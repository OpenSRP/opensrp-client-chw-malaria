package org.smartregister.chw.malaria.provider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.smartregister.configurableviews.model.View;

import java.util.Set;

public class MalariaRegisterProviderTest {
    @Mock
    private MalariaRegisterProvider malariaRegisterProvider;

    @Mock
    private Set<View> visibleColumns;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getView() {
        malariaRegisterProvider.getView(null, null, null);
        Mockito.when(visibleColumns.isEmpty()).thenReturn(false);
        Mockito.verify(malariaRegisterProvider, Mockito.never()).populatePatientColumn(null, null);
    }

    @Test
    public void updateClients() {
        Assert.assertNull(malariaRegisterProvider.updateClients(null, null, null, null));
    }

    @Test
    public void newFormLauncher() {
        Assert.assertNull(malariaRegisterProvider.newFormLauncher(null, null, null));
    }
}