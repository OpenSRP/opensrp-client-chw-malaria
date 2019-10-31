package org.smartregister.chw.malaria.interactor;

import android.support.annotation.VisibleForTesting;

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
    public void saveRegistration(final String jsonString, final MalariaRegisterContract.InteractorCallBack callBack) {

        Runnable runnable = () -> {
            try {
                saveRegistration(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            appExecutors.mainThread().execute(() -> callBack.onRegistrationSaved());
        };
        appExecutors.diskIO().execute(runnable);
    }

    private void saveRegistration(final String jsonString) throws Exception {

        AllSharedPreferences allSharedPreferences = MalariaLibrary.getInstance().context().allSharedPreferences();
        Event baseEvent = JsonFormUtils.processJsonForm(allSharedPreferences, jsonString);

        Util.processEvent(allSharedPreferences, baseEvent);
    }
}
