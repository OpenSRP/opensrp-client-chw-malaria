package org.smartregister.presenter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.malaria.contract.MalariaRegisterFragmentContract;
import org.smartregister.chw.malaria.presenter.BaseMalariaRegisterFragmentPresenter;

import static org.mockito.Mockito.validateMockitoUsage;

public class BaseMalariaRegisterFragmentPresenterTest {
    @Mock
    protected MalariaRegisterFragmentContract.View view;
    @Mock
    protected MalariaRegisterFragmentContract.Model model;
    private BaseMalariaRegisterFragmentPresenter baseMalariaRegisterFragmentPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        baseMalariaRegisterFragmentPresenter = new BaseMalariaRegisterFragmentPresenter(view, model, "");
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseMalariaRegisterFragmentPresenter);
    }

    @Test
    public void testGetMainCondition() {
        Assert.assertEquals("", baseMalariaRegisterFragmentPresenter.getMainCondition());
    }

}