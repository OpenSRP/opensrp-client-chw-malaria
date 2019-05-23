package org.smartregister.chw.malaria.domain;

import com.vijay.jsonwizard.activities.JsonWizardFormActivity;

import org.smartregister.view.activity.BaseProfileActivity;

public class MalariaMetadata {

    public final Class familyFormActivity;
    public final Class familyMemberFormActivity;
    public final Class profileActivity;
    public final String uniqueIdentifierKey;
    public final boolean formWizardValidateRequiredFieldsBefore;

    public MalariaRegister malariaRegister;
    public FamilyMemberRegister familyMemberRegister;
    public FamilyDueRegister familyDueRegister;
    public FamilyActivityRegister familyActivityRegister;
    public FamilyOtherMemberRegister familyOtherMemberRegister;

    public MalariaMetadata(Class<? extends JsonWizardFormActivity> familyFormActivity, Class<? extends JsonWizardFormActivity> familyMemberFormActivity, Class<? extends BaseProfileActivity> profileActivity, String uniqueIdentifierKey, boolean formWizardValidateRequiredFieldsBefore) {
        this.familyFormActivity = familyFormActivity;
        this.familyMemberFormActivity = familyMemberFormActivity;
        this.profileActivity = profileActivity;
        this.uniqueIdentifierKey = uniqueIdentifierKey;
        this.formWizardValidateRequiredFieldsBefore = formWizardValidateRequiredFieldsBefore;
    }

    public void updateFamilyRegister(String formName, String tableName, String registerEventType, String updateEventType, String config, String familyHeadRelationKey, String familyCareGiverRelationKey) {
        this.malariaRegister = new MalariaRegister(formName, tableName, registerEventType, updateEventType, config, familyHeadRelationKey, familyCareGiverRelationKey);
    }

    public void updateFamilyMemberRegister(String formName, String tableName, String registerEventType, String updateEventType, String config, String familyRelationKey) {
        this.familyMemberRegister = new FamilyMemberRegister(formName, tableName, registerEventType, updateEventType, config, familyRelationKey);
    }

    public void updateFamilyDueRegister(String tableName, int currentLimit, boolean showPagination) {
        this.familyDueRegister = new FamilyDueRegister(tableName, currentLimit, showPagination);
    }

    public void updateFamilyActivityRegister(String tableName, int currentLimit, boolean showPagination) {
        this.familyActivityRegister = new FamilyActivityRegister(tableName, currentLimit, showPagination);
    }

    public void updateFamilyOtherMemberRegister(String tableName, int currentLimit, boolean showPagination) {
        this.familyOtherMemberRegister = new FamilyOtherMemberRegister(tableName, currentLimit, showPagination);
    }

    public class MalariaRegister {

        public final String formName;

        public final String tableName;

        public final String registerEventType;

        public final String updateEventType;

        public final String config;

        public final String familyHeadRelationKey;

        public final String familyCareGiverRelationKey;


        public MalariaRegister(String formName, String tableName, String registerEventType, String updateEventType, String config, String familyHeadRelationKey, String familyCareGiverRelationKey) {
            this.formName = formName;
            this.tableName = tableName;
            this.registerEventType = registerEventType;
            this.updateEventType = updateEventType;
            this.config = config;
            this.familyHeadRelationKey = familyHeadRelationKey;
            this.familyCareGiverRelationKey = familyCareGiverRelationKey;
        }
    }

    public class FamilyMemberRegister {

        public final String formName;

        public final String tableName;

        public final String registerEventType;

        public final String updateEventType;

        public final String config;

        public final String familyRelationKey;


        public FamilyMemberRegister(String formName, String tableName, String registerEventType, String updateEventType, String config, String familyRelationKey) {
            this.formName = formName;
            this.tableName = tableName;
            this.registerEventType = registerEventType;
            this.updateEventType = updateEventType;
            this.config = config;
            this.familyRelationKey = familyRelationKey;
        }
    }

    public class FamilyDueRegister {

        public final String tableName;
        public final int currentLimit;
        public final boolean showPagination;

        public FamilyDueRegister(String tableName, int currentLimit, boolean showPagination) {
            this.tableName = tableName;
            if (currentLimit <= 0) {
                this.currentLimit = 20;
            } else {
                this.currentLimit = currentLimit;
            }
            this.showPagination = showPagination;
        }
    }

    public class FamilyActivityRegister {

        public final String tableName;
        public final int currentLimit;
        public final boolean showPagination;

        public FamilyActivityRegister(String tableName, int currentLimit, boolean showPagination) {
            this.tableName = tableName;
            if (currentLimit <= 0) {
                this.currentLimit = 20;
            } else {
                this.currentLimit = currentLimit;
            }
            this.showPagination = showPagination;
        }
    }

    public class FamilyOtherMemberRegister {

        public final String tableName;
        public final int currentLimit;
        public final boolean showPagination;

        public FamilyOtherMemberRegister(String tableName, int currentLimit, boolean showPagination) {
            this.tableName = tableName;
            if (currentLimit <= 0) {
                this.currentLimit = 20;
            } else {
                this.currentLimit = currentLimit;
            }
            this.showPagination = showPagination;
        }
    }
}
