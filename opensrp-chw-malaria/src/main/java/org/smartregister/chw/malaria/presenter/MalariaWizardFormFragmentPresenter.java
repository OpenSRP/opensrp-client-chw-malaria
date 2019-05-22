package org.smartregister.chw.malaria.presenter;

import android.widget.LinearLayout;

import com.vijay.jsonwizard.fragments.JsonFormFragment;
import com.vijay.jsonwizard.interactors.JsonFormInteractor;
import com.vijay.jsonwizard.presenters.JsonFormFragmentPresenter;

import org.smartregister.chw.malaria.fragment.MalariaWizardFormFragment;
import org.smartregister.chw.malaria.util.Constants;

/**
 * Created by keyman on 04/08/18.
 */
public class MalariaWizardFormFragmentPresenter extends JsonFormFragmentPresenter {

    public static final String TAG = MalariaWizardFormFragmentPresenter.class.getName();

    public MalariaWizardFormFragmentPresenter(JsonFormFragment formFragment, JsonFormInteractor jsonFormInteractor) {
        super(formFragment, jsonFormInteractor);
    }

    @Override
    public void setUpToolBar() {
        super.setUpToolBar();
    }

    @Override
    public void onNextClick(LinearLayout mainView) {

        validateAndWriteValues();
        boolean validateOnSubmit = validateOnSubmit();
        if (validateOnSubmit && getIncorrectlyFormattedFields().isEmpty()) {
            moveToNextWizardStep();
        } else if (isFormValid()) {
            moveToNextWizardStep();
        } else {
            getView().showSnackBar(getView().getContext().getResources()
                    .getString(com.vijay.jsonwizard.R.string.json_form_on_next_error_msg));
        }
    }

    private void moveToNextWizardStep() {
        JsonFormFragment next = MalariaWizardFormFragment.getFormFragment(mStepDetails.optString(Constants.JSON_FORM_EXTRA.NEXT));
        getView().hideKeyBoard();
        getView().transactThis(next);
    }

    public boolean intermediatePage() {
        return this.mStepDetails != null && this.mStepDetails.has(Constants.JSON_FORM_EXTRA.NEXT);
    }
}