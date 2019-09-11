package org.smartregister.presenter;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.chw.malaria.presenter.BaseMalariaProfilePresenter;
import org.smartregister.commonregistry.CommonPersonObjectClient;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class BaseMalariaProfilePresenterTest {
    @Mock
    private CommonPersonObjectClient commonPersonObjectClient = Mockito.mock(CommonPersonObjectClient.class);

    @Mock
    private MalariaProfileContract.View view = Mockito.mock(MalariaProfileContract.View.class);

    @Mock
    private MalariaProfileContract.Interactor interactor = Mockito.mock(MalariaProfileContract.Interactor.class);

    @Mock
    private MemberObject memberObject = new MemberObject(commonPersonObjectClient);

    private BaseMalariaProfilePresenter profilePresenter = new BaseMalariaProfilePresenter(view, interactor, memberObject);


    @Test
    public void fillProfileDataCallsSetProfileViewWithDataWhenPassedMemberObject() {
        profilePresenter.fillProfileData(memberObject);
        verify(view).setProfileViewWithData();
    }

    @Test
    public void fillProfileDataDoesntCallsSetProfileViewWithDataIfMemberObjectEmpty() {
        if (memberObject.getBaseEntityId() != null){
            profilePresenter.fillProfileData(null);
            verify(view, never()).setProfileViewWithData();
        }
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

    @Test
    public void testMemberProfileImage() {
        profilePresenter.fillProfileData(memberObject);
        verify(view).setProfileImage(null,null);
    }
}
