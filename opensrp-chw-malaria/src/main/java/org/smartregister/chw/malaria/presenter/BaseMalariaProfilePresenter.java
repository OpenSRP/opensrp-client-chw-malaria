package org.smartregister.chw.malaria.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;

import java.lang.ref.WeakReference;


public class BaseMalariaProfilePresenter implements MalariaProfileContract.Presenter {
    protected WeakReference<MalariaProfileContract.View> view;
    protected MemberObject memberObject;
    protected MalariaProfileContract.Interactor interactor;
    protected Context context;

    public BaseMalariaProfilePresenter(MalariaProfileContract.View view, MalariaProfileContract.Interactor interactor, MemberObject memberObject) {
        this.view = new WeakReference<>(view);
        this.memberObject = memberObject;
        this.interactor = interactor;
    }

    @Override
    public void fillProfileData(MemberObject memberObject) {
        if (memberObject != null && getView() != null) {
            getView().setProfileViewWithData();
        }
    }

    @Override
    public void recordMalariaButton(int days_from_malaria_test_date) {
        if (getView() == null) {
            return;
        }

        if (days_from_malaria_test_date < 7 || days_from_malaria_test_date > 14) {
            getView().hideView();
        } else if (days_from_malaria_test_date <= 10) {
            getView().setDueColor();
        } else
            getView().setOverDueColor();
    }

    @Override
    @Nullable
    public MalariaProfileContract.View getView() {
        if (view != null && view.get() != null)
            return view.get();

        return null;
    }

    @Override
    public void refreshProfileBottom() {
        interactor.refreshProfileInfo(memberObject, getView());
    }
}
