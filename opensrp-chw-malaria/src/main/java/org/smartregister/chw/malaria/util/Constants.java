package org.smartregister.chw.malaria.util;

public interface Constants {

    int REQUEST_CODE_GET_JSON = 2244;
    String ENCOUNTER_TYPE = "encounter_type";
    String STEP_ONE = "step1";
    String STEP_TWO = "step2";

    interface JSON_FORM_EXTRA {
        String JSON = "json";
        String ENCOUNTER_TYPE = "encounter_type";
    }

    interface EVENT_TYPE {
        String MALARIA_CONFIRMATION = "Malaria Confirmation";
        String MALARIA_FOLLOWUP = "Malaria Followup";
        String MALARIA_FOLLOW_UP_VISIT = "Malaria Follow-up Visit";
    }

    interface FORMS {
        String MALARIA_REGISTRATION = "malaria_confirmation";
        String MALARIA_FOLLOWUP = "malaria_followup";
        String MALARIA_FOLLOW_UP_VISIT = "malaria_followup_visit";
    }

    interface TABLES {
        String MALARIA_CONFIRMATION = "ec_malaria_confirmation";
        String MALARIA_FOLLOW_UP = "ec_malaria_follow_up_visit";
    }

    interface ACTIVITY_PAYLOAD {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String ACTION = "ACTION";
        String MALARIA_FORM_NAME = "MALARIA_FORM_NAME";

    }

    interface ACTIVITY_PAYLOAD_TYPE {
        String REGISTRATION = "REGISTRATION";
        String FOLLOWUP = "FOLLOWUP";
        String FOLLOW_UP_VISIT = "FOLLOW_UP_VISIT";
    }

    interface CONFIGURATION {
        String MALARIA_CONFIRMATION = "malaria_confirmation";
    }

    interface MALARIA_MEMBER_OBJECT {
        String MEMBER_OBJECT = "memberObject";
    }

}
