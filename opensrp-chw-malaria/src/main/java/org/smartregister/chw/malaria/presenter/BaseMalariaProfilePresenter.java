package org.smartregister.chw.malaria.presenter;

import android.content.Context;
import android.widget.Toast;

import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.domain.AlertStatus;
import org.smartregister.malaria.R;
import org.smartregister.view.contract.BaseProfileContract;

import java.util.Date;


//public class BaseMalariaProfilePresenter implements MalariaProfileContract.Presenter {
public class BaseMalariaProfilePresenter implements BaseProfileContract.Presenter, MalariaProfileContract.InteractorCallBack, MalariaProfileContract.Presenter {

    //    protected WeakReference<MalariaProfileContract.View> view;
    protected MalariaProfileContract.View view;
    protected MemberObject memberObject;
    protected MalariaProfileContract.Interactor interactor;
    protected Context context;

    public BaseMalariaProfilePresenter(MalariaProfileContract.View view, MalariaProfileContract.Interactor interactor, MemberObject memberObject) {
        this.view = view;
        this.memberObject = memberObject;
        this.interactor = interactor;
    }

    @Override
    public void fillProfileData(MemberObject memberObject) {
        String entityType = memberObject.getBaseEntityId();
        if (memberObject != null && getView() != null) {
            getView().setProfileViewWithData();
            view.setProfileImage(memberObject.getBaseEntityId(), entityType);
        }
    }

    @Override
    public void recordMalariaButton(int days_from_malaria_test_date) {
        if (getView() == null)
            return;

        if (days_from_malaria_test_date < 7 || days_from_malaria_test_date > 14) {
            getView().hideView();
        } else if (days_from_malaria_test_date < 10) {
            getView().setDueColor();
        } else
            getView().setOverDueColor();
    }

    @Override
    public void recordMalariaFollowUp(Context context) {
        Toast.makeText(context, R.string.record_malaria, Toast.LENGTH_SHORT).show();
    }

    @Override
    public MalariaProfileContract.View getView() {
        if (view != null) {
            return view;
        } else {
            return null;
        }
    }

    @Override
    public void onDestroy(boolean b) {
//        TODO implement onDestroy
    }

    @Override
    public void refreshProfileBottom() {
        interactor.refreshProfileInfo(memberObject, getView());
    }

    @Override
    public void refreshMedicalHistory(boolean hasHistory) {
//        TODO implement onDestroy
    }

    @Override
    public void refreshUpComingServicesStatus(String service, AlertStatus status, Date date) {
//        TODO implement onDestroy
    }

    @Override
    public void refreshFamilyStatus(AlertStatus status) {
//        TODO implement onDestroy
    }

    @Override
    public void setProfileImage(String baseEntityId, String entityType) {
//        TODO implement onDestroy
    }
}
