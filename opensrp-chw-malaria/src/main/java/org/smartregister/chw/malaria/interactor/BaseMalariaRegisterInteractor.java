package org.smartregister.chw.malaria.interactor;

import android.support.annotation.VisibleForTesting;

import org.apache.commons.lang3.tuple.Triple;
import org.smartregister.chw.malaria.MalariaLibrary;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;
import org.smartregister.chw.malaria.util.AppExecutors;
import org.smartregister.chw.malaria.util.JsonFormUtils;
import org.smartregister.chw.malaria.util.Util;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.repository.AllSharedPreferences;

public class BaseMalariaRegisterInteractor implements MalariaRegisterContract.Interactor {

    private AppExecutors appExecutors;

    @VisibleForTesting
    BaseMalariaRegisterInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BaseMalariaRegisterInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
//        implement
    }

    @Override
    public void getNextUniqueId(Triple<String, String, String> triple, MalariaRegisterContract.InteractorCallBack callBack) {
//        implement
    }

    @Override
    public void saveRegistration(final String jsonString, final MalariaRegisterContract.InteractorCallBack callBack) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // save it
                try {
                    saveRegistration(jsonString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onRegistrationSaved();
                    }
                });
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void removeFamilyFromRegister(String closeFormJsonString, String providerId) {
//        implement
    }

    private void saveRegistration(final String jsonString) throws Exception {

        AllSharedPreferences allSharedPreferences = MalariaLibrary.getInstance().context().allSharedPreferences();
        Event baseEvent = JsonFormUtils.processJsonForm(allSharedPreferences, jsonString);

        Util.processEvent(allSharedPreferences, baseEvent);
    }
}
