package org.smartregister.activity;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.malaria.activity.BaseMalariaProfileActivity;

public class BaseMalariaProfileActivityTest {
    @Mock
    protected BaseMalariaProfileActivity baseMalariaProfileActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}
