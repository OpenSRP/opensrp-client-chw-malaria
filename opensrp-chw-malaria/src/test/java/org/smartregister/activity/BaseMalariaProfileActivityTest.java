package org.smartregister.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.smartregister.chw.malaria.activity.BaseMalariaProfileActivity;
import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.domain.AlertStatus;
import org.smartregister.malaria.R;

import static org.mockito.Mockito.validateMockitoUsage;

public class BaseMalariaProfileActivityTest {
    @Mock
    public BaseMalariaProfileActivity baseMalariaProfileActivity;

    @Mock
    public MalariaProfileContract.Presenter profilePresenter;

    @Mock
    public View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseMalariaProfileActivity);
    }

    @Test
    public void setOverDueColor() {
        baseMalariaProfileActivity.setOverDueColor();
        Mockito.verify(view, Mockito.never()).setBackgroundColor(Color.RED);
    }

    @Test
    public void formatTime() {
        BaseMalariaProfileActivity activity = new BaseMalariaProfileActivity();
        try {
            Assert.assertEquals("25 Oct 2019", Whitebox.invokeMethod(activity, "formatTime", "25-10-2019"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkHideView() {
        baseMalariaProfileActivity.hideView();
        Mockito.verify(view, Mockito.never()).setVisibility(View.GONE);
    }

    @Test
    public void checkProgressBar() {
        baseMalariaProfileActivity.showProgressBar(true);
        Mockito.verify(view, Mockito.never()).setVisibility(View.VISIBLE);
    }

    @Test
    public void medicalHistoryRefresh() {
        baseMalariaProfileActivity.refreshMedicalHistory(true);
        Mockito.verify(view, Mockito.never()).setVisibility(View.VISIBLE);
    }

    @Test
    public void onClickBackPressed() {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.title_layout);
        Mockito.doNothing().when(baseMalariaProfileActivity).onBackPressed();
        baseMalariaProfileActivity.onClick(view);
        Mockito.verify(baseMalariaProfileActivity).onBackPressed();
    }

    @Test
    public void onClickOpenMedicalHistory() {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.rlLastVisit);
        Mockito.doNothing().when(baseMalariaProfileActivity).openMedicalHistory();
        baseMalariaProfileActivity.onClick(view);
        Mockito.verify(baseMalariaProfileActivity).openMedicalHistory();
    }

    @Test
    public void onClickOpenUpcomingServices() {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.rlUpcomingServices);
        Mockito.doNothing().when(baseMalariaProfileActivity).openUpcomingService();
        baseMalariaProfileActivity.onClick(view);
        Mockito.verify(baseMalariaProfileActivity).openUpcomingService();
    }

    @Test
    public void onClickOpenFamlilyServicesDue() {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.rlFamilyServicesDue);
        Mockito.doNothing().when(baseMalariaProfileActivity).openFamilyDueServices();
        baseMalariaProfileActivity.onClick(view);
        Mockito.verify(baseMalariaProfileActivity).openFamilyDueServices();
    }

    @Test(expected = Exception.class)
    public void refreshFamilyStatusComplete() throws Exception {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        TextView textView = view.findViewById(R.id.textview_family_has);
        Whitebox.setInternalState(baseMalariaProfileActivity, "tvFamilyStatus", textView);
        Mockito.doNothing().when(baseMalariaProfileActivity).showProgressBar(false);
        baseMalariaProfileActivity.refreshFamilyStatus(AlertStatus.complete);
        Mockito.verify(baseMalariaProfileActivity).showProgressBar(false);
        PowerMockito.verifyPrivate(baseMalariaProfileActivity).invoke("setFamilyStatus", "Family has nothing due");
    }

    @Test(expected = Exception.class)
    public void refreshFamilyStatusNormal() throws Exception {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        TextView textView = view.findViewById(R.id.textview_family_has);
        Whitebox.setInternalState(baseMalariaProfileActivity, "tvFamilyStatus", textView);
        Mockito.doNothing().when(baseMalariaProfileActivity).showProgressBar(false);
        baseMalariaProfileActivity.refreshFamilyStatus(AlertStatus.complete);
        Mockito.verify(baseMalariaProfileActivity).showProgressBar(false);
        PowerMockito.verifyPrivate(baseMalariaProfileActivity).invoke("setFamilyStatus", "Family has services due");
    }

    @Test(expected = Exception.class)
    public void onActivityResult() throws Exception {
        baseMalariaProfileActivity = Mockito.spy(new BaseMalariaProfileActivity());
        Whitebox.invokeMethod(baseMalariaProfileActivity, "onActivityResult", 2244, -1, null);
        Mockito.verify(profilePresenter).saveForm(null);
    }

}
