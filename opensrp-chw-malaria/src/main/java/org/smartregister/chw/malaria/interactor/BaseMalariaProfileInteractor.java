package org.smartregister.chw.malaria.interactor;

import android.support.annotation.VisibleForTesting;

import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.chw.malaria.util.AppExecutors;
import org.smartregister.domain.AlertStatus;

import java.util.Date;

public class BaseMalariaProfileInteractor implements MalariaProfileContract.Interactor {
    protected AppExecutors appExecutors;

    @VisibleForTesting
    BaseMalariaProfileInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BaseMalariaProfileInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void refreshProfileInfo(MemberObject memberObject, MalariaProfileContract.InteractorCallBack callback) {
        Runnable runnable = () -> appExecutors.mainThread().execute(() -> {
            callback.refreshFamilyStatus(AlertStatus.normal);
            callback.refreshMedicalHistory(true);
            callback.refreshUpComingServicesStatus("Malaria Visit", AlertStatus.normal, new Date());
        });
        appExecutors.diskIO().execute(runnable);
    }
}
