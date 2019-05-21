package org.smartregister.chw.malaria.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.domain.Form;

import org.json.JSONObject;
import org.smartregister.AllConstants;
import org.smartregister.Context;
import org.smartregister.chw.malaria.contract.MalariaRegisterContract;
import org.smartregister.chw.malaria.fragment.BaseMalariaRegisterFragment;
import org.smartregister.chw.malaria.model.BaseMalariaRegisterModel;
import org.smartregister.chw.malaria.presenter.BaseMalariaRegisterPresenter;
import org.smartregister.chw.malaria.util.Constants;
import org.smartregister.malaria.R;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.List;
import java.util.logging.Logger;

public class BaseMalariaRegisterActivity extends BaseRegisterActivity implements MalariaRegisterContract.View {
    public static final String TAG = BaseMalariaRegisterActivity.class.getCanonicalName();

    @Override
    public void startRegistration() {
        startFormActivity(getRegistrationForm(), null, null);
    }

    public String getRegistrationForm() {
        return Constants.FORMS.MALARIA_REGISTRATION;
    }

    @Override
    public void startFormActivity(String formName, String entityId, String metaData) {
        try {
            if (mBaseFragment instanceof BaseMalariaRegisterFragment) {
                presenter().startForm(formName, entityId, metaData, getLocationID());
            }
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            displayToast(getString(R.string.error_unable_to_start_form));
        }
    }

    protected String getLocationID() {
        return Context.getInstance().allSharedPreferences().getPreference(AllConstants.CURRENT_LOCATION_ID);
    }

    @Override
    public void startFormActivity(JSONObject jsonForm) {
        Intent intent = new Intent(this, getFamilyFormActivity());
        intent.putExtra(Constants.JSON_FORM_EXTRA.JSON, jsonForm.toString());

        if (getFormConfig() != null) {
            intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, getFormConfig());
        }

        startActivityForResult(intent, Constants.REQUEST_CODE_GET_JSON);
    }

    @Override
    public Form getFormConfig() {
        return null;
    }

    public Class getFamilyFormActivity() {
        return BaseMalariaRegisterActivity.class;
    }

    @Override
    protected void onActivityResultExtended(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {
            try {
                String jsonString = data.getStringExtra(Constants.JSON_FORM_EXTRA.JSON);
                Log.d("JSONResult", jsonString);

                JSONObject form = new JSONObject(jsonString);
                if (form.getString(Constants.ENCOUNTER_TYPE).equals(getRegisterEventType())) {
                    presenter().saveForm(jsonString, false);
                }
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }

        }
    }

    @Override
    public List<String> getViewIdentifiers() {
        return null;
    }

    /**
     * Returns the event type for a malaria registration
     *
     * @return
     */
    public String getRegisterEventType() {
        return Constants.EVENT_TYPE.MALARIA_REGISTRATION;
    }

    /**
     * Override this to subscribe to bottom navigation
     */
    @Override
    protected void registerBottomNavigation() {
//        implement BottomNavigation
    }

    @Override
    protected void initializePresenter() {
        presenter = new BaseMalariaRegisterPresenter(this, new BaseMalariaRegisterModel());
    }

    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        return new BaseMalariaRegisterFragment();
    }

    @Override
    protected Fragment[] getOtherFragments() {
        return new Fragment[0];
    }

    @Override
    public MalariaRegisterContract.Presenter presenter() {
        return (MalariaRegisterContract.Presenter) presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profile_menu) {
            Intent intent = new Intent(this, MalariaProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
