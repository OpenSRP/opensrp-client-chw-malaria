package org.smartregister.chw.malaria.util;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.malaria.MalariaLibrary;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.domain.tag.FormTag;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.util.FormUtils;

import static org.smartregister.chw.malaria.util.Constants.ENCOUNTER_TYPE;
import static org.smartregister.chw.malaria.util.Constants.STEP_ONE;
import static org.smartregister.chw.malaria.util.Constants.STEP_TWO;

public class JsonFormUtils extends org.smartregister.util.JsonFormUtils {
    public static final String METADATA = "metadata";

    protected static Triple<Boolean, JSONObject, JSONArray> validateParameters(String jsonString) {

        JSONObject jsonForm = toJSONObject(jsonString);
        JSONArray fields = malariaFormFields(jsonForm);

        Triple<Boolean, JSONObject, JSONArray> registrationFormParams = Triple.of(jsonForm != null && fields != null, jsonForm, fields);
        return registrationFormParams;
    }

    public static JSONArray malariaFormFields(JSONObject jsonForm) {
        try {
            JSONObject step1 = jsonForm.has(STEP_ONE) ? jsonForm.getJSONObject(STEP_ONE) : null;
            if (step1 != null) {
                JSONArray fieldsOne = step1.has(FIELDS) ? step1.getJSONArray(FIELDS) : null;
                JSONObject step2 = jsonForm.has(STEP_TWO) ? jsonForm.getJSONObject(STEP_TWO) : null;
                if (step2 != null) {
                    JSONArray fieldsTwo = step2.has(FIELDS) ? step2.getJSONArray(FIELDS) : null;
                    for (int i = 0; i < fieldsTwo.length(); i++) {
                        fieldsOne.put(fieldsTwo.get(i));
                    }
                }
                return fieldsOne;
            }
        } catch (JSONException e) {
            Log.e(TAG, "", e);
        }
        return null;
    }

    public static Event processJsonForm(AllSharedPreferences allSharedPreferences, String
            jsonString) {

        Triple<Boolean, JSONObject, JSONArray> registrationFormParams = validateParameters(jsonString);

        if (!registrationFormParams.getLeft()) {
            return null;
        }

        JSONObject jsonForm = registrationFormParams.getMiddle();
        JSONArray fields = registrationFormParams.getRight();
        String entityId = getString(jsonForm, ENTITY_ID);

        return org.smartregister.util.JsonFormUtils.createEvent(fields, getJSONObject(jsonForm, METADATA), formTag(allSharedPreferences), entityId, getString(jsonForm, ENCOUNTER_TYPE), Constants.TABLES.MALARIA_CONFIRMATION);
    }


    protected static FormTag formTag(AllSharedPreferences allSharedPreferences) {
        FormTag formTag = new FormTag();
        formTag.providerId = allSharedPreferences.fetchRegisteredANM();
        formTag.appVersion = MalariaLibrary.getInstance().getApplicationVersion();
        formTag.databaseVersion = MalariaLibrary.getInstance().getDatabaseVersion();
        return formTag;
    }


    public static void tagEvent(AllSharedPreferences allSharedPreferences, Event event) {
        String providerId = allSharedPreferences.fetchRegisteredANM();
        event.setProviderId(providerId);
        event.setLocationId(locationId(allSharedPreferences));
        event.setChildLocationId(allSharedPreferences.fetchCurrentLocality());
        event.setTeam(allSharedPreferences.fetchDefaultTeam(providerId));
        event.setTeamId(allSharedPreferences.fetchDefaultTeamId(providerId));

        event.setClientApplicationVersion(MalariaLibrary.getInstance().getApplicationVersion());
        event.setClientDatabaseVersion(MalariaLibrary.getInstance().getDatabaseVersion());
    }

    private static String locationId(AllSharedPreferences allSharedPreferences) {
        String providerId = allSharedPreferences.fetchRegisteredANM();
        String userLocationId = allSharedPreferences.fetchUserLocalityId(providerId);
        if (StringUtils.isBlank(userLocationId)) {
            userLocationId = allSharedPreferences.fetchDefaultLocalityId(providerId);
        }

        return userLocationId;
    }

    public static void getRegistrationForm(JSONObject jsonObject, String entityId, String
            currentLocationId) throws JSONException {
        jsonObject.getJSONObject(METADATA).put(ENCOUNTER_LOCATION, currentLocationId);
        jsonObject.put(org.smartregister.util.JsonFormUtils.ENTITY_ID, entityId);
    }

    public static JSONObject getFormAsJson(String formName) throws Exception {
        return FormUtils.getInstance(MalariaLibrary.getInstance().context().applicationContext()).getFormJson(formName);
    }

}
