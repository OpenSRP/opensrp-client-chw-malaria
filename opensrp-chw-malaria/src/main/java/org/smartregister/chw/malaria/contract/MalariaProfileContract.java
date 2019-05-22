package org.smartregister.chw.malaria.contract;

import android.content.Context;
import android.content.Intent;

import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONObject;
import org.smartregister.chw.malaria.domain.MalariaEventClient;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.domain.FetchStatus;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.view.contract.BaseProfileContract;

public interface MalariaProfileContract {

    interface View extends BaseProfileContract.View {

        Context getApplicationContext();

        String getString(int resourceId);

        void startFormActivity(JSONObject form);

        void refreshMemberList(final FetchStatus fetchStatus);

        void displayShortToast(int resourceId);

        void setProfileImage(String baseEntityId);

        void setProfileName(String fullName);

        void setProfileDetailOne(String detailOne);

        void setProfileDetailTwo(String detailTwo);

        void setProfileDetailThree(String detailThree);

        MalariaProfileContract.Presenter presenter();

    }

    interface Presenter extends BaseProfileContract.Presenter {

        MalariaProfileContract.View getView();

        void startForm(String formName, String entityId, String metadata, String currentLocationId) throws Exception;

        void saveFamilyMember(String jsonString);

        void updateFamilyRegister(String jsonString);

        void fetchProfileData();

        void refreshProfileView();

        void processFormDetailsSave(Intent data, AllSharedPreferences allSharedPreferences);

        String familyBaseEntityId();

    }

    interface Interactor {

        void onDestroy(boolean isChangingConfiguration);

        void refreshProfileView(String baseEntityId, boolean isForEdit, MalariaProfileContract.InteractorCallBack callback);

        void getNextUniqueId(Triple<String, String, String> triple, MalariaProfileContract.InteractorCallBack callBack);

        void saveRegistration(final MalariaEventClient familyEventClient, final String jsonString, final boolean isEditMode, final MalariaProfileContract.InteractorCallBack callBack);

    }

    interface InteractorCallBack {

        void startFormForEdit(CommonPersonObjectClient client);

        void refreshProfileTopSection(CommonPersonObjectClient client);

        void onUniqueIdFetched(Triple<String, String, String> triple, String entityId);

        void onNoUniqueId();

        void onRegistrationSaved(boolean isEditMode);

    }

    interface Model {

        JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception;

        MalariaEventClient processMemberRegistration(String jsonString, String familyBaseEntityId);

        MalariaEventClient processFamilyRegistrationForm(String jsonString, String familyBaseEntityId);

        MalariaEventClient processUpdateMemberRegistration(String jsonString, String familyBaseEntityId);

    }

}
