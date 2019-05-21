package org.smartregister.chw.malaria.model;

import org.json.JSONObject;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;

import java.util.List;

public class BaseMalariaRegisterModel implements MalariaRegisterContract.Model  {
    @Override
    public void registerViewConfigurations(List<String> viewIdentifiers) {
//        implement

    }

    @Override
    public void unregisterViewConfiguration(List<String> viewIdentifiers) {
//        implement

    }

    @Override
    public void saveLanguage(String language) {
//        implement

    }

    @Override
    public String getLocationId(String locationName) {
        return null;
    }

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception {
        return null;
    }

    @Override
    public String getInitials() {
        return null;
    }
}
