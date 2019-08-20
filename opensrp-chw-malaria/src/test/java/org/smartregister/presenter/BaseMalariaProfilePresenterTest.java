package org.smartregister.presenter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.chw.malaria.presenter.BaseMalariaProfilePresenter;
import org.smartregister.commonregistry.CommonPersonObjectClient;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

public class BaseMalariaProfilePresenterTest {
    @Mock
    private CommonPersonObjectClient commonPersonObjectClient;

    @Mock
    private MalariaProfileContract.View view;

    private MemberObject memberObject;

    private BaseMalariaProfilePresenter profilePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        memberObject = new MemberObject(commonPersonObjectClient);
        profilePresenter = new BaseMalariaProfilePresenter(view, memberObject);
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void testAttachView() {
        profilePresenter.attachView(view);
        Assert.assertNotNull(this.view);
    }

    @Test
    public void fillProfileDataCallsSetProfileViewWithDataWhenPassedMemberObject() {
        profilePresenter.fillProfileData(memberObject);
        verify(view).setProfileViewWithData();
    }

    @Test
    public void fillProfileDataDoesntCallsSetProfileViewWithDataIfMemberObjectEmpty() {
        profilePresenter.fillProfileData(null);
        verify(view, never()).setProfileViewWithData();
    }

    @Test
    public void malariaTestDatePeriodIsLessThanSeven() {
        profilePresenter.recordMalariaButton(4);
        verify(view).hideView();
    }

    @Test
    public void malariaTestDatePeriodIsBetweenSevenAndTen() {
        profilePresenter.recordMalariaButton(7);
        verify(view).setDueColor();
    }

    @Test
    public void malariaTestDatePeriodGreaterThanTen() {
        profilePresenter.recordMalariaButton(14);
        verify(view).setOverDueColor();
    }

}
