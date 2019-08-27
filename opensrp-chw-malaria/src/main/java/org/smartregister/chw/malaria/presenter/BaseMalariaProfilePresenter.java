package org.smartregister.chw.malaria.presenter;

import android.content.Context;
import android.widget.Toast;
import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.malaria.R;


public class BaseMalariaProfilePresenter {
    protected MalariaProfileContract.View view;
    protected MemberObject memberObject;
    protected Context context;


    public BaseMalariaProfilePresenter(MalariaProfileContract.View view, MemberObject memberObject) {
        this.view = view;
        this.memberObject = memberObject;
    }

    public void attachView(MalariaProfileContract.View view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void fillProfileData(MemberObject memberObject) {
        String entityType = memberObject.getBaseEntityId();
        if (memberObject != null) {
            view.setProfileViewWithData();
            view.setProfileImage(memberObject.getBaseEntityId(), entityType);
        }
    }

    public void recordMalariaButton(int days_from_malaria_test_date) {
        if (days_from_malaria_test_date < 7) {
            view.hideView();
        } else if (days_from_malaria_test_date < 10) {
            view.setDueColor();
        } else
            view.setOverDueColor();
    }


    public void recordMalariaFollowUp(Context context) {
        Toast.makeText(context, R.string.record_malaria, Toast.LENGTH_SHORT).show();
    }

}
