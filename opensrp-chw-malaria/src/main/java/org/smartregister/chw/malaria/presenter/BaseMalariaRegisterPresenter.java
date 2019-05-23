package org.smartregister.chw.malaria.presenter;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONObject;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;
import org.smartregister.chw.malaria.interactor.BaseMalariaRegisterInteractor;
import org.smartregister.malaria.R;

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

        if (StringUtils.isBlank(entityId)) {
            Triple<String, String, String> triple = Triple.of(formName, metadata, currentLocationId);
            interactor.getNextUniqueId(triple, this);
            return;
        }

        JSONObject form = model.getFormAsJson(formName, entityId, currentLocationId);
        getView().startFormActivity(form);

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
        try {
            startForm(triple.getLeft(), entityId, triple.getMiddle(), triple.getRight());
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            getView().displayToast(R.string.error_unable_to_start_form);
        }
    }

    @Override
    public void onNoUniqueId() {
        getView().displayShortToast(R.string.no_unique_id);
    }

    @Override
    public void onRegistrationSaved(boolean isEdit) {
//        implement
    }

    @Override
    public void registerViewConfigurations(List<String> list) {
        model.registerViewConfigurations(list);
    }

    @Override
    public void unregisterViewConfiguration(List<String> list) {
        model.unregisterViewConfiguration(list);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        viewReference = null;//set to null on destroy
        // Inform interactor
        interactor.onDestroy(isChangingConfiguration);
        // Activity destroyed set interactor to null
        if (!isChangingConfiguration) {
            interactor = null;
            model = null;
        }
    }

    @Override
    public void updateInitials() {
        String initials = model.getInitials();
        if (initials != null) {
            getView().updateInitialsText(initials);
        }
    }

    private MalariaRegisterContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }
}
