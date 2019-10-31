package org.smartregister.chw.malaria.model;

import org.json.JSONObject;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;
import org.smartregister.chw.malaria.util.JsonFormUtils;

public class BaseMalariaRegisterModel implements MalariaRegisterContract.Model {

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception {
        JSONObject jsonObject = JsonFormUtils.getFormAsJson(formName);
        JsonFormUtils.getRegistrationForm(jsonObject, entityId, currentLocationId);

        return jsonObject;
    }

}
