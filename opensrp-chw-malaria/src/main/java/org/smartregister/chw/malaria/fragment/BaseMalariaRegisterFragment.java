package org.smartregister.chw.malaria.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

import org.smartregister.chw.malaria.activity.BaseMalariaRegisterActivity;
import org.smartregister.chw.malaria.contract.MalariaRegisterFragmentContract;
import org.smartregister.chw.malaria.model.BaseMalariaRegisterFragmentModel;
import org.smartregister.chw.malaria.presenter.BaseMalariaRegisterFragmentPresenter;
import org.smartregister.chw.malaria.provider.MalariaRegisterProvider;
import org.smartregister.chw.malaria.util.DBConstants;
import org.smartregister.chw.malaria.util.Utils;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.configurableviews.model.View;
import org.smartregister.chw.malaria.util.Constants;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.malaria.R;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class BaseMalariaRegisterFragment extends BaseRegisterFragment implements MalariaRegisterFragmentContract.View {
    private static final String TAG = BaseMalariaRegisterFragment.class.getCanonicalName();
    public static final String CLICK_VIEW_NORMAL = "click_view_normal";
    public static final String CLICK_VIEW_DOSAGE_STATUS = "click_view_dosage_status";
    @Override
    public void initializeAdapter(Set<View> visibleColumns) {
        MalariaRegisterProvider malariaRegisterProvider = new MalariaRegisterProvider(getActivity(), paginationViewHandler);
        clientAdapter = new RecyclerViewPaginatedAdapter(null, malariaRegisterProvider, context().commonrepository(this.tablename));
        clientAdapter.setCurrentlimit(20);
        clientsView.setAdapter(clientAdapter);
    }

    @Override
    public MalariaRegisterFragmentContract.Presenter presenter() {
        return (MalariaRegisterFragmentContract.Presenter) presenter;
    }

    @Override
    protected void initializePresenter() {
        if (getActivity() == null) {
            return;
        }
        presenter = new BaseMalariaRegisterFragmentPresenter(this, new BaseMalariaRegisterFragmentModel(), null);
    }

    @Override
    public void setUniqueID(String s) {
        if (getSearchView() != null) {
            getSearchView().setText(s);
        }
    }

    @Override
    public void setAdvancedSearchFormData(HashMap<String, String> hashMap) {
//        implement search here
    }

    @Override
    protected String getMainCondition() {
        return "";
    }

    @Override
    protected String getDefaultSortQuery() {
        return "";
    }

    @Override
    protected void startRegistration() {
        ((BaseMalariaRegisterActivity) getActivity()).startFormActivity(Utils.metadata().malariaRegister.formName, null, null);
    }

    @Override
    protected void onViewClicked(android.view.View view) {

        if (getActivity() == null) {
            return;
        }

        if (view.getTag() != null && view.getTag(R.id.VIEW_ID) == CLICK_VIEW_NORMAL) {
            goToPatientDetailActivity((CommonPersonObjectClient) view.getTag(), false);
        } else if (view.getTag() != null && view.getTag(R.id.VIEW_ID) == CLICK_VIEW_DOSAGE_STATUS) {
            goToPatientDetailActivity((CommonPersonObjectClient) view.getTag(), true);
        }
    }



    private void goToPatientDetailActivity(CommonPersonObjectClient patient,
                                           boolean goToDuePage) {

        Intent intent = new Intent(getActivity(), Utils.metadata().profileActivity);
        intent.putExtra(Constants.INTENT_KEY.FAMILY_BASE_ENTITY_ID, patient.getCaseId());
        intent.putExtra(Constants.INTENT_KEY.FAMILY_HEAD, Utils.getValue(patient.getColumnmaps(), DBConstants.KEY.FAMILY_HEAD, false));
        intent.putExtra(Constants.INTENT_KEY.PRIMARY_CAREGIVER, Utils.getValue(patient.getColumnmaps(), DBConstants.KEY.PRIMARY_CAREGIVER, false));
        intent.putExtra(Constants.INTENT_KEY.VILLAGE_TOWN, Utils.getValue(patient.getColumnmaps(), DBConstants.KEY.VILLAGE_TOWN, false));
        intent.putExtra(Constants.INTENT_KEY.FAMILY_NAME, Utils.getValue(patient.getColumnmaps(), DBConstants.KEY.FIRST_NAME, false));
        intent.putExtra(Constants.INTENT_KEY.GO_TO_DUE_PAGE, goToDuePage);

        startActivity(intent);
    }

    @Override
    public void showNotFoundPopup(String s) {
//        implement dialog
    }
}
