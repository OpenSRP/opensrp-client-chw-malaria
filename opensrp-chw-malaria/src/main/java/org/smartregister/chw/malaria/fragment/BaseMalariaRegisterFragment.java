package org.smartregister.chw.malaria.fragment;

import org.smartregister.chw.malaria.contract.MalariaRegisterFragmentContract;
import org.smartregister.chw.malaria.model.BaseMalariaRegisterFragmentModel;
import org.smartregister.chw.malaria.presenter.BaseMalariaRegisterFragmentPresenter;
import org.smartregister.chw.malaria.provider.MalariaRegisterProvider;
import org.smartregister.configurableviews.model.View;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;
import java.util.Set;

public class BaseMalariaRegisterFragment extends BaseRegisterFragment implements MalariaRegisterFragmentContract.View {
    @Override
    public void initializeAdapter(Set<View> visibleColumns) {
        MalariaRegisterProvider malariaRegisterProvider = new MalariaRegisterProvider(getActivity(), commonRepository(), visibleColumns, registerActionHandler, paginationViewHandler);
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
//        start forms here if the module requires registration
    }

    @Override
    protected void onViewClicked(android.view.View view) {
//        implement onclick actions
    }

    @Override
    public void showNotFoundPopup(String s) {
//        implement dialog
    }
}
