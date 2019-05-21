package org.smartregister.chw.malaria.presenter;

import org.apache.commons.lang3.tuple.Triple;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;
import org.smartregister.chw.malaria.interactor.BaseMalariaRegisterInteractor;

import java.lang.ref.WeakReference;
import java.util.List;

public class BaseMalariaRegisterPresenter implements MalariaRegisterContract.Presenter, MalariaRegisterContract.InteractorCallBack  {

    public static final String TAG = BaseMalariaRegisterPresenter.class.getName();

    protected WeakReference<MalariaRegisterContract.View> viewReference;
    protected MalariaRegisterContract.Interactor interactor;
    protected MalariaRegisterContract.Model model;

    public BaseMalariaRegisterPresenter(MalariaRegisterContract.View view, MalariaRegisterContract.Model model) {
        viewReference = new WeakReference<>(view);
        interactor = new BaseMalariaRegisterInteractor();
        this.model = model;
    }

    @Override
    public void saveLanguage(String language) {
//        implement
    }

    @Override
    public void startForm(String formName, String entityId, String metadata, String currentLocationId) throws Exception {
//        implement
    }

    @Override
    public void saveForm(String jsonString, boolean isEditMode) {
//        implement
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
    public void onRegistrationSaved(boolean isEdit) {
//        implement
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
}
