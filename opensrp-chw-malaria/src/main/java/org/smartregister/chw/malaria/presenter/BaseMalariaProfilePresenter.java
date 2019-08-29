package org.smartregister.chw.malaria.presenter;

import android.content.Context;
import android.widget.Toast;
import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.malaria.R;
import org.smartregister.view.contract.BaseProfileContract;


public class BaseMalariaProfilePresenter implements BaseProfileContract.Presenter, MalariaProfileContract.InteractorCallBack, MalariaProfileContract.Presenter {

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

    }
}
