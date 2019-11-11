package org.smartregister.chw.malaria.contract;

import org.jetbrains.annotations.Nullable;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.domain.AlertStatus;

import java.util.Date;

public interface MalariaProfileContract {
    interface View extends InteractorCallBack {

        void setProfileViewWithData();

        void hideView();

        void setDueColor();

        void setOverDueColor();

        void openMedicalHistory();

        void openUpcomingService();

        void openFamilyDueServices();

        void showProgressBar(boolean status);
    }

    interface Presenter {

        void fillProfileData(@Nullable MemberObject memberObject);

        @Nullable
        View getView();

        void refreshProfileBottom();

        void recordMalariaButton(int days);
    }

    interface Interactor {

        void refreshProfileInfo(MemberObject memberObject, InteractorCallBack callback);

    }

    interface InteractorCallBack {

        void refreshMedicalHistory(boolean hasHistory);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);
    }
}