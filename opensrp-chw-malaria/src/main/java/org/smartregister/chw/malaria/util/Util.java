package org.smartregister.chw.malaria.util;

import org.json.JSONObject;
import org.smartregister.chw.malaria.MalariaLibrary;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.malaria.R;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.repository.BaseRepository;
import org.smartregister.sync.ClientProcessorForJava;
import org.smartregister.sync.helper.ECSyncHelper;

import java.util.Date;

import static org.smartregister.util.Utils.getAllSharedPreferences;

public class Util {

    public static void processEvent(AllSharedPreferences allSharedPreferences, Event baseEvent) throws Exception {
        if (baseEvent != null) {
            JsonFormUtils.tagEvent(allSharedPreferences, baseEvent);
            JSONObject eventJson = new JSONObject(JsonFormUtils.gson.toJson(baseEvent));
            getSyncHelper().addEvent(baseEvent.getBaseEntityId(), eventJson);

            long lastSyncTimeStamp = getAllSharedPreferences().fetchLastUpdatedAtDate(0);
            Date lastSyncDate = new Date(lastSyncTimeStamp);
            getClientProcessorForJava().processClient(getSyncHelper().getEvents(lastSyncDate, BaseRepository.TYPE_Unsynced));
            getAllSharedPreferences().saveLastUpdatedAtDate(lastSyncDate.getTime());
        }
    }

    public static ECSyncHelper getSyncHelper() {
        return MalariaLibrary.getInstance().getEcSyncHelper();
    }

    public static ClientProcessorForJava getClientProcessorForJava() {
        return MalariaLibrary.getInstance().getClientProcessorForJava();
    }

    public static int getMemberProfileImageResourceIDentifier(String entityType) {
        return R.mipmap.ic_member;
    }
}
