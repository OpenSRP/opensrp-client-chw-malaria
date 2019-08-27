package org.smartregister.chw.malaria.contract;
import org.smartregister.view.contract.BaseProfileContract;

public interface MalariaProfileContract {
    interface View {
        void setProfileViewWithData();

        void hideView();

        void setDueColor();

        void setOverDueColor();

        void setProfileImage(String baseEntityId, String entityType);
    }

    interface InteractorCallBack {

    }

    interface Interactor {

    }

    interface Presenter extends BaseProfileContract.Presenter {
        MalariaProfileContract.View getView();
    }
}