package org.smartregister.chw.malaria.interactor;

import org.apache.commons.lang3.tuple.Triple;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;

public class BaseMalariaRegisterInteractor implements MalariaRegisterContract.Interactor {
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
//        implement
    }

    @Override
    public void getNextUniqueId(Triple<String, String, String> triple, MalariaRegisterContract.InteractorCallBack callBack) {
//        implement
    }

    @Override
    public void saveRegistration(MalariaRegisterContract.InteractorCallBack callBack) {
//        implement
    }

    @Override
    public void removeFamilyFromRegister(String closeFormJsonString, String providerId) {
//        implement
    }
}
