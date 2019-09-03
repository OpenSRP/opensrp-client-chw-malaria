package org.smartregister.chw.malaria.presenter;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONObject;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;
import org.smartregister.malaria.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class BaseMalariaRegisterPresenter implements MalariaRegisterContract.Presenter, MalariaRegisterContract.InteractorCallBack {

    public static final String TAG = BaseMalariaRegisterPresenter.class.getName();

    protected WeakReference<MalariaRegisterContract.View> viewReference;
    protected MalariaRegisterContract.Interactor interactor;
    protected MalariaRegisterContract.Model model;

    public BaseMalariaRegisterPresenter(MalariaRegisterContract.View view, MalariaRegisterContract.Model model, MalariaRegisterContract.Interactor interactor) {
        viewReference = new WeakReference<>(view);
        this.interactor = interactor;
        this.model = model;
    }

    @Override
    public void saveLanguage(String language) {
//        implement
    }

    @Override
    public void startForm(String formName, String entityId, String metadata, String currentLocationId) throws Exception {
        if (StringUtils.isBlank(entityId)) {
            return;
        }

        JSONObject form = model.getFormAsJson(formName, entityId, currentLocationId);
        getView().startFormActivity(form);
    }

    @Override
    public void saveForm(String jsonString) {
        try {
            getView().showProgressDialog(R.string.saving_dialog_title);
            interactor.saveRegistration(jsonString, this);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    @Override
    public void closeFamilyRecord(String jsonString) {
//        implement
    }

    @Override
    public void onUniqueIdFetched(Triple<String, String, String> triple, String entityId) {
//        implement
    }

    @Override
    public void onNoUniqueId() {
//        implement
    }

    @Override
    public void onRegistrationSaved() {
        getView().hideProgressDialog();
    }

    @Override
    public void registerViewConfigurations(List<String> list) {
//        implement
    }

    @Override
    public void unregisterViewConfiguration(List<String> list) {
//        implement
    }

    @Override
    public void onDestroy(boolean b) {
//        implement
    }

    @Override
    public void updateInitials() {
//        implement
    }

    private MalariaRegisterContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }
}
