package org.smartregister.activity;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.malaria.activity.BaseMalariaProfileActivity;

import static org.mockito.Mockito.validateMockitoUsage;

public class BaseMalariaProfileActivityTest {
    @Mock
    protected BaseMalariaProfileActivity baseMalariaProfileActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

}
